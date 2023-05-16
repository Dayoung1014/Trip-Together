<template>
  <div class="regist">
    <h1 class="underline">SSAFY 게시글 수정</h1>
    <div class="regist_form">
      <label for="userName">작성자</label>
      <input
        v-model="data.userName"
        type="text"
        id="userName"
        ref="userName"
      /><br />
      <label for="title">제목</label>
      <input v-model="data.title" type="text" id="title" ref="title" /><br />
      <label for="content">내용</label>
      <br />
      <textarea
        v-model="data.content"
        id="content"
        ref="content"
        cols="35"
        rows="5"
      ></textarea
      ><br />
      <button @click="checkValue">수정</button>
      <button @click="moveList">목록</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BoardModify",
  data() {
    return {
      data: {
        userName: "",
        content: "",
        title: "",
        no: "",
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
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userName.focus());
      err &&
        !this.data.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.data.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 registArticle 호출
      else this.modifyArticle();
    },
    modifyArticle() {
      console.log("글수정 하러가자!!!!");
      axios({
        method: "put",
        url: "http://localhost:8080/board",
        redirect: "follow",
        data: this.data,
      });
      this.moveList();
    },

    moveList() {
      console.log("글목록 보러가자!!!");
      setTimeout(() => {
        this.$router.push("/board");
      }, 100);
    },
  },

  created() {
    this.no = this.$route.params.no;
    console.log(this.no);

    axios({
      method: "get",
      url: `http://localhost:8080/board/${this.no}`,
      responseType: "json",
    })
      .then((data) => {
        this.data = data.data;
        console.log(data);
      })
      .catch((error) => console.log(error));
  },
};
</script>

<style></style>
