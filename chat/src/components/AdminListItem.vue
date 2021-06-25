<template>
  <li>
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
  },
};
</script>

