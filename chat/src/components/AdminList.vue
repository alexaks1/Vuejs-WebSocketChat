<template>
  <ul class="admin__list">
    <AdminListItem
      @selectVariant="selectVariant"
      v-for="(item, index) in items"
      :key="index"
      :item="item"
      :index="index"
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
  data(){
      return{
          selectedVariant: null,
      }
  },
  props: {
    items: {
      type: Array,
    },
  },
  methods:{
      selectVariant(index){
          this.selectedVariant = index;
          this.$emit("selectVariant",  this.selectedVariant);
      }
  },
};
</script>

