<template>
  <div style="margin-top: 50px; /* 원하는 간격 설정 */"> 
    <div v-if="articles.length">
      <table id="article-list">
        <colgroup>
          <col style="width: 5%" />
          <col style="width: 10%" />
          <col style="width: 40%" />
          <col style="width: 10%" />
          <col style="width: 15%" />
          <col style="width: 15%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>비밀글</th>
            <th>제목</th>
            <th>작성자</th>
            <th>시작일</th> 
            <th>종료일</th> 
          </tr>
        </thead>
        <tbody>
          <plan-list-item
            v-for="article in articles"
            :key="article.id"
            :article="article" 
          ></plan-list-item>
        </tbody>
      </table>
    </div>
    <div v-else class="text-center">게시글이 없습니다.</div>
  </div>
</template>

<script>
import axios from "axios";
import PlanListItem from "./PlanListItem.vue";

export default {
  name: "PlanList",
  components: {
    PlanListItem,
  },
  data() {
    return {
      user_id : "",
      articles: {},
    };
  },
  created() { 
    axios.get(`http://localhost:8080/plan/`)
  .then(response => {  
    this.articles = response.data;
    console.log(this.articles)
  })
  .catch(error => {
    console.error(error);
  });
  },

};
</script>

<style>

table { 
    width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    background-color: white;
    border-bottom: 1px solid #444444;
    padding: 10px;
  }
  </style>
