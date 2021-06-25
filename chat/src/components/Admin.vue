<template>
  <div class="admin">
    <div class="admin__container">
      <h1>AdminPanel</h1>
      <form class="admin__input-area" @submit.prevent="addUser">
        <div class="admin__input-and-label">
          <label>ID чата</label>
          <input
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
            v-model="selectedUserId"
            type="text"
            name="selected-user-id"
            required
            minlength="5"
            maxlength="30"
          />
        </div>
        <button class="admin__add-btn">Добавить</button>
        <span v-bind:class="{ 'success-message': !result.isError }">{{
          result.message
        }}</span>
      </form>
      <div class="chats-users-lists">
        <div class="list-and-title">
          <h2>Чаты</h2>
          <AdminList :items="chats"></AdminList>
        </div>
        <div class="list-and-title">
          <h2>Пользователи</h2>
          <AdminList :items="users"></AdminList>
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
      result: {
        isError: false,
        message: "Успешно!",
      },
      users: [
        {
          id: 1,
          firstName: "Багыр",
          lastName: "Делгер",
        },
        {
          id: 2,
          firstName: "Азамат",
          lastName: "Айталиев",
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
      ],
    };
  },
  methods: {},
  computed: {},
  mounted() {
    axios
      .post("http://localhost:8081/auth/sign-in", {
        email: "admin",
        password: "admin",
      })
      .then((response) => console.log(response));
    axios
      .post("http://localhost:8081/auth/sign-up", {
        credentialsDTO: {
          email: "ilon@mail.ru",
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

