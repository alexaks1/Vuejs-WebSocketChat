<template>
  <div class="auth">
    <div class="container">
      <h1 class="logo">WSC</h1>
      <form
        class="auth__form"
        action="user-info.html"
        method="POST"
        @submit.prevent="onSubmit"
      >
        <div class="auth__input-area">
          <label class="input-label auth__login-input-label" for="login"
            >Логин</label
          >
          <input
            v-model="login"
            class="auth-input"
            type="text"
            id="login"
            name="login"
            required
            minlength="5"
            maxlength="30"
          />
          <label class="input-label auth__password-input-label" for="password"
            >Пароль</label
          >
          <input
            v-model="password"
            class="auth-input"
            type="password"
            id="password"
            name="password"
            required
            minlength="5"
            maxlength="30"
          />
        </div>
        <p v-if="status.code >= 500" class="auth-error">
          {{ status.message }}
        </p>
        <button class="submit-button auth__submit" type="submit">Вход</button>
      </form>
      <!-- <div class="accent-text auth__signup-offer">
                <span class="auth__signup-offer-text">Нет учётной записи?</span>
                <a class="link" href="/sign-up.html">Зарегистрироваться</a>
            </div> -->
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Auth",
  data() {
    return {
      login: "",
      password: "",
      status: {
        code: 0,
        message: "",
      },
    };
  },
  methods: {
    isServerError() {
      if (this.error.code >= 500) {
        return true;
      }
    },
    onSubmit() {
      axios
        .post("http://localhost:8081/auth/sign-in", {
          login: this.login,
          password: this.password,
        })
        .then((response) => {
          this.status.code = response.status;
          if (this.status.code === 200){
            this.$router.push({name : 'chat'});
          }
          //   console.log(response);
        })
        .catch((error) => {
          // console.log(error.response)
          this.status.code = error.response.status;
          if (this.status.code >= 500) {
            this.status.message =
              "Внутренняя ошибка сервера: Проверьте логин и пароль";
          }
        });
    },
  },
  computed: {},
  mounted() {
    //     axios
    //       .post("http://localhost:8081/auth/sign-in", {
    //         email: "admin",
    //         password: "admin",
    //       })
    //       .then((response) => console.log(response));
    //     axios
    //       .post("http://localhost:8081/auth/sign-up", {
    //         credentialsDTO: {
    //           email: "ilon@mail.ru",
    //           password: "12345",
    //         },
    //         detailsDTO: {
    //           firstName: "Ilon",
    //           lastName: "Mask",
    //           address: "sadsad",
    //         },
    //       })
    //       .then((response) => console.log(response));
  },
};
</script>

