<template>
  <ul>
    <AdminListItem
      v-for="(item, index) in items"
      :key="index"
      :user="item"
    ></AdminListItem>
  </ul>
</template>

<script>
import AdminListItem from "./AdminListItem.vue";
export default {
  name: "AdminList",
  components: {
    AdminListItem,
  },
  props: {
    items: {
      type: Array,
    },
  },
};
</script>

