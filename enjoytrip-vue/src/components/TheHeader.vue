<template>
  <div class="header"> 
    <div>
      <router-link :to="{name: 'home'}">Home</router-link> | 
      <router-link :to="{name: 'user'}">로그인</router-link> | 
      <router-link :to="{name: 'user'}">회원가입</router-link> | 
      <router-link :to="{name: 'user'}">지역별 여행지</router-link> | 
      <router-link :to="{name: 'user'}">나의 여행 계획</router-link> | 
      <router-link :to="{name: 'user'}">여행 계획 둘러보기</router-link> | 
      <router-link :to="{name: 'user'}">핫플 자랑하기</router-link> | 
      <router-link :to="{name: 'board'}">공지사항</router-link>
    </div>
  </div>
</template>

<script>
export default {
  name: "TheHeader",
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
