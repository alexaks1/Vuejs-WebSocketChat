<template>
  <li
    class="chat-info chat-list__item chat-list__item--active"
    @click="onClick"
  >
    <p class="chat-info__first-row chat-info-row">
      <span class="chat-info__title">{{ name }}</span>
      <!-- <span class="chat-info__last-msg-time">{{ lastMessage.sendingDate}}</span> -->
    </p>
    <!-- <p class="chat-info__second-row chat-info-row">
            <span class="chat-info__last-msg-sender">{{ lastMessage.senderName}}</span>
            <span>: </span>
            <span class="chat-info__last-msg-text">{{ messageFirstTwenty }}</span>
        </p> -->
  </li>
</template>

<script>
export default {
  name: "ChatListItem",
  props: {
    name: {
      type: String,
    },
  },
  data() {
    return {
      chats: [],
    };
  },
  methods: {
      onClick() {
          this.$emit('loadChatMessages')
      },
  },
};
</script>

