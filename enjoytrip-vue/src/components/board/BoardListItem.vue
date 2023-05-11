<template>
  <tr v-on:click="clickView(article.id)">
    <td>{{article.id}}</td>
    <td>{{article.title}}</td>
    <td>{{article.userId}}</td>
    <td>{{article.hit}}</td>
    <td>{{article.writeTime}}</td>
  </tr>
</template>

<script>
export default {
  name: "BoardListItem",
  props:{
    article : Object
  },
methods: { 
clickView(id) {
  this.$router.push({
    path: `/board/detail/${id}`
  });
},
},
};
</script>

<style></style>
