<template>
  <li class="chat-history__item message">
    <p class="message__info">
      <span class="message__sender">{{ senderName }} </span>
      <!-- <span class="message__time">{{ message.time }}</span> -->
    </p>
    <p class="message__text">{{ message.content }}</p>
  </li>
</template>

<script>
export default {
  name: "Message",
  props: {
    message: {
      type: Object,
    },
    users: {
      type: Array,
    },
  },
  computed: {
    senderName() {
      for (let i = 0; i < this.users.length; ++i) {
        if (this.users[i].id == this.message.sender) {
          return this.users[i].userDetails.firstName + " " + this.users[i].userDetails.lastName;
        }
      }
      return ""
    },
  },
};
</script>

