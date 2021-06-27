<template>
  <li class="chat-info chat-list__item chat-list__item--active">
        <p class="chat-info__first-row chat-info-row">
            <span class="chat-info__title">{{ title }}</span>
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
  name: 'ChatListItem',
  props: {
    title: {
        type: String
    },
    // lastMessage: {
    //     type: Object
    // }
  },
  data(){
      return{
          chats: []
      }
  },
//   computed:{
//         messageFirstTwenty(){
//             return this.lastMessage.content.substring(0, 40)
//         }
//     },
}
</script>

