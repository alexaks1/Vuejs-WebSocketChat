<template>
  <li class="admin__list-item" @click="onClick">
    {{ user }}
  </li>
</template>

<script>
export default {
  name: "AdminListItem",
  props: {
    user: {
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

