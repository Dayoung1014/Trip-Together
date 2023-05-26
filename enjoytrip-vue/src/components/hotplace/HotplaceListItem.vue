<template>
  <tr @click="goToDetail(article.id)">
    <td>{{ article.id }}</td>
    <td>{{article.name}}</td> 
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
  methods : {
    goToDetail(id) {
      console.log(id)
      this.$router.push('/hotplace/detail/' + id);
    }
  }
};
</script>

<style></style>
