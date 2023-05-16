<template>
  <div class="regist">
    <h1 class="underline">SSAFY 게시글 작성</h1>
    <div class="regist_form">
      <label for="userid">작성자</label>
      <input type="text" id="userId" ref="userId" value="this.article.userId" v-model="article.userId" /><br />

      <label for="subject">제목</label>
      <input type="text" id="name" ref="name" v-model="article.name" /><br />

      <!-- <label for="subject">시도</label>
      <select id="search-area-sel" class="form-select" onchange="changeAreaSel()">
        <option value="-1" selected>시도선택</option>
      </select>

      <label for="subject">구군</label>
      <select id="search-area-sel" class="form-select" onchange="changeAreaSel()">
        <option value="-1" selected>구군선택</option>
      </select> -->

      <label for="title">장소명(번호로)</label>
      <input
        type="text"
        id="dataNum"
        ref="dataNum"
        v-model="article.dataNum"
      /><br />
      <!-- <input type="text" class="form-control" name="place_name" id="place_name" placeholder="하단에서 선택" readonly>
      <input type="text" class="form-control" name="dataNum" id="dataNum" hidden> -->

      <label for="content">설명</label>
      <br />
      <textarea
        id="content"
        ref="content"
        cols="35"
        rows="5"
        v-model="article.content"
      ></textarea
      ><br />

      <label for="title"><strong>방문 시간</strong></label>
      <input
        type="date"
        name="visitTime"
        id="visitTime"
        max="2023-12-31"
        min="2000-01-01"
        value="2023-05-12"
        v-model="article.visitTime"
      />

      <button @click="checkValue">등록</button>
      <button @click="$router.push('/hotplace/list')">목록</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "HotplaceModify",
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
        })
        .catch((error) => console.log(error));
    },
  methods: {

    // 입력값 체크하기
    checkValue() {
      // 사용자 입력값 체크하기
      let err = true;
      let msg = "";
      !this.article.userId &&
        ((msg = "작성자 입력해주세요"),
        (err = false),
        this.$refs.userId.focus());
      err &&
        !this.article.name &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.name.focus());
      err &&
        !this.article.dataNum &&
        ((msg = "장소명 입력해주세요"),
        (err = false),
        this.$refs.dataNum.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else this.registHotplace();
    },
    registHotplace() {
      axios({
        method: "post",
        url: "http://localhost:8080/hotplace/",
        data: this.article,
        redirect: "follow",
      });

      this.$router.push("/hotplace");
      window.location.reload(true);
    },
  },
};
</script>

<style></style>
