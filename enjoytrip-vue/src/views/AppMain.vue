<template>
  <div class="main">
  </div>
</template>

<script>
export default {
  name: "AppMain",
  data() {
    return {
      mapInfo: "지도를 출력할 예정입니다.",
      attractionInfo: "관광지 목록을 출력 할 예정입니다.",
      planInfo: "여행 계획을 출력 할 예정입니다.",
    };
  },
};
</script>

<style scope>
.main-content {
  border: 2px solid black;
  height: 200px;
  width: 80%;
  margin: auto;
  margin-top: 20px;
}
</style>
