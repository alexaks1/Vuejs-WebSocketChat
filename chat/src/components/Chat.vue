<template>
  <div v-if="!isLoading" class="chats">
    <div class="sidebar left-bar">
      <div class="top-bar">
        <button
          v-if="user.role === 'ADMIN'"
          @click="$router.push({ name: 'admin' })"
          class="add-chat-btn"
          name="add-chat"
        >
          <img src="../../src/assets/icons/add.svg" />
        </button>
        <h1>Чаты</h1>
      </div>
      <ul class="chat-list">
        <ChatListItem
          v-for="(chat, index) in chats"
          :name="chat.name"
          :key="chat.id"
          @click.native="updateSelectedChat(index)"
        ></ChatListItem>
      </ul>
    </div>
    <div class="sidebar right-bar">
      <div class="top-bar">
        <div class="chosen-chat-info">
          <h2 class="chosen-chat-info__name">{{ chatName }}</h2>
          <p
            v-if="selectedChat !== -1"
            class="chosen-chat-info__number-of-people"
          >
            {{ numberOfMembers }} участников
          </p>
        </div>
      </div>
      <ul class="chat-history">
        <Message
          v-for="(message, index) in loadedMessages"
          :message="loadedMessages[index]"
          :users="chats[selectedChat].users"
          :key="index"
        ></Message>
      </ul>
      <form
        v-if="selectedChat !== -1"
        class="message-form"
        @submit.prevent="sendMsg"
      >
        <textarea
          type="text"
          v-model="newMessage"
          class="message-form__textarea message-input-area"
          placeholder="Введите ваше сообщение..."
          required
        ></textarea>
        <button
          class="message-form__submit-btn"
          type="submit"
          name="send-message"
        >
          <img src="../../src/assets/icons/send.svg" />
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import ChatListItem from "./ChatListItem.vue";
import Message from "./Message.vue";
import Stomp from "stompjs";
import SockJS from "sockjs-client";

export default {
  name: "Chat",
  components: {
    ChatListItem,
    Message,
  },
  data() {
    return {
      isLoading: true,
      user: null,
      selectedChat: -1,
      chats: [],
      loadedMessages: [],
      newMessage: "",
      ws: null,
    };
  },
  methods: {
    // isAdmin() {
    //   if (this.user.role === "ADMIN") return true;
    //   return false;
    // },
    updateSelectedChat(index) {
      this.selectedChat = index;
      const sockjs = new SockJS("http://localhost:8080/sockjs");
      const stompClient = Stomp.over(sockjs);
      this.ws = stompClient;

      axios.get(`http://localhost:8080/messages/${this.chats[this.selectedChat].id}`).then((response) => {
        // this.chats[this.selectedChat].messages = response.data.map(
        this.loadedMessages = response.data.map((message) => {
          return {
            id: message.id,
            chatId: message.chatId,
            content: message.content,
            sender: message.sender,
          };
        });
      });
      stompClient.connect(
        {},
        () => {
          console.log("connected");
          stompClient.subscribe(`/user/${this.chats[this.selectedChat].id}/messages`, (answer) => {
            console.log(answer, "recieved");
            // axios.get("http://localhost:8080/messages/1").then(console.log);
            axios.get(`http://localhost:8080/messages/${this.chats[this.selectedChat].id}`).then((response) => {
              this.loadedMessages = response.data.map((message) => {
                return {
                  id: message.id,
                  chatId: message.chatId,
                  content: message.content,
                  sender: message.sender,
                };
              });
            });
          });
        },
        (error) => {
          console.log("error", error);
        }
      );
    },
    sendMsg() {
      console.log('Нажата кнопка "Отправить"');
      this.ws.send(
        "/app/send",
        {},
        JSON.stringify({
          id: 0,
          chatId: this.chats[this.selectedChat].id,
          content: this.newMessage,
          sender: this.user.id,
          // time: new Date(),
        })
      );
      this.newMessage = "";
    },
  },
  computed: {
    chatName() {
      if (this.selectedChat === -1) return "Выберите чат";
      if (this.chats.length) return this.chats[this.selectedChat].name;
      return "";
    },
    numberOfMembers() {
      if (this.selectedChat === -1) return;
      return this.chats[this.selectedChat].users.length;
    },
  },
  mounted() {
    //Сохраняем данные авторизации пользователя в sessionStorage
    let sessionStorage = window.sessionStorage;
    this.user = JSON.parse(sessionStorage.user);
    //Загружаем чаты
    axios
      .get("http://localhost:8080/chats/", {
        params: {
          login: this.user.login,
          password: this.user.password,
        },
      })
      .then((response) => {
        // console.log(response);
        if (response.data.length !== 0)
          this.chats = response.data.map((chat) => {
            return {
              id: chat.id,
              name: chat.name,
              users: chat.users,
            };
          });
        //console.log(this.chats)
      })
      .finally(() => {
        this.isLoading = false;
      });
  },
};
</script>

