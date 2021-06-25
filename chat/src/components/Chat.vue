<template>
  <div v-if="!isLoading" class="chats">
    <div class="sidebar left-bar">
      <div class="top-bar">
        <button
          v-if="isAdmin"
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
          :title="chat.title"
          :last-message="chat.messages[chat.messages.length - 1]"
          :key="chat.id"
          @click.native="updateSelectedChat(index)"
        ></ChatListItem>
      </ul>
    </div>
    <div class="sidebar right-bar">
      <div class="top-bar">
        <div class="chosen-chat-info">
          <h2 class="chosen-chat-info__title">{{ chatTitle }}</h2>
          <p class="chosen-chat-info__number-of-people">
            {{ numberOfMembers }} участников
          </p>
        </div>
      </div>
      <ul class="chat-history">
        <Message
          v-for="(message, index) in chats[selectedChat].messages"
          :message="message"
          :key="index"
        ></Message>
      </ul>
      <form class="message-form" @submit.prevent="sendMsg">
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
// import axios from 'axios'
import ChatListItem from "./ChatListItem.vue";
import Message from "./Message.vue";

export default {
  name: "Chat",
  components: {
    ChatListItem,
    Message,
  },
  data() {
    return {
      isLoading: false,
      userId: 1,
      isAdmin: true,
      selectedChat: 0,
      chats: [
        {
          id: 1,
          title: "Чат 1",
          numberOfMembers: 5,
          messages: [
            {
              id: 1,
              senderName: "Максим Романов",
              sendingDate: "12:28",
              content:
                "Здорово",
            },
            {
              id: 2,
              senderName: "Александр Аксёнов",
              sendingDate: "13:37",
              content:
                "Привет, как дела?",
            },
            {
              id: 1,
              senderName: "Максим Романов",
              sendingDate: "13:38",
              content:
                "Пушка!",
            }
          ],
        },
        {
          id: 2,
          title: "Чат 2",
          numberOfMembers: 2,
          messages: [
            {
              id: 1,
              senderName: "Иванов Роман",
              sendingDate: "5:32",
              content:
                "Здорово",
            },
            {
              id: 2,
              senderName: "Александр Аксёнов",
              sendingDate: "13:37",
              content:
                "Привет, как дела?",
            },
            {
              id: 1,
              senderName: "Иванов Роман",
              sendingDate: "13:38",
              content:
                "Вроде бы работает",
            }
          ],
        },
        {
          id: 2,
          title: "Тестовый чат",
          numberOfMembers: 15,
          messages: [
            {
              id: 1,
              senderName: "Аксёнов Александр",
              sendingDate: "5:32",
              content:
                "Hello",
            },
          ],
        },
      ],
      newMessage: "",
    };
  },
  methods: {
    updateSelectedChat(index) {
      this.selectedChat = index;
    },
    sendMsg() {
      console.log(this.newMessage);
    },
  },
  computed: {
    chatTitle() {
      return this.chats[this.selectedChat].title;
    },
    numberOfMembers() {
      return this.chats[this.selectedChat].numberOfMembers;
    },
  },
  //   mounted() {
  //         axios
  //           .get('https://jsonplaceholder.typicode.com/users')
  //           .then(response => this.chats = response.data.map(user=>{
  //             return {
  //                 id: user.id,
  //                 title: user.name,
  //                 numberOfMembers: user.id,
  //                 messages:[
  //                     {
  //                         id: 9,
  //                         senderName: "Багыр Делгер",
  //                         sendingDate: "2:28",
  //                         content: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Obcaecati voluptas cumque ipsum, iusto tempore porro animi consectetur odit aspernatur nihil, in itaque eum expedita fuga sed quas corporis quo eveniet eligendi nam nobis dolores eaque ducimus! Vitae nihil aperiam laboriosam fugit expedita quam fugiat enim perspiciatis placeat assumenda ipsa rem delectus eveniet neque accusamus, pariatur eius ipsam! Iure consequuntur sequi accusantium magni officia incidunt fuga maxime rerum, quaerat harum laboriosam repellendus libero, quam similique totam itaque fugit voluptate nam! Atque magnam voluptatum quis alias hic recusandae dolorem eveniet, reiciendis quaerat, quia impedit maiores, nostrum eaque neque ipsam quibusdam maxime nam eos exercitationem dolorum. Impedit necessitatibus nisi reprehenderit placeat incidunt provident, sed aliquam illum possimus consectetur qui doloribus est sunt nihil corporis inventore culpa delectus quis vel atque deserunt sapiente! Delectus dolore consequuntur voluptatibus sit velit architecto veniam neque non. Ducimus accusantium laborum nulla voluptas, quidem, quibusdam dolor expedita quo architecto ipsa deleniti tempora officia saepe totam nesciunt a quos nostrum. Eveniet sunt deleniti ab aspernatur, nemo odio veniam amet vero ex non repellat ad, facere id iusto, omnis iste porro voluptatum itaque perferendis. Soluta velit, itaque amet odit dolores fugit dolorem. Esse provident facilis praesentium incidunt sapiente, possimus soluta aliquid aperiam eveniet non architecto odio quos ipsum optio sequi. Sequi doloremque tenetur expedita iure cumque quos, libero consectetur sit inventore, nostrum maiores perspiciatis veritatis dolorem repudiandae dolore minima et cupiditate animi! Deleniti voluptate voluptas officiis eaque deserunt error quidem, fugiat dolorum ipsum? Error saepe aperiam, hic qui similique eaque fugit. Ratione harum deserunt, consequatur et obcaecati laboriosam earum impedit architecto mollitia eum, dolore, ex voluptatibus voluptate! Illo corrupti quaerat quibusdam facere praesentium harum nulla, deleniti velit cum ad ipsum qui voluptatibus vitae libero aut excepturi sit! Sed quae distinctio aliquam ratione id ducimus ipsum, animi unde modi eos repudiandae laborum officiis ab necessitatibus sit cumque fugit at quod, tempore dicta corrupti adipisci saepe delectus. In nobis beatae nemo, aliquam autem at voluptas aut iste doloremque optio nam vitae ipsa sit perferendis laborum assumenda amet consequuntur quibusdam minus. Itaque doloribus amet, alias explicabo corrupti iusto debitis molestias reprehenderit suscipit ea, minus esse necessitatibus quo ut repellendus temporibus nisi rerum hic ad? Accusantium iure quam blanditiis adipisci fuga id voluptatum sint delectus nobis ad possimus temporibus expedita magni corrupti distinctio ex dolor autem libero incidunt a vitae reiciendis, minima facere suscipit? Sit repellendus tenetur, id eos, cumque a cum laboriosam asperiores corporis eligendi modi dicta repellat voluptatibus obcaecati explicabo cupiditate possimus ullam, voluptatum labore? Exercitationem qui totam incidunt cum officia et odit laboriosam aliquid ratione rerum, voluptatem ipsum mollitia ut aspernatur obcaecati dolores? Autem reiciendis ipsam veniam earum id harum! Distinctio pariatur quasi in, non asperiores hic veritatis neque, fugit id libero officiis! Tempora, nostrum hic debitis facere alias doloribus placeat unde ratione esse. Magni aliquam necessitatibus architecto natus praesentium, illum quos reprehenderit perspiciatis aliquid perferendis veritatis beatae nihil fuga! Illo temporibus, sed deserunt, maiores unde, recusandae tenetur provident dolores ipsam cumque praesentium laudantium eveniet. Odit ipsum fugit atque odio nulla incidunt omnis, nihil quaerat reprehenderit, autem, beatae tempora rem deserunt veniam amet obcaecati vel nostrum magni nemo voluptate quod laboriosam sunt! Cupiditate magnam eligendi id assumenda, vitae repudiandae! Necessitatibus aliquid quod distinctio in laboriosam, voluptas, expedita ea optio, temporibus id recusandae? Atque, ipsum earum, suscipit corrupti quisquam eaque fuga voluptatem dolore adipisci dolorum natus magni molestias! Voluptatem, voluptatum sed laudantium quisquam aperiam recusandae quibusdam tenetur adipisci iusto maiores quis unde libero provident veniam tempora placeat consequuntur praesentium facere ipsa magnam nemo obcaecati culpa et! Unde natus odio dolores consequatur laborum sunt dolorum facere iste sapiente rem, cupiditate necessitatibus laudantium vel enim, minima harum adipisci esse ad cum eos libero? Error odio animi dolorem temporibus a, suscipit atque, beatae deserunt nulla tempora, modi ullam omnis cupiditate dolores similique impedit! Commodi cumque corporis tempore esse voluptatibus assumenda, asperiores ipsam magni sequi similique ut impedit nobis aliquid. Iusto autem quae fuga voluptatibus qui! Maiores itaque perspiciatis quisquam asperiores cumque molestiae, explicabo tenetur voluptatum doloremque, sit doloribus, ipsum praesentium aspernatur dolorem labore laborum dolores. Quo reiciendis corporis quibusdam nemo ullam porro nesciunt odit, consectetur blanditiis exercitationem dolorem beatae adipisci cupiditate, tempora, corrupti laborum nulla consequuntur iste minus modi. Itaque voluptatibus laudantium, harum sint magni hic quod repudiandae, quae saepe doloremque minus qui atque cupiditate assumenda iusto odit explicabo debitis excepturi maiores veritatis dolor adipisci quam sed similique! Quibusdam voluptatum qui repellat ea eaque asperiores consequatur unde cum? Reiciendis voluptatem assumenda maxime ullam corrupti architecto aperiam, exercitationem cum ut enim veritatis sapiente quaerat. Cumque atque maxime consequuntur nisi hic autem saepe officiis iste, odio qui. Impedit reiciendis voluptatum neque explicabo modi ipsam perferendis ipsa repellat ratione natus, repudiandae sit velit dolore fuga aut possimus sequi iste, error voluptas reprehenderit cumque! Accusantium quas nobis alias. Quisquam voluptate architecto eligendi temporibus ea molestiae impedit, quod atque! Sequi placeat numquam iste dignissimos autem sapiente voluptatem esse unde officia ratione voluptas sed, deleniti iure maiores in provident ab quae ad! Eum asperiores, dolores accusamus nulla expedita harum eos quia praesentium fugit doloribus similique repudiandae minus laudantium ullam nemo beatae unde modi fugiat, ipsa aliquid. Ea iusto repellendus sit vel cum quod, quo animi sed aperiam eum odit neque dicta labore est perspiciatis beatae itaque cumque dolorum dolores, perferendis odio aliquid accusantium exercitationem earum? Iusto vero voluptas ut amet blanditiis impedit delectus optio, qui eius aliquam, officiis quibusdam eligendi doloribus voluptates? Obcaecati soluta harum exercitationem, aspernatur tempore delectus aliquam in, iure iste, deserunt nemo minima corrupti. Qui cumque consectetur recusandae praesentium repellat assumenda rem iusto perspiciatis! Nemo distinctio enim delectus numquam reprehenderit cum minima, unde praesentium laudantium? Reprehenderit iure quibusdam molestias ab natus impedit, voluptatum laborum consequuntur. Beatae vitae aspernatur cum facilis maiores? Molestias aperiam provident assumenda. Exercitationem optio nesciunt ratione maxime. Excepturi quia deleniti ducimus, dolorum rem, reiciendis, dicta laudantium sit non enim totam nam necessitatibus. Accusantium autem velit laboriosam placeat natus vitae, ad cupiditate, rem dolores eius pariatur dolorem eos ipsa. Facere numquam veniam modi dolore velit sapiente, alias quas iste similique doloremque inventore, nisi veritatis culpa dolorum! Possimus?"
  //                     },
  //                     {
  //                         id: 10,
  //                         senderName: "Азамат Айталиев",
  //                         sendingDate: "13:37",
  //                         content: "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Obcaecati voluptas cumque ipsum, iusto tempore porro animi consectetur odit aspernatur nihil, in itaque eum expedita fuga sed quas corporis quo eveniet eligendi nam nobis dolores eaque ducimus! Vitae nihil aperiam laboriosam fugit expedita quam fugiat enim perspiciatis placeat assumenda ipsa rem delectus eveniet neque accusamus, pariatur eius ipsam! Iure consequuntur sequi accusantium magni officia incidunt fuga maxime rerum, quaerat harum laboriosam repellendus libero, quam similique totam itaque fugit voluptate nam! Atque magnam voluptatum quis alias hic recusandae dolorem eveniet, reiciendis quaerat, quia impedit maiores, nostrum eaque neque ipsam quibusdam maxime nam eos exercitationem dolorum. Impedit necessitatibus nisi reprehenderit placeat incidunt provident, sed aliquam illum possimus consectetur qui doloribus est sunt nihil corporis inventore culpa delectus quis vel atque deserunt sapiente! Delectus dolore consequuntur voluptatibus sit velit architecto veniam neque non. Ducimus accusantium laborum nulla voluptas, quidem, quibusdam dolor expedita quo architecto ipsa deleniti tempora officia saepe totam nesciunt a quos nostrum. Eveniet sunt deleniti ab aspernatur, nemo odio veniam amet vero ex non repellat ad, facere id iusto, omnis iste porro voluptatum itaque perferendis. Soluta velit, itaque amet odit dolores fugit dolorem. Esse provident facilis praesentium incidunt sapiente, possimus soluta aliquid aperiam eveniet non architecto odio quos ipsum optio sequi. Sequi doloremque tenetur expedita iure cumque quos, libero consectetur sit inventore, nostrum maiores perspiciatis veritatis dolorem repudiandae dolore minima et cupiditate animi! Deleniti voluptate voluptas officiis eaque deserunt error quidem, fugiat dolorum ipsum? Error saepe aperiam, hic qui similique eaque fugit. Ratione harum deserunt, consequatur et obcaecati laboriosam earum impedit architecto mollitia eum, dolore, ex voluptatibus voluptate! Illo corrupti quaerat quibusdam facere praesentium harum nulla, deleniti velit cum ad ipsum qui voluptatibus vitae libero aut excepturi sit! Sed quae distinctio aliquam ratione id ducimus ipsum, animi unde modi eos repudiandae laborum officiis ab necessitatibus sit cumque fugit at quod, tempore dicta corrupti adipisci saepe delectus. In nobis beatae nemo, aliquam autem at voluptas aut iste doloremque optio nam vitae ipsa sit perferendis laborum assumenda amet consequuntur quibusdam minus. Itaque doloribus amet, alias explicabo corrupti iusto debitis molestias reprehenderit suscipit ea, minus esse necessitatibus quo ut repellendus temporibus nisi rerum hic ad? Accusantium iure quam blanditiis adipisci fuga id voluptatum sint delectus nobis ad possimus temporibus expedita magni corrupti distinctio ex dolor autem libero incidunt a vitae reiciendis, minima facere suscipit? Sit repellendus tenetur, id eos, cumque a cum laboriosam asperiores corporis eligendi modi dicta repellat voluptatibus obcaecati explicabo cupiditate possimus ullam, voluptatum labore? Exercitationem qui totam incidunt cum officia et odit laboriosam aliquid ratione rerum, voluptatem ipsum mollitia ut aspernatur obcaecati dolores? Autem reiciendis ipsam veniam earum id harum! Distinctio pariatur quasi in, non asperiores hic veritatis neque, fugit id libero officiis! Tempora, nostrum hic debitis facere alias doloribus placeat unde ratione esse. Magni aliquam necessitatibus architecto natus praesentium, illum quos reprehenderit perspiciatis aliquid perferendis veritatis beatae nihil fuga! Illo temporibus, sed deserunt, maiores unde, recusandae tenetur provident dolores ipsam cumque praesentium laudantium eveniet. Odit ipsum fugit atque odio nulla incidunt omnis, nihil quaerat reprehenderit, autem, beatae tempora rem deserunt veniam amet obcaecati vel nostrum magni nemo voluptate quod laboriosam sunt! Cupiditate magnam eligendi id assumenda, vitae repudiandae! Necessitatibus aliquid quod distinctio in laboriosam, voluptas, expedita ea optio, temporibus id recusandae? Atque, ipsum earum, suscipit corrupti quisquam eaque fuga voluptatem dolore adipisci dolorum natus magni molestias! Voluptatem, voluptatum sed laudantium quisquam aperiam recusandae quibusdam tenetur adipisci iusto maiores quis unde libero provident veniam tempora placeat consequuntur praesentium facere ipsa magnam nemo obcaecati culpa et! Unde natus odio dolores consequatur laborum sunt dolorum facere iste sapiente rem, cupiditate necessitatibus laudantium vel enim, minima harum adipisci esse ad cum eos libero? Error odio animi dolorem temporibus a, suscipit atque, beatae deserunt nulla tempora, modi ullam omnis cupiditate dolores similique impedit! Commodi cumque corporis tempore esse voluptatibus assumenda, asperiores ipsam magni sequi similique ut impedit nobis aliquid. Iusto autem quae fuga voluptatibus qui! Maiores itaque perspiciatis quisquam asperiores cumque molestiae, explicabo tenetur voluptatum doloremque, sit doloribus, ipsum praesentium aspernatur dolorem labore laborum dolores. Quo reiciendis corporis quibusdam nemo ullam porro nesciunt odit, consectetur blanditiis exercitationem dolorem beatae adipisci cupiditate, tempora, corrupti laborum nulla consequuntur iste minus modi. Itaque voluptatibus laudantium, harum sint magni hic quod repudiandae, quae saepe doloremque minus qui atque cupiditate assumenda iusto odit explicabo debitis excepturi maiores veritatis dolor adipisci quam sed similique! Quibusdam voluptatum qui repellat ea eaque asperiores consequatur unde cum? Reiciendis voluptatem assumenda maxime ullam corrupti architecto aperiam, exercitationem cum ut enim veritatis sapiente quaerat. Cumque atque maxime consequuntur nisi hic autem saepe officiis iste, odio qui. Impedit reiciendis voluptatum neque explicabo modi ipsam perferendis ipsa repellat ratione natus, repudiandae sit velit dolore fuga aut possimus sequi iste, error voluptas reprehenderit cumque! Accusantium quas nobis alias. Quisquam voluptate architecto eligendi temporibus ea molestiae impedit, quod atque! Sequi placeat numquam iste dignissimos autem sapiente voluptatem esse unde officia ratione voluptas sed, deleniti iure maiores in provident ab quae ad! Eum asperiores, dolores accusamus nulla expedita harum eos quia praesentium fugit doloribus similique repudiandae minus laudantium ullam nemo beatae unde modi fugiat, ipsa aliquid. Ea iusto repellendus sit vel cum quod, quo animi sed aperiam eum odit neque dicta labore est perspiciatis beatae itaque cumque dolorum dolores, perferendis odio aliquid accusantium exercitationem earum? Iusto vero voluptas ut amet blanditiis impedit delectus optio, qui eius aliquam, officiis quibusdam eligendi doloribus voluptates? Obcaecati soluta harum exercitationem, aspernatur tempore delectus aliquam in, iure iste, deserunt nemo minima corrupti. Qui cumque consectetur recusandae praesentium repellat assumenda rem iusto perspiciatis! Nemo distinctio enim delectus numquam reprehenderit cum minima, unde praesentium laudantium? Reprehenderit iure quibusdam molestias ab natus impedit, voluptatum laborum consequuntur. Beatae vitae aspernatur cum facilis maiores? Molestias aperiam provident assumenda. Exercitationem optio nesciunt ratione maxime. Excepturi quia deleniti ducimus, dolorum rem, reiciendis, dicta laudantium sit non enim totam nam necessitatibus. Accusantium autem velit laboriosam placeat natus vitae, ad cupiditate, rem dolores eius pariatur dolorem eos ipsa. Facere numquam veniam modi dolore velit sapiente, alias quas iste similique doloremque inventore, nisi veritatis culpa dolorum! Possimus?"
  //                     }
  //                 ],
  //                 newMessage: '',
  //             }
  //           })).finally(()=>{
  //             this.isLoading = false
  //           });
  //   },
};
</script>

