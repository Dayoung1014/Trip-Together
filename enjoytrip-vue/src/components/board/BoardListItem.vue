<template>
  <tr>
    <td>{{ article.id }}</td>
    <router-link :to="'/board/detail/' + article.id">{{
      article.title
    }}</router-link>
    <td>{{ article.content }}</td>
    <td>{{ article.userName }}</td>
    <td>{{ article.hit }}</td>
    <td>{{ article.writeTime }}</td>
  </tr>
</template>

<script>
export default {
  name: "BoardListItem",
  props: {
    article: Object,
  },
};
</script>

<style></style>
