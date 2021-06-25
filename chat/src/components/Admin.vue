<template>
  <div class="admin">
    <div class="admin__container">
      <button class="admin__back-btn" @click="$router.push({ name: 'chats' })">
        Назад
      </button>
      <h1>AdminPanel</h1>
      <form class="admin__input-area" @submit.prevent="addUser">
        <div class="admin__input-and-label">
          <label>ID чата</label>
          <input
            class="admin__input"
            v-model="selectedChatId"
            type="text"
            name="selected-chat-id"
            required
            minlength="5"
            maxlength="30"
          />
        </div>
        <div class="admin__input-and-label">
          <label>ID пользователя</label>
          <input
            class="admin__input"
            v-model="selectedUserId"
            type="text"
            name="selected-user-id"
            required
            minlength="5"
            maxlength="30"
          />
        </div>
        <button class="admin__add-user-to-chat-btn">Добавить</button>
        <span v-if="result.message" v-bind:class="{ 'success-message': !result.isError }">{{
          result.message
        }}</span>
      </form>
      <div class="chats-users-lists">
        <div class="list-and-title">
          <div class="list-and-title__title">
            <h2>Чаты</h2>
            <button class="add-to-list">+</button>
          </div>
          <AdminList
            :items="chats"
            @selectVariant="updateSelectedChatId"
          ></AdminList>
        </div>
        <div class="list-and-title">
          <div class="list-and-title__title">
            <h2>Пользователи</h2>
            <button class="add-to-list">+</button>
          </div>
          <AdminList
            :items="users"
            @selectVariant="updateSelectedUserId"
          ></AdminList>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import AdminList from "../components/AdminList.vue";
export default {
  name: "Admin",
  components: {
    AdminList,
  },
  data() {
    return {
      selectedChatId: null,
      selectedUserId: null,
      newChat: {
        title: "Чат 1",
      },
      newUser: {
        firstName: "Багыр",
        lastName: "Делгер",
        login: "login",
        password: "password",
      },
      result: {
        isError: false,
        message: "Успешно!",
      },
      users: [
        {
          id: 1,
          firstName: "Багыр",
          lastName: "Делгер",
          login: "login",
          password: "password",
        },
        {
          id: 2,
          firstName: "Азамат",
          lastName: "Айталиев",
          login: "login",
          password: "password",
        },
      ],
      chats: [
        {
          id: 1,
          title: "Чат 1",
        },
        {
          id: 2,
          title: "Чат 2",
        },
        {
          id: 3,
          title: "Чат 3",
        },
        {
          id: 4,
          title: "Чат 1",
        },
        {
          id: 5,
          title: "Чат 2",
        },
        {
          id: 6,
          title: "Чат 3",
        },
        {
          id: 7,
          title: "Чат 1",
        },
        {
          id: 8,
          title: "Чат 2",
        },
        {
          id: 9,
          title: "Чат 3",
        },
        {
          id: 10,
          title: "Чат 1",
        },
        {
          id: 11,
          title: "Чат 2",
        },
        {
          id: 12,
          title: "Чат 3",
        },
        {
          id: 13,
          title: "Чат 1",
        },
        {
          id: 14,
          title: "Чат 2",
        },
        {
          id: 15,
          title: "Чат 3",
        },
        {
          id: 16,
          title: "Чат 1",
        },
        {
          id: 17,
          title: "Чат 2",
        },
        {
          id: 18,
          title: "Чат 3",
        },
        {
          id: 19,
          title: "Чат 1",
        },
        {
          id: 20,
          title: "Чат 2",
        },
        {
          id: 21,
          title: "Чат 3",
        },
        {
          id: 22,
          title: "Чат 1",
        },
        {
          id: 23,
          title: "Чат 2",
        },
        {
          id: 24,
          title: "Чат 3",
        },
        {
          id: 25,
          title: "Чат 1",
        },
        {
          id: 26,
          title: "Чат 2",
        },
        {
          id: 27,
          title: "Чат 3",
        },
        {
          id: 28,
          title: "Чат 1",
        },
        {
          id: 29,
          title: "Чат 2",
        },
        {
          id: 30,
          title: "Чат 3",
        },
        {
          id: 31,
          title: "Чат 1",
        },
        {
          id: 32,
          title: "Чат 2",
        },
        {
          id: 33,
          title: "Чат 3",
        },
        {
          id: 34,
          title: "Чат 1",
        },
        {
          id: 35,
          title: "Чат 2",
        },
        {
          id: 36,
          title: "Чат 3",
        },
      ],
    };
  },
  methods: {
    updateSelectedChatId(index) {
      this.selectedChatId = this.chats[index].id;
    },
    updateSelectedUserId(index) {
      this.selectedUserId = this.users[index].id;
    },
  },
  computed: {},
  mounted() {
    axios
      .post("http://localhost:8081/auth/sign-in", {
        login: "admin",
        password: "admin",
      })
      .then((response) => console.log(response));
    axios
      .post("http://localhost:8081/auth/sign-up", {
        credentialsDTO: {
          login: "ilon@mail.ru",
          password: "12345",
        },
        detailsDTO: {
          firstName: "Ilon",
          lastName: "Mask",
          address: "sadsad",
        },
      })
      .then((response) => console.log(response));
  },
};
</script>

