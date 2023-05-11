<template>
  <div class="regist">
    <h1 class="underline">SSAFY 게시글 수정</h1>
    <div class="regist_form">
      <label for="userid">작성자</label>
      <input type="text" id="userid" ref="userid" v-model="article.userid" /><br />
      <label for="subject">제목</label>
      <input type="text" id="subject" ref="subject" v-model="article.subject" /><br />
      <label for="content">내용</label>
      <br />
      <textarea id="content" ref="content" cols="35" rows="5" v-model="article.content" ></textarea><br />
      <button @click="checkValue">수정</button>
      <button @click="$router.push('/board')">목록</button>
    </div>
  </div>
</template>

<script>
export default {
  name: "BoardModify",
 
  data() {
    return {
      no : "", 
      article : {}
    } 
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 registArticle 호출
    checkValue() {
      console.log(this.article);
      // 사용자 입력값 체크하기
      // 작성자아이디, 제목, 내용이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.article.userid && ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userid.focus());
      err && !this.article.subject && ((msg = "제목 입력해주세요"), (err = false), this.$refs.subject.focus());
      err && !this.article.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 registArticle 호출
      else this.modifyArticle();
    },
    modifyArticle()  {
      // 비동기
      // TODO : 글번호에 해당하는 글정보 수정.
          console.log(this.article);
          this.no = this.$route.params.no; 
          fetch(`http://192.168.208.40:9999/vue/board`, {
              method: "PUT", 
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(this.article),
            })
            .then((response) => response.text()) // json으로 변환
            .then((data) => {
              this.article = data; // article 객체에 저장
            })
            .then(() => { this.$router.push('/board/list')}); // then() 함수에서의 비동기 처리가 완료된 후에 페이지 이동을 수행
    },
 
  },
  created() {
    // 비동기
    // TODO : 글번호에 해당하는 글정보 얻기.
        this.no = this.$route.params.no; 
          fetch(`http://192.168.208.40:9999/vue/board/${this.no}`, {
              method: "GET", 
              headers: { "Content-Type": "application/json" },
              body: JSON.stringify(this.articleno),
            })
            .then((response) => response.json()) // json으로 변환
            .then((data) => {
              this.article = data // article 객체에 저장
            });
  },
};
</script>

<style></style>
