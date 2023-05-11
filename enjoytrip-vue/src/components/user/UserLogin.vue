<template>
  <div class="regist">
    <h1 class="underline">로그인</h1>
    <div class="regist_form">
      <label for="id">ID</label>
      <input type="text" id="id" v-model="id" /><br />
      
      <label for="pwd">Password</label>
      <input type="text" id="pwd" v-model="pwd" /><br />
      <button @click="checkValue">로그인</button>   
    </div>
  </div>
</template>

<script>
export default {
  name: "BoardWrite",
  data() {
    return {
      id:"",
      pwd:"", 
    };
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 registArticle 호출
    checkValue() {
      // 사용자 입력값 체크하기
      // 작성자아이디, 제목, 내용이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.id && ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.id.focus());
      err && !this.pwd && ((msg = "비밀번호를 입력해주세요"), (err = false), this.$refs.pwd.focus()); 

      if (!err) alert(msg); 
      else this.login();
    },
    login() {
      var data = {
        id: this.id,
        pwd: this.pwd, 
      }
      // 비동기
      // TODO : 글번호에 해당하는 글정보 등록.
      console.log(data);
      fetch("http://localhost:8080/user/login", {
        method: "POST",
        headers: {
        'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      })
      .then((response) => response.json())
      .then((result) => { 
        if (result.status === 'OK') {
          console.log('로그인 성공');
          //this.$router.push('/');
        } else {
          console.log('로그인 실패');
          alert("로그인 실패");
        }
        });
      //.then(() => { this.$router.push('/board/list')}); // then() 함수에서의 비동기 처리가 완료된 후에 페이지 이동을 수행
    }, 
  },
};
</script>

<style></style>
