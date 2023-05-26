<template>
  <div class="regist">
    <!-- <h1>SSAFY 글 삭제</h1> -->
    <div>삭제중....</div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "BoardDelete",
  data() {
    return { 
      userId:{}
    };
  },
  created() {
    this.no = this.$route.params.no;

    // 현재 세션 스토리지에서 "vuex" 키의 값을 가져오기
    const vuexData = sessionStorage.getItem("vuex");
    const userData = JSON.parse(vuexData);
    // userInfo의 id 값 가져오기
    this.manager = userData.memberStore.userInfo.manager;

    if (this.manager !== 1) {
    alert("관리자가 아닙니다.");
    this.$router.push("/board/list");
    return;
  }
  else {
    axios({
      method: "delete",
      url: `http://localhost:8080/board/${this.no}`,
    });
    setTimeout(() => {
      this.$router.push("/board");
    }, 100);
  }

  },
};
</script>

<style></style>
