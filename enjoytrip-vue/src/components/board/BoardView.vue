<template>
  <div class="regist" style="border: none; "> 
    <div class="regist_form" style="border: none;"> 
      <div class="view text-start" style="border: none; font-size: 40px">{{ article.title }}</div>
          <div class="view text-start" style="border: none; font-size: 20px">
            <b-icon icon="person-fill" font-scale="1" ></b-icon> {{ article.userName }}</div>
      <div class="view text-end" style="border: none; font-size: 20px">
        <b-icon icon="pencil" font-scale="1"></b-icon> {{ article.writeTime }}</div>
      <div class="view" style="border: none; font-size: 20px"> {{ article.content }}</div>

      <div style="padding-top: 15px" class="col-6 offset-6 d-flex justify-content-end" > 
        <b-button v-if="this.manager === 1"  class="btn"  @click="$router.push('/board/modify/' + article.id)" variant="outline-dark" style="width:10%">수정</b-button>
        <b-button v-if="this.manager === 1"  class="btn"  @click="$router.push('/board/delete/' + article.id)" variant="outline-dark" style="width:10%">삭제</b-button>
        <b-button class="btn"  @click="$router.push('/board/list/')" variant="outline-dark" style="width:10%">목록</b-button>
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
      manager:{}
    };
  },

  created() {
            // 현재 세션 스토리지에서 "vuex" 키의 값을 가져오기
            const vuexData = sessionStorage.getItem("vuex");
    const userData = JSON.parse(vuexData);
    console.log(userData)
    // userInfo의 id 값 가져오기
    this.manager = userData.memberStore.userInfo.manager;
    console.log(this.manager)

    this.no = this.$route.params.no;

    axios({
      method: "get",
      url: `http://localhost:8080/board/${this.no}`,
      responseType: "json",
    })
      .then((data) => {
        this.article = data.data;
        console.log(this.article)
      })
      .catch((error) => console.log(error));
  },
};
</script>

<style></style>
