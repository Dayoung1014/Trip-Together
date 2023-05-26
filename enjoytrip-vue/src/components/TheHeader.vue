<template>
  <div class="header">
    <div class="container-fluid">
      <div
        class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start"
      >
        <ul
          class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0 align-items-center"
        >
          <li>
            <router-link
              :to="{ name: 'home' }"
              class="nav-link mx-5 p-3 ps-5 px-2 fs-1"
              style="color: black"
            >
              <img
                src="http://localhost:8080/img/TripTogetherLogo.png"
                alt="TripTogether Logo"
                style="width: 120px; height: auto"
              />
            </router-link>
          </li>
          <!-- <li>
            <router-link
              :to="{ name: 'map' }"
              class="nav-link ps-5 px-2 fs-5"
              style="color: black"
              ><strong>Map</strong></router-link
            >
          </li> -->
          <li>
            <router-link
              :to="{ name: 'plan' }"
              class="nav-link ps-3 px-2 fs-5"
              style="color: black"
              ><strong>My Plan</strong></router-link
            >
          </li>
          <li>
            <router-link
              :to="{ name: 'plancommunitylist' }"
              class="nav-link ps-3 px-2 fs-5"
              style="color: black"
              ><strong>Plan Community</strong></router-link
            >
          </li>
          <li>
            <router-link
              :to="{ name: 'hotplace' }"
              class="nav-link ps-3 px-2 fs-5"
              style="color: black"
              ><strong>Review</strong></router-link
            >
          </li>
          <li>
            <router-link
              :to="{ name: 'board' }"
              class="nav-link ps-3 px-2 fs-5"
              style="color: black"
              ><strong>Notice</strong></router-link
            >
          </li>
        </ul>

        <div
          v-if="userInfo === null"
          style="display: flex; flex-direction: row; width: 10%"
        >
          <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown right>
              <template #button-content>
                <b-icon icon="people" font-scale="2"></b-icon>
              </template>
              <b-dropdown-item href="#">
                <b-button
                  id="btn-login"
                  variant="white"
                  class="btn fs-6"
                  style="width: 80%"
                  @click="$bvModal.show('loginmodal')"
                  ><b-icon icon="key"></b-icon><strong> 로그인</strong>
                </b-button>
              </b-dropdown-item>
              <b-dropdown-item href="#">
                <b-button
                  id="btn-join"
                  variant="white"
                  class="btn fs-6"
                  style="width: 80%"
                  @click="$bvModal.show('joinmodal')"
                  ><b-icon icon="person-circle"></b-icon
                  ><strong> 회원가입</strong></b-button
                >
              </b-dropdown-item>

              <b-dropdown-item href="#">
                <b-button
                  id="btn-join"
                  variant="white"
                  class="btn fs-6"
                  style="width: 80%"
                  @click="$bvModal.show('findpwmodal')"
                  ><b-icon icon="person-circle"></b-icon
                  ><strong> 비밀번호 찾기</strong></b-button
                >
              </b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </div>
        <div
          v-else
          style="
            display: flex;
            flex-direction: row;
            width: 20%;
            justify-content: space-evenly;
          "
        >
          <b-navbar-nav class="ml-auto">
            <b-nav-item-dropdown right>
              <template #button-content>
                {{ userInfo.id }}
                <b-icon icon="person-circle" font-scale="2"></b-icon>
              </template>
              <b-dropdown-item href="#">
                <b-button
                  id="btn-login"
                  variant="white"
                  class="btn fs-6"
                  style="width: 80%"
                  @click.prevent="onClickLogout"
                  ><b-icon icon="key"></b-icon><strong> 로그아웃</strong>
                </b-button>
              </b-dropdown-item>
              <b-dropdown-item href="#">
                <b-button
                  id="btn-join"
                  variant="white"
                  class="btn fs-6"
                  style="width: 80%"
                  @click="$bvModal.show('mypagePwCheckmodal')"
                  ><b-icon icon="person-circle"></b-icon
                  ><strong> 마이페이지</strong></b-button
                >
              </b-dropdown-item>
            </b-nav-item-dropdown>
          </b-navbar-nav>
        </div>
      </div>
    </div>

    <!-- 모달 회원가입 -->
    <b-modal id="joinmodal" hide-footer>
      <template #modal-title> 회원가입 </template>
      <div class="d-block text-start">
        <form ref="form" @submit.stop.prevent="handleSubmit">
          <b-form-group
            label-for="form-join"
            invalid-feedback="Name is required"
          >
            <div>
              <form id="form-join" method="POST" action="">
                <div class="mb-3">
                  <label for="username" class="form-label">이름 : </label>
                  <input
                    style="width: 100%"
                    type="text"
                    class="form-control"
                    id="username"
                    name="name"
                    ref="name"
                    placeholder="이름..."
                    v-model="name"
                  />
                </div>
                <div class="mb-3">
                  <label for="userid" class="form-label">아이디 : </label>
                  <input
                    style="width: 100%"
                    type="text"
                    class="form-control"
                    id="userid"
                    name="id"
                    placeholder="아이디..."
                    v-model="id"
                    ref="id"
                    @blur="idCheck"
                  />
                </div>
                <div id="idcheck-result" :style="{ color: idCheckResultColor }">
                  {{ idCheckResult }}
                </div>
                <div class="mb-3">
                  <label for="userpwd" class="form-label">비밀번호 : </label>
                  <input
                    style="width: 100%"
                    type="password"
                    class="form-control"
                    id="userpwd"
                    name="pwd"
                    ref="pwd"
                    placeholder="비밀번호..."
                    v-model="pwd"
                  />
                </div>
                <div class="mb-3">
                  <label for="password" class="form-label" style="width: 100%"
                    >비밀번호확인 :
                  </label>
                  <input
                    style="width: 100%"
                    type="password"
                    class="form-control"
                    id="pwdcheck"
                    ref="pwdcheck"
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
                      ref="email"
                      placeholder="이메일아이디"
                      v-model="email"
                    />
                  </div>
                </div>
                <div class="col-auto text-center">
                  <button
                    type="button"
                    id="btn-join"
                    class="btn btn-outline-secondary mb-3"
                    style="width: 100%; display: flex; justify-content: center"
                    @click="checkJoinValue"
                  >
                    회원가입
                  </button>
                </div>
              </form>
            </div>
          </b-form-group>
        </form>
      </div>
    </b-modal>

    <!-- 모달 로그인 -->
    <b-modal id="loginmodal" hide-footer>
      <template #modal-title> 로그인 </template>
      <div class="d-block text-start">
        <form id="form-login" method="POST" action="">
          <div class="mb-3">
            <label for="userid" class="form-label">아이디 : </label>
            <input
              type="text"
              class="form-control"
              id="userid"
              name="id"
              ref="id"
              v-model="user.userid"
              placeholder="아이디..."
              style="width: 100%"
            />
          </div>
          <div class="mb-3">
            <label for="userpwd" class="form-label">비밀번호 : </label>
            <input
              type="password"
              class="form-control"
              id="userpwd"
              name="pwd"
              ref="pwd"
              v-model="user.userpwd"
              placeholder="비밀번호..."
            />
          </div>
          <div class="col-auto text-start">
            <input
              id="btnlogin2"
              class="btn btn-outline-secondary mb-3"
              style="width: 100%; display: flex; justify-content: center"
              value="로그인"
              @click="confirm"
            />
          </div>
        </form>
      </div>
    </b-modal>

    <!-- 모달 비밀번호 찾기 -->
    <b-modal id="findpwmodal" hide-footer>
      <template #modal-title> 비밀번호 찾기 </template>
      <div class="d-block text-start">
        <form id="form-login" method="POST" action="">
          <div class="mb-3">
            <label for="userid" class="form-label">아이디 : </label>
            <input
              style="width: 100%"
              type="text"
              class="form-control"
              id="userid"
              name="id"
              ref="id"
              placeholder="아이디..."
              v-model="id"
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
                ref="email"
                placeholder="이메일아이디"
                v-model="email"
              />
            </div>
          </div>
          <div class="col-auto text-start">
            <input
              class="btn btn-outline-secondary mb-3"
              style="width: 100%; display: flex; justify-content: center"
              id="btnFindPw"
              value="이메일로 비밀번호 전송하기"
              @click="btnFindPw"
            />
          </div>
        </form>
      </div>
    </b-modal>

    <!-- 모달 마이페이지 비밀번호 확인 -->
    <b-modal id="mypagePwCheckmodal" hide-footer>
      <template #modal-title> 비밀번호 확인 </template>
      <div class="d-block text-start">
        <form id="form-mypage" method="POST" action="">
          <div class="mb-3">
            <input
              type="password"
              class="form-control"
              id="userpwd"
              name="pwd"
              ref="pwd"
              placeholder="비밀번호..."
              v-model="pwd"
            />
          </div>
          <div class="col-auto text-center">
            <input
              class="btn btn-outline-secondary mb-3"
              style="width: 100%; display: flex; justify-content: center"
              id="btnmodify"
              value="확인"
              @click="checkIdPw"
            />
          </div>
        </form>
      </div>
    </b-modal>

    <!-- 모달 마이페이지 -->
    <b-modal id="mypagemodal" hide-footer>
      <template #modal-title> 마이페이지 </template>
      <div class="d-block text-start">
        <form id="form-mypage" method="POST" action="">
          <div class="mb-3">
            <label for="userid" class="form-label">아이디 : </label>
            <input
              style="width: 100%"
              type="text"
              class="form-control"
              id="userid"
              name="id"
              ref="id"
              v-model="id"
              readonly
            />
          </div>
          <div class="mb-3">
            <label for="username" class="form-label">이름 : </label>
            <input
              style="width: 100%"
              type="text"
              class="form-control"
              id="username"
              name="name"
              ref="name"
              placeholder="이름..."
              v-model="name"
            />
          </div>

          <div class="mb-3">
            <label for="userpwd" class="form-label">비밀번호 : </label>
            <input
              style="width: 100%"
              type="password"
              class="form-control"
              id="userpwd"
              name="pwd"
              ref="pwd"
              placeholder="비밀번호..."
              v-model="pwd"
            />
          </div>
          <div class="mb-3">
            <label for="emailid" class="form-label">이메일 : </label>
            <div class="input-group">
              <input
                style="width: 100%"
                type="text"
                class="form-control"
                id="emailid"
                name="email"
                ref="email"
                placeholder="이메일아이디"
                v-model="email"
              />
            </div>
          </div>
          <div class="col-auto text-center">
            <input
              class="btn btn-outline-secondary mb-3"
              style="width: 100%; display: flex; justify-content: center"
              id="btnmodify"
              value="회원 정보 수정"
              @click="checkMypageValue"
            />
          </div>
        </form>
      </div>
    </b-modal>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
