<template>
  <div class="regist">
    <h1 class="underline">SSAFY 글 상세보기</h1>
    <div class="regist_form">
      <label> 글번호</label>
      <div class="view">{{article.articleno}}</div>
      <label> 글제목</label>
      <div class="view">{{article.subject}}</div>
      <label> 작성자</label>
      <div class="view">{{article.userid}}</div>
      <label> 조회수</label>
      <div class="view">{{article.hit}}</div>
      <label> 작성시간</label>
      <div class="view">{{article.regtime}}</div>
      <label> 내용</label>
      <div class="view">{{article.content}}</div>

      <div style="padding-top: 15px">
       <a href="#" @click="$router.push(`/board/modify/${article.articleno}`)" class="btn">수정</a>
        <a href="#" @click="$router.push(`/board/delete/${article.articleno}`)" class="btn">삭제</a>
        <a href="#" @click="$router.push('/board')" class="btn">목록</a>
      </div>
    </div>
  </div>
</template>

<script> 
export default {
  name: "BoardView",
  props:{ 
    //no: Number // 이전 페이지에서 전달된 글 번호
  },
  data() {
    return {
      no : "", // 이전 페이지에서 전달된 글 번호
      article : {} // fetch 된 데이터 저장할 빈 객체
    };
  },
  created() {
     this.no = this.$route.params.no;
      
    // 비동기
    // TODO : 글번호에 해당하는 글정보 얻기. 
      fetch(`http://192.168.208.40:9999/vue/board/${this.no}`, {
        method: "GET", 
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(this.articleno),
      })
      .then((response) => response.json()) // json으로 변환
      .then((data) => {
        this.article = data; // article 객체에 저장
      });
  },
  methods : {
    selectView() {
      
    }
  }
};
</script>

<style></style>
