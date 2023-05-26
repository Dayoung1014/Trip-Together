<template>
  <div class="regist">
    <!-- <h1 class="underline">Notice 작성</h1> -->
    <div class="regist_form">
      <label for="title">제목</label>
      <input
        style="width: 100%"
        type="text"
        id="title"
        ref="title"
        v-model="data.title"
      /><br />
      <label for="content">내용</label>
      <br />
      <textarea
        id="content"
        ref="content"
        cols="35"
        rows="5"
        v-model="data.content"
      ></textarea
      ><br />
      <div
        style="padding-top: 15px"
        class="col-6 offset-6 d-flex justify-content-end"
      >
        <b-button
          @click="checkValue"
          class="btn"
          variant="outline-dark"
          style="width: 10%"
          >등록</b-button
        >
        <b-button
          @click="$router.push('/board/list')"
          class="btn"
          variant="outline-dark"
          style="width: 10%"
          >목록</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "BoardWrite",
  data() {
    return {
      data: {
        content: "",
        title: "",
        userId: "",
      },
    };
  },
  created() {
    // 현재 세션 스토리지에서 "vuex" 키의 값을 가져오기
    const vuexData = sessionStorage.getItem("vuex");
    const userData = JSON.parse(vuexData);
    // userInfo의 id 값 가져오기
    this.data.userId = userData.memberStore.userInfo.id;

    if (userData.memberStore.userInfo.manager !== 1) {
      alert("관리자가 아닙니다.");
      this.$router.push("/board/list");
      return;
    }

    console.log(userData.memberStore.userInfo);
    console.log(this.data);
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 registArticle 호출
    checkValue() {
      // 사용자 입력값 체크하기
      // 작성자아이디, 제목, 내용이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.data.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.data.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 registArticle 호출
      else this.registArticle();
    },
    registArticle() {
      axios({
        method: "post",
        url: "http://localhost:8080/board/",
        data: this.data,
        redirect: "follow",
      });

      setTimeout(() => {
        this.$router.push("/board");
      }, 100);
    },

    moveList() {
      console.log("글목록 보러가자!!!");
    },
  },
};
</script>

<style></style>