const memberStore = "memberStore";
import cryptoRandomString from "crypto-random-string";

export default {
  name: "TheHeader",
  data() {
    return {
      name: "",
      id: "",
      pwd: "",
      pwdcheck: "",
      email: "",
      idCheckResult: "", // 중복 확인 결과를 저장할 데이터 속성
      idCheckResultColor: "", // 중복 확인 결과를 저장할 데이터 속성
      newPwd: "",
      newPwdCheck: "",
      user: {
        userid: "",
        userpwd: "",
      },
    };
  },

  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    ...mapActions(memberStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      console.log(this.isLogin);
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        //this.$router.push(this.$route.path);
        this.$bvModal.hide("loginmodal"); // 모달 닫기
      } else {
        alert("아이디 비밀번호를 확인해주세요.");
      }
    },
    idCheck() {
      const id = this.$refs.id.value;
      if (id.length < 6) {
        this.idCheckResult = "6자 이상의 아이디를 사용하세요.";
        this.idCheckResultColor = "red";
        return;
      }
      fetch(`http://localhost:8080/user/${id}`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => response.json())
        .then((result) => {
          console.log(result);
          if (result.msg === "No") {
            //alert("중복된 아이디입니다.")
            this.idCheckResult = "중복된 아이디입니다.";
            this.idCheckResultColor = "red";
          } else {
            //alert("사용 가능한 아이디입니다.");
            this.idCheckResult = "사용 가능한 아이디입니다.";
            this.idCheckResultColor = "blue";
          }
        });
    },
    onClickLogout() {
      console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },

    // 입력값 체크하기
    checkLoginValue() {
      // 사용자 입력값 체크하기
      // 작성자아이디, 제목, 내용이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      !this.id &&
        ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.id.focus());
      err &&
        !this.pwd &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.pwd.focus());

      if (!err) alert(msg);
      else this.login();
    },
    validateEmail(email) {
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return emailRegex.test(email);
    },
    checkJoinValue() {
      let err = true;
      let msg = "";

      !this.name &&
        ((msg = "이름을 입력해주세요"), (err = false), this.$refs.name);
      err &&
        !this.id &&
        ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.id);
      err &&
        !this.pwd &&
        ((msg = "비밀번호를 입력해주세요"), (err = false), this.$refs.pwd);
      err &&
        !this.pwdcheck &&
        ((msg = "비밀번호를 재입력해주세요"),
        (err = false),
        this.$refs.pwdcheck);
      err &&
        this.pwdcheck !== this.pwd &&
        ((msg = "비밀번호 재입력을 확인해주세요."),
        (err = false),
        this.$refs.pwdcheck);
      err &&
        !this.email &&
        ((msg = "이메일을 입력해주세요"), (err = false), this.$refs.email);
      err &&
        !this.validateEmail(this.email) && // 이메일 유효성 검사
        ((msg = "유효한 이메일을 입력해주세요"),
        (err = false),
        this.$refs.email);

      if (!err) alert(msg);
      else this.join();
    },
    join() {
      var data = {
        name: this.name,
        id: this.id,
        pwd: this.pwd,
        email: this.email,
      };
      // 비동기
      console.log(data);
      fetch("http://localhost:8080/user/join", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(data),
      })
        .then((response) => response.json())
        .then((result) => {
          if (result.status === "OK") {
            console.log("회원가입 성공");
            this.$router.go(0);
          } else {
            console.log("회원가입 실패");
            alert("회원가입 실패");
          }
        });
    },

    btnFindPw() {
      const temporaryPassword = cryptoRandomString({ length: 10 });

      console.log(this.id + this.email);
      const emailData = {
        to: this.email,
        subject: "비밀번호를 알려드립니다.",
        text: "",
      };

      fetch(`http://localhost:8080/user/getUserInfo/${this.id}`, {
        method: "GET",
      })
        .then((response) => response.json())
        .then((result) => {
          if (result.userInfo.email === emailData.to) {
            result.userInfo.pwd = temporaryPassword;
            emailData.text = temporaryPassword;
            fetch("http://localhost:8080/user/", {
              method: "PUT",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify(result.userInfo),
            });

            fetch("http://localhost:8080/user/sendEmail", {
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: JSON.stringify(emailData),
            });
            alert("email로 임시 비밀번호를 발송했습니다.");
          } else {
            alert("사용자 id, email을 확인해주세요.");
          }
        });
    },

    // 회원 정보 수정 전 비밀번호 확인
    checkIdPw() {
      if (this.pwd === this.userInfo.pwd) {
        this.$bvModal.hide("mypagePwCheckmodal"); // 모달 닫기
        this.id = this.userInfo.id;
        this.name = this.userInfo.name;
        this.email = this.userInfo.email;
        this.$bvModal.show("mypagemodal");
      } else {
        alert("비밀번호를 다시 입력하세요.");
      }
    },

    checkMypageValue() {
      let err = true;
      let msg = "";
      !this.name &&
        ((msg = "이름을 입력해주세요"), (err = false), this.$refs.name);
      !this.pwd &&
        ((msg = "비밀번호를 입력해주세요"), (err = false), this.$refs.pwd);
      err &&
        !this.email &&
        ((msg = "이메일을 입력해주세요"), (err = false), this.$refs.email);
      err &&
        !this.validateEmail(this.email) && // 이메일 유효성 검사
        ((msg = "유효한 이메일을 입력해주세요"),
        (err = false),
        this.$refs.email);

      if (!err) alert(msg);
      else this.modifyUser();
    },

    modifyUser() {
      console.log(this.name);
      console.log(this.pwd);
      console.log(this.email);

      const modiUser = {
        id: this.id,
        name: this.name,
        pwd: this.pwd,
        email: this.email,
      };
      fetch("http://localhost:8080/user/", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(modiUser),
      });

      this.$bvModal.hide("mypagemodal"); // 모달 닫기
    },
  },
};
</script>

<style scope>
router-link {
  color: black;
}
img {
  width: 150px;
}

.header {
  text-align: center;
}

a {
  font-weight: bold;
  color: black;
}

a:hover {
  color: black;
}
</style>
