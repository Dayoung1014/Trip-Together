<template>
  <div class="regist">
    <h1 class="underline">SSAFY 게시글 작성</h1>
    <div class="regist_form">
      <label for="userName">작성자</label>
      <input type="text" id="userName" ref="userName" v-model="data.userName" /><br />
      <label for="title">제목</label>
      <input type="text" id="title" ref="title" v-model="data.title" /><br />
      <label for="content">내용</label>
      <br />
      <textarea id="content" ref="content" cols="35" rows="5" v-model="data.content"></textarea
      ><br />
      <button @click="checkValue">등록</button>
      <button @click="$router.push('/board/list')">목록</button>
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
        userName: "",
        userId: "ssafy",
      },
    };
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 registArticle 호출
    checkValue() {
      // 사용자 입력값 체크하기
      // 작성자아이디, 제목, 내용이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.data.userName &&
        ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userName.focus());
      err &&
        !this.data.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.data.content &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

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
