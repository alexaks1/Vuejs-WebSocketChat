<template>
  <li class="chat-history__item message">
    <p class="message__info">
      <span class="message__sender">{{ message.senderName }} </span>
      <span class="message__time">{{ message.sendingDate }}</span>
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
      required: true,
    },
  },
  computed: {
    messageFirstTwenty() {
      return this.lastMessage.content.substring(0, 40);
    },
  },
};
</script>

