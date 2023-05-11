<template>
  <div class="regist">
    <h1 class="underline">회원가입</h1>
    <div class="regist_form">
      <label for="name">이름</label>
      <input type="text" id="name" v-model="name" /><br />

      <label for="id">아이디</label>
      <input type="text" id="id" v-model="id" /><br />
      
      <label for="pwd">비밀번호</label>
      <input type="text" id="pwd" v-model="pwd" /><br />

      <label for="pwdcheck">비밀번호 확인</label>
      <input type="text" id="pwdcheck" v-model="pwdcheck" /><br />

      <label for="email">이메일</label>
      <input type="text" id="email" v-model="email" /><br />

      <button @click="checkValue">등록</button>   
    </div>
  </div>
</template>

<script>
export default {
  name: "BoardWrite",
  data() {
    return {
      name:"",
      id:"",
      pwd:"", 
      pwdcheck:"",
      email:""
    };
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 registArticle 호출
    checkValue() { 
      let err = true;
      let msg = "";
      !this.name && ((msg = "이름을 입력해주세요"), (err = false), this.$refs.name.focus());
      err && !this.id && ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.id.focus()); 
      err && !this.pwd && ((msg = "비밀번호를 입력해주세요"), (err = false), this.$refs.pwd.focus());
      err && !this.pwdcheck && ((msg = "비밀번호를 재입력해주세요"), (err = false), this.$refs.pwdcheck.focus());
      err && !this.email && ((msg = "비밀번호를 입력해주세요"), (err = false), this.$refs.email.focus());

      if (!err) alert(msg); 
      else this.join();
    },
    join() {
      var data = {
        name : this.name,
        id: this.id,
        pwd: this.pwd, 
        email:this.email,
      }
      // 비동기 
      console.log(data);
      fetch("http://localhost:8080/user/join", {
        method: "POST",
        headers: {
        'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      })
      .then((response) => response.json())
      .then((result) => { 
        if (result.status === 'OK') {
          console.log('회원가입 성공');
          this.$router.push('/');
        } else {
          console.log('회원가입 실패');
          alert("회원가입 실패");
        }
        });
      //.then(() => { this.$router.push('/board/list')}); // then() 함수에서의 비동기 처리가 완료된 후에 페이지 이동을 수행
    }, 
  },
};
</script>

<style></style>
