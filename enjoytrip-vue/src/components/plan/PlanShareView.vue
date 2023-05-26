<template>
  <div class="regist">
    <!-- <h1 class="underline">Plan 상세보기</h1> -->
    <div class="regist_form">
      <div class="view text-start" style="border: none; font-size: 40px">{{ article.name }}</div>
      <div class="view text-start" style="border: none; font-size: 20px">
            <b-icon icon="person-fill" font-scale="1" ></b-icon> {{ article.userId }} 
      </div>
      <div class="view text-start" style="border: none; font-size: 20px"> 
            <b-icon icon="calendar" font-scale="1"></b-icon> {{ article.startDate }} ~
            <b-icon icon="calendar" font-scale="1"></b-icon> {{ article.endDate }}
      </div>
      
      <div style="padding-top: 15px" class="col-6 offset-6 d-flex justify-content-end">
        <b-button v-if="this.userId === article.userId"  class="btn"  @click="$router.push('/plan/modify/' + article.id)" variant="outline-dark" style="width:10%">수정</b-button>
        <b-button v-if="this.userId === article.userId" class="btn"  @click="$router.push('/plan/delete/' + article.id)" variant="outline-dark" style="width:10%">삭제</b-button>
        <b-button class="btn"  @click="$router.push('/plan/list/')" variant="outline-dark" style="width:10%">목록</b-button>
        <b-button v-if="this.userId === article.userId" class="btn" @click="sharePlanLink()" variant="outline-dark" style="width:20%">일행 추가</b-button>
      </div>
    </div>
  </div>
</template> 
<script>
import axios from "axios";

export default {
  name: "PlanShareView",

  data() {
    return {
      article: {}, 
      userId : {}
    };
  },

  created() {
    // 현재 세션 스토리지에서 "vuex" 키의 값을 가져오기
        const vuexData = sessionStorage.getItem("vuex");
    const userData = JSON.parse(vuexData);
    console.log(userData)
    // userInfo의 id 값 가져오기
    this.userId = userData.memberStore.userInfo.id;
    console.log(this.userId)
    this.no = this.$route.params.no;

    axios({
      method: "get",
      url: `http://localhost:8080/plan/detail/${this.no}`,
      responseType: "json",
    })
      .then((response) => {
        console.log(response.data)
        this.article = response.data;          
      })
      .catch((error) => console.log(error));
  },

  methods: {
    sharePlanLink() {
      const currentURL = window.location.href;
    
    // 클립보드에 복사하기 위해 input 요소를 동적으로 생성합니다.
    const input = document.createElement('input');
    input.style.opacity = '0';
    input.value = currentURL;
    document.body.appendChild(input);
    
    // input 요소의 값을 선택하고 복사합니다.
    input.select();
    document.execCommand('copy');
    
    // input 요소를 제거합니다.
    document.body.removeChild(input);
    
    // 복사 완료 메시지 등의 알림을 사용자에게 표시할 수 있습니다.
    alert('링크가 클립보드에 복사되었습니다!');

    console.log(currentURL)
    }
  }
};
</script>

<style></style>
