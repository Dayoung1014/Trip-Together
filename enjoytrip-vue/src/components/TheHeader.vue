<template>
  <div class="header p-3">
    <div class="container-fluid">
      <div
        class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start"
      >
        <ul
          class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0 align-items-center"
        >
          <li>
            <router-link
              :to="{ name: 'user' }"
              class="nav-link mx-5 p-3 ps-5 px-2 fs-1"
              style="color: SeaGreen"
              >Enjoy Trip</router-link
            >
          </li>
          <li>
            <router-link
              :to="{ name: 'user' }"
              class="nav-link ps-5 px-2 fs-5"
              style="color: ForestGreen"
              >지역별 여행지</router-link
            >
          </li>
          <li>
            <router-link
              :to="{ name: 'user' }"
              class="nav-link ps-3 px-2 fs-5"
              style="color: SkyBlue"
              >나의여행계획</router-link
            >
          </li>
          <li>
            <router-link
              :to="{ name: 'user' }"
              class="nav-link ps-3 px-2 fs-5"
              style="color: Plum"
              >여행 계획 둘러보기</router-link
            >
          </li>
          <li>
            <router-link
              :to="{ name: 'hotplace' }"
              class="nav-link ps-3 px-2 fs-5"
              style="color: Crimson"
              >핫플 자랑하기</router-link
            >
          </li>
          <li>
            <router-link
              :to="{ name: 'board' }"
              class="nav-link ps-3 px-2 fs-5"
              style="color: Plum"
              >공지사항</router-link
            >
          </li>
        </ul>
        <!--로그인 전-->
        <button
          type="button"
          class="btn fs-5"
          style="color: SeaGreen"
          id="btnlogin"
          data-bs-toggle="modal"
          data-bs-target="#loginModal"
        >
          로그인
        </button>
        <button
          type="button"
          class="btn fs-5"
          style="color: Plum"
          id="btnsignin"
          data-bs-toggle="modal"
          data-bs-target="#signinModal"
        >
          회원가입
        </button>
      </div>
    </div>

    <!-- The Modal 로그인-->
    <div class="modal fade" tabindex="-1" role="dialog" id="loginModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content rounded-4 shadow">
          <div class="modal-header p-5 pb-2 border-bottom-0">
            <!-- <h1 class="modal-title fs-5" >Modal title</h1> -->
            <h1 class="fw-bold mb-0 fs-4">로그인</h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>

          <div class="modal-body p-5 pt-0">
            <form id="form-login" method="POST" action="">
              <div class="mb-3">
                <label for="userid" class="form-label">아이디 : </label>
                <input
                  type="text"
                  class="form-control"
                  id="userid"
                  name="id" 
                  v-model="id"
                  placeholder="아이디..."
                />
              </div>
              <div id="idcheck-result"></div>
              <div class="mb-3">
                <label for="userpwd" class="form-label">비밀번호 : </label>
                <input
                  type="text"
                  class="form-control"
                  id="userpwd"
                  name="pwd"
                  v-model="pwd"
                  placeholder="비밀번호..."
                />
              </div>
              <div class="col-auto text-center">
                <input
                  class="w-100 mb-2 btn btn-lg rounded-3 btn-primary"
                  id="btnlogin2"
                  value="로그인"
                  @click="checkLoginValue"
                />
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>

    <!-- The Modal 회원가입-->
    <div class="modal fade" tabindex="-1" role="dialog" id="signinModal">
      <div class="modal-dialog" role="document">
        <div class="modal-content rounded-4 shadow">
          <div class="modal-header p-5 pb-2 border-bottom-0">
            <!-- <h1 class="modal-title fs-5" >Modal title</h1> -->
            <h1 class="fw-bold mb-0 fs-4">회원가입</h1>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>

          <div class="modal-body p-5 pt-0">
            <form id="form-join" method="POST" action="">
              <div class="mb-3">
                <label for="username" class="form-label">이름 : </label>
                <input
                  type="text"
                  class="form-control"
                  id="username"
                  name="name"
                  placeholder="이름..."
                  v-model="name" 
                />
              </div>
              <div class="mb-3">
                <label for="userid" class="form-label">아이디 : </label>
                <input
                  type="text"
                  class="form-control"
                  id="userid"
                  name="id"
                  placeholder="아이디..."
                  v-model="id" 
                />
              </div>
              <div id="idcheck-result"></div>
              <div class="mb-3">
                <label for="userpwd" class="form-label">비밀번호 : </label>
                <input
                  type="text"
                  class="form-control"
                  id="userpwd"
                  name="pwd"
                  placeholder="비밀번호..."
                  v-model="pwd" 
                />
              </div>
              <div class="mb-3">
                <label for="pwdcheck" class="form-label">비밀번호확인 : </label>
                <input
                  type="text"
                  class="form-control"
                  id="pwdcheck"
                  placeholder="비밀번호확인..."
                  v-model="pwdcheck" 
                />
              </div>
              <div class="mb-3">
                <label for="emailid" class="form-label">이메일 : </label>
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control"
                    id="emailid"
                    name="email"
                    placeholder="이메일아이디"
                    v-model="email" 
                  />
                </div>
              </div>
              <div class="col-auto text-center">
                <button
                  type="button"
                  id="btn-join"
                  class="btn btn-outline-primary mb-3"
                  @click="checkJoinValue"
                >
                  회원가입
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "TheHeader",
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
    // 입력값 체크하기  
    checkLoginValue() {
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
          this.$router.go(0)
        } else {
          console.log('로그인 실패');
          alert("로그인 실패");
        }
        });
      //.then(() => { this.$router.push('/board/list')}); // then() 함수에서의 비동기 처리가 완료된 후에 페이지 이동을 수행
    }, 
    checkJoinValue() { 
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
          this.$router.go(0)
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

<style scope>
img {
  width: 150px;
}

.header {
  padding: 30px;
  text-align: center;
  box-shadow: 0px 1px 10px rgba(159, 157, 157, 0.3);
}

a {
  font-weight: bold;
  color: #2c3e50;
}

a:hover {
  color: #42b983;
}
</style>


