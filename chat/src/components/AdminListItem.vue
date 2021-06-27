<template>
  <li class="admin__list-item" @click="onClick">
    {{ item }}
  </li>
</template>

<script>
export default {
  name: "AdminListItem",
  props: {
    item: {
      type: Object,
    },
    index: {
      type: Number,
    },
  },
  methods:{
      onClick(){
          this.$emit('selectVariant', this.index);
      }
  }
};
</script>

