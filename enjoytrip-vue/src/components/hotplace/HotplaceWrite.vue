<template>
  <div class="regist">
    <h1 class="underline">SSAFY 게시글 작성</h1>
    <div class="regist_form">
      <label for="name">제목</label>
      <input type="text" id="name" ref="name" v-model="data.name" /><br />

      <label for="img">사진</label>
      <input type="file" id="img" multiple="multiple" ref="img" />

      <label for="title">장소명(번호로)</label>
      <input
        type="text"
        id="dataNum"
        ref="dataNum"
        v-model="data.dataNum"
      /><br />

      <label for="content">설명</label>
      <br />
      <textarea
        id="content"
        ref="content"
        cols="35"
        rows="5"
        v-model="data.content"
      ></textarea
      ><br />

      <label for="title"><strong>방문 시간</strong></label>
      <input
        type="date"
        name="visitTime"
        ref="visitTime"
        id="visitTime"
        max="2023-12-31"
        min="2000-01-01"
        value="2023-05-12"
        v-model="data.visitTime"
      />

      <button @click="submitForm()">등록</button>
      <button @click="$router.push('/hotplace/list')">목록</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "HotplaceWrite",
  data() {
    return {
      data: {
        name: "",
        dataNum: "125406",
        visitTime: "",
        content: "",
        userId: "ssafy",
      },
    };
  },
  methods: {
    // 입력값 체크하기
    checkValue() {
      // 사용자 입력값 체크하기
      let err = true;
      let msg = "";
      !this.data.userId &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.data.name &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.name.focus());
      err &&
        !this.data.dataNum &&
        ((msg = "장소명 입력해주세요"),
        (err = false),
        this.$refs.dataNum.focus());
      err &&
        !this.data.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.registHotplace();
    },

    submitForm() {
      let formData = new FormData();
      formData.append("userId", "ssafy");
      formData.append("name", this.$refs.name.value);
      formData.append("dataNum", this.$refs.dataNum.value);
      formData.append("content", this.$refs.content.value);
      formData.append("visitTime", this.$refs.visitTime.value);
      let imgFiles = this.$refs.img.files;
      for (let i = 0; i < imgFiles.length; i++) {
        formData.append("files", imgFiles[i]);
      }

      console.log(this.data);
      axios({
        method: "post",
        url: "http://localhost:8080/hotplace/",
        data: formData,
        headers: { "Content-Type": "multipart/form-data" },
        redirect: "follow",
      });

      setTimeout(() => {
        this.$router.push("/hotplace");
      }, 100);
    },
  },
};
</script>

<style></style>
