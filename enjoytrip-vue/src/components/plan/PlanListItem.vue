<template>
  <tr @click="goToDetail(article.id)">
    <td>{{ article.id }}</td>  
    <td> 
      <b-icon :icon="article.isPublic === '1' ? 'unlock' : 'lock'"></b-icon>
    </td>
  
    <td>{{article.name}}</td> 
    <td>{{ article.userId }}</td> 
    <td>{{ article.startDate }}</td>
    <td>{{ article.endDate }}</td>
  </tr>
</template>

<script>
export default {
  name: "PlanListItem",
  props: {
    article: Object,
  },

  methods : {
    goToDetail(id) {
      console.log(id)
      this.$router.push('/plan/detail/' + id);
    }
  }
};
</script>

<style></style>
