<template>
  <div class="regist">
    <!-- <h1 class="underline">Plan 등록</h1> -->
    <div class="regist_form">
      <label for="name">일정명</label>
      <input
        style="width: 100%"
        type="text"
        id="name"
        ref="name"
        v-model="data.name"
      /><br />

      <label for="title"><strong>시작일</strong></label>
      <input
        type="date"
        name="startDate"
        ref="startDate"
        id="startDate"
        max="2023-12-31"
        min="2000-01-01"
        value="2023-05-12"
        v-model="data.startDate"
      />

      <label for="title"><strong>종료일</strong></label>
      <input
        type="date"
        name="endDate"
        ref="endDate"
        id="endDate"
        max="2023-12-31"
        min="2000-01-01"
        value="2023-05-12"
        v-model="data.endDate"
      />

      <label for="visibility"><strong>비밀글</strong></label>
      <div>
        <label for="public"
          ><b-icon icon="unlock-fill" font-scale="1"></b-icon>공개<input
            type="radio"
            id="public_yes"
            name="isPublic"
            value="1"
            v-model="data.isPublic"
        /></label>
        <label for="private"
          ><b-icon icon="lock-fill" font-scale="1"></b-icon>비공개<input
            type="radio"
            id="public_no"
            name="isPublic"
            value="0"
            v-model="data.isPublic"
        /></label>
      </div>

      <div
        style="padding-top: 15px"
        class="col-6 offset-6 d-flex justify-content-end"
      >
        <b-button
          @click="checkValue()"
          class="btn"
          variant="outline-dark"
          style="width: 10%"
          >등록</b-button
        >
        <b-button
          @click="$router.push('/plan/list')"
          class="btn"
          variant="outline-dark"
          style="width: 10%"
          >목록</b-button
        >
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "PlanWrite",
  data() {
    return {
      data: {
        name: "",
        startDate: "",
        endDate: "",
        userId: "",
        isPublic: "",
      },
    };
  },
  created() {
    // 현재 세션 스토리지에서 "vuex" 키의 값을 가져오기
    const vuexData = sessionStorage.getItem("vuex");
    const userData = JSON.parse(vuexData);
    // userInfo의 id 값 가져오기
    this.data.userId = userData.memberStore.userInfo.id;
  },
  methods: {
    // 입력값 체크하기
    checkValue() {
      // 사용자 입력값 체크하기
      let err = true;
      let msg = "";
      err &&
        !this.data.name &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.name.focus());
      err &&
        !this.data.startDate &&
        ((msg = "시작일 입력해주세요"),
        (err = false),
        this.$refs.startDate.focus());
      err &&
        !this.data.endDate &&
        ((msg = "종료일 입력해주세요"),
        (err = false),
        this.$refs.endDate.focus());
      err &&
        !this.data.isPublic &&
        ((msg = "비밀글 여부를 선택해주세요"), (err = false));

      if (!err) alert(msg);
      else this.submitForm();
    },

    submitForm() {
      console.log(this.data);
      axios({
        method: "post",
        url: "http://localhost:8080/plan/",
        data: this.data,
        redirect: "follow",
      });

      setTimeout(() => {
        this.$router.push("/plan");
      }, 100);
    },
  },
};
</script>

<style></style>
