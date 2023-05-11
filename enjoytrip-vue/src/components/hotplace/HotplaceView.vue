<template>
  <div class="regist">
    <h1 class="underline">SSAFY 글 상세보기</h1>
    <div class="regist_form">
      <label> 번호</label>
      <div class="view">{{ article.id }}</div>
      <label> 제목</label>
      <div class="view">{{ article.name }}</div>
      <label> 작성자</label>
      <div class="view">{{ article.userId }}</div>
      <label> 내용</label>
      <div class="view">{{ article.content }}</div>
      <label> 방문일</label>
      <div class="view">{{ article.visitTime }}</div>
      <label> 작성일</label>
      <div class="view">{{ article.writeTime }}</div>
      
      <div style="padding-top: 15px">
        <router-link class="btn" :to="'/hotplace/update/' + article.id"
          >수정</router-link
        >
        <router-link class="btn" :to="'/hotplace/delete/' + article.id"
          >삭제</router-link
        >
        <router-link class="btn" :to="'/hotplace/list/'">목록</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "HotplaceView",

  data() {
    return {
      article: {},
    };
  },

  created() {
    this.no = this.$route.params.no;

    axios({
      method: "get",
      url: `http://localhost:8080/hotplace/${this.no}`,
      responseType: "json",
    })
      .then((response) => {
        this.article = response.data.resdata; 
        console.log(this.article)
      })
      .catch((error) => console.log(error));
  },
};
</script>

<style></style>
