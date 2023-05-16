<template>
  <div class="regist">
    <h1 class="underline">SSAFY 글 상세보기</h1>
    <div class="regist_form">
      <label> 글번호</label>
      <div class="view">{{ article.id }}</div>
      <label> 글제목</label>
      <div class="view">{{ article.title }}</div>
      <label> 작성자</label>
      <div class="view">{{ article.userName }}</div>
      <label> 조회수</label>
      <div class="view">{{ article.hit }}</div>
      <label> 작성시간</label>
      <div class="view">{{ article.writeTime }}</div>
      <label> 내용</label>
      <div class="view">{{ article.content }}</div>

      <div style="padding-top: 15px">
        <router-link class="btn" :to="'/board/modify/' + article.id"
          >수정</router-link
        >
        <router-link class="btn" :to="'/board/delete/' + article.id"
          >삭제</router-link
        >
        <router-link class="btn" :to="'/board/list/'">목록</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BoardView",

  data() {
    return {
      article: {},
    };
  },

  created() {
    this.no = this.$route.params.no;

    axios({
      method: "get",
      url: `http://localhost:8080/board/${this.no}`,
      responseType: "json",
    })
      .then((data) => {
        this.article = data.data;
      })
      .catch((error) => console.log(error));
  },
};
</script>

<style></style>
