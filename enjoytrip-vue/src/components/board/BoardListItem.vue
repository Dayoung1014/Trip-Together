<template>
  <tr v-on:click="clickView(article.articleno)">
    <td>{{article.articleno}}</td>
    <td>{{article.subject}}</td>
    <td>{{article.userid}}</td>
    <td>{{article.hit}}</td>
    <td>{{article.regtime}}</td>
  </tr>
</template>

<script>
export default {
  name: "BoardListItem",
  props:{
    article : Object
  },
methods: { 
clickView(no) {
  this.$router.push({
    path: `/board/detail/${no}`
  });
},
},
};
</script>

<style></style>
