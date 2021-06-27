<template>
  <div class="admin">
    <div class="admin__container">
      <button class="admin__back-btn" @click="$router.push({ name: 'chats' })">
        Назад
      </button>
      <h1>AdminPanel</h1>
      <form class="admin__input-area" @submit.prevent="addUserToChat">
        <div class="admin__input-and-label">
          <label>ID чата</label>
          <input
            class="admin__input"
            v-model="selectedChatId"
            type="text"
            name="selected-chat-id"
            required
            minlength="1"
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
            minlength="1"
          />
        </div>
        <button class="admin__add-user-to-chat-btn">Добавить</button>
        <span
          v-if="result.message"
          v-bind:class="{
            'success-message': !result.isError,
            'error-message': result.isError,
          }"
          >{{ result.message }}</span
        >
      </form>
      <div class="chats-users-lists">
        <div class="list-and-title">
          <div class="list-and-title__title">
            <h2>Чаты</h2>
            <button class="add-to-list" @click="addChatModalIsOpened = true">
              +
            </button>
          </div>
          <AdminList
            :items="chats"
            @selectVariant="updateSelectedChatId"
          ></AdminList>
        </div>
        <div class="list-and-title">
          <div class="list-and-title__title">
            <h2>Пользователи</h2>
            <button class="add-to-list" @click="addUserModalIsOpened = true">
              +
            </button>
          </div>
          <AdminList
            :items="users"
            @selectVariant="updateSelectedUserId"
          ></AdminList>
        </div>
      </div>
    </div>
    <div v-if="addUserModalIsOpened" class="modal">
      <div class="modal-content">
        <span class="close-modal" @click="addUserModalIsOpened = false">X</span>
        <div class="registration">
          <h2>Регистрация нового пользователя</h2>
          <form class="registration__form" @submit.prevent="signUpUser">
            <div class="registration__input-area">
              <label
                class="input-label registration__login-input-label"
                for="login"
                >Логин</label
              >
              <input
              v-model="newUser.login"
                class="auth-input"
                type="text"
                id="login"
                name="login"
                required
                minlength="5"
                maxlength="30"
              />
              <label
                class="input-label accent-text registration__name-input-label"
                for="name"
                >Имя</label
              >
              <input
              v-model="newUser.firstName"
                class="auth-input"
                type="text"
                id="name"
                name="name"
                required
                minlength="1"
                maxlength="30"
              />
              <label
                class="
                  accent-text
                  input-label
                  registration__surname-input-label
                "
                for="surname"
                >Фамилия</label
              >
              <input
              v-model="newUser.lastName"
                class="auth-input"
                type="text"
                id="surname"
                name="surname"
                required
                minlength="1"
                maxlength="30"
              />
              <label
                class="
                  accent-text
                  input-label
                  registration__password-input-label
                "
                for="password"
                >Пароль</label
              >
              <input
              v-model="newUser.password"
                class="auth-input"
                type="password"
                id="password"
                name="password"
                required
                minlength="5"
                maxlength="30"
              />
            </div>
            <button class="submit-button auth__submit" type="submit">
              Зарегистрировать
            </button>
          </form>
        </div>
      </div>
    </div>
    <div v-if="addChatModalIsOpened" class="modal">
      <div class="modal-content">
        <span class="close-modal" @click="addChatModalIsOpened = false">X</span>
        <div class="registration">
          <h2>Создание нового чата</h2>
          <form class="registration__form" @submit.prevent="createChat">
            <div class="registration__input-area">
              <label
                class="input-label registration__login-input-label"
                for="chat-title"
                >Название чата</label
              >
              <input
                v-model="newChat.title"
                class="auth-input"
                type="text"
                id="chat-title"
                name="chat-title"
                required
                minlength="5"
                maxlength="30"
              />
            </div>
            <button class="submit-button auth__submit" type="submit">
              Создать
            </button>
          </form>
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
      role: null,
      userId: null,
      addUserModalIsOpened: false,
      addChatModalIsOpened: false,
      selectedChatId: null,
      selectedUserId: null,
      newChat: {
        title: "Чат 1",
        status: {
          isSubmitted: false,
          isError: false,
          message: "",
        },
      },
      newUser: {
        firstName: "",
        lastName: "",
        login: "",
        password: "",
        status: {
          isSubmitted: false,
          isError: false,
          message: "",
        },
      },
      result: {
        isSubmitted: false,
        isError: false,
        message: "",
      },
      users: [],
      chats: [],
    };
  },
  methods: {
    updateSelectedChatId(index) {
      this.selectedChatId = this.chats[index].id;
    },
    updateSelectedUserId(index) {
      this.selectedUserId = this.users[index].id;
    },
    addUserToChat() {
      axios
        .post(
          `http://localhost:8081/chats/${this.selectedChatId}/${this.selectedUserId}`,
          null,
          { params: { userId: this.userId } }
        )
        // .then((response) => {
        // console.log(response);
        .then(() => {
          this.result.isError = false;
          this.result.message = "Успешно!";
        })
        // .catch((error) => {
        // console.log(error.response);
        .catch(() => {
          this.result.isError = true;
          this.result.message = "Ошибка!";
        });
    },
    createChat() {
      axios
        .post(
          `http://localhost:8081/chats/`,
          {
            name: this.newChat.title,
          },
          { params: { userId: this.userId } }
        )
        .then(() => {
          this.newChat.status.isSubmitted = true;
          this.newChat.status.isError = false;
          this.newChat.status.message = "Успешно";
        })
        .catch(() => {
          this.newChat.status.isSubmitted = true;
          this.newChat.status.isError = true;
          this.newChat.status.message = "Ошибка";
        });
    },
    signUpUser() {
      axios
        .post(`http://localhost:8081/auth/sign-up`, {
          "credentialsDTO": {
            login: this.newUser.login,
            password: this.newUser.password,
          },
          "detailsDTO": {
            id: "0",
            firstName: this.newUser.firstName,
            lastName: this.newUser.lastName,
            role: "USER",
          },
        })
        .then(() => {
          this.newChat.status.isSubmitted = true;
          this.newChat.status.isError = false;
          this.newChat.status.message = "Успешно";
        })
        .catch(() => {
          this.newChat.status.isSubmitted = true;
          this.newChat.status.isError = true;
          this.newChat.status.message = "Ошибка";
        });
    },
  },
  computed: {},
  mounted() {
    // this.role = sessionStorage.role;
    this.userId = sessionStorage.userId;
    axios
      .get("http://localhost:8081/chats/", { params: { userId: this.userId } })
      .then((response) => {
        console.log(response);
        // if (response.data.length !== 0)
        this.chats = response.data.map((chat) => {
          return {
            id: chat.id,
            title: chat.name,
            users: chat.users,
          };
        });
        //console.log(this.chats)
      });
    axios
      .get("http://localhost:8081/auth/users", {
        params: { userId: this.userId },
      })
      .then((response) => {
        console.log(response);
        this.users = response.data.map((user) => {
          return {
            id: user.detailsDTO.id,
            firstName: user.detailsDTO.firstName,
            lastName: user.detailsDTO.lastName,
            role: user.detailsDTO.role,
            // login: user.login,
            // password: user.password,
          };
        });
        //console.log(this.users)
      });
  },
};
</script>