<template>
  <tr>
    <td>{{ article.id }}</td>
    <router-link :to="'/hotplace/detail/' + article.id"><td>{{
      article.name
    }}</td></router-link>
    <td>{{ article.userId }}</td> 
    <td>{{ article.visitTime }}</td>
    <td>{{ article.writeTime }}</td>
  </tr>
</template>

<script>
export default {
  name: "HotplaceListItem",
  props: {
    article: Object,
  },
};
</script>

<style></style>
