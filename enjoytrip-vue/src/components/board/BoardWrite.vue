<template>
  <div class="regist">
    <h1 class="underline">SSAFY 게시글 작성</h1>
    <div class="regist_form">
      <label for="userid">작성자</label>
      <input type="text" id="userid" v-model="userid" /><br />
      <label for="subject">제목</label>
      <input type="text" id="subject" v-model="subject" /><br />
      <label for="content">내용</label>
      <br />
      <textarea id="content" v-model="content" cols="35" rows="5"></textarea><br />
      <button @click="checkValue">등록</button> 
      <router-link to="/board/list"><button>목록</button></router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "BoardWrite",
  data() {
    return {
      userid:"",
      subject:"",
      content:""
    };
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 registArticle 호출
    checkValue() {
      // 사용자 입력값 체크하기
      // 작성자아이디, 제목, 내용이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.userid && ((msg = "작성자 입력해주세요"), (err = false), this.$refs.userid.focus());
      err && !this.subject && ((msg = "제목 입력해주세요"), (err = false), this.$refs.subject.focus());
      err && !this.content && ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 registArticle 호출
      else this.registArticle();
    },
    registArticle() {
      var data = {
        userid: this.userid,
        subject: this.subject,
        content: this.content,
      }
      // 비동기
      // TODO : 글번호에 해당하는 글정보 등록.
      console.log(data);
      fetch("http://192.168.208.40:9999/vue/board", {
        method: "POST",
        headers: {
        'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      })
      .then((response) => response.text())
      .then((result) => console.log(result))
      .then(() => { this.$router.push('/board/list')}); // then() 함수에서의 비동기 처리가 완료된 후에 페이지 이동을 수행
    }, 
  },
};
</script>

<style></style>
