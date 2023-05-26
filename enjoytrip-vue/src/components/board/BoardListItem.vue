<template>
  <tr @click="goToDetail(article.id)">
    <td>{{ article.id }}</td>
    <td>{{article.title}}</td>
    <td>{{ article.userName }}</td> 
    <td>{{ article.writeTime }}</td>
  </tr>
</template>

<script>
export default {
  name: "BoardListItem",
  props: {
    article: Object,
  },
  methods : {
    goToDetail(id) {
      console.log(id)
      this.$router.push('/board/detail/' + id);
    }
  }
};
</script>

<style></style>
