<template>
  <div class="regist">
    <!-- <h1>SSAFY 글 삭제</h1> -->
    <div>삭제중....</div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "PlanDelete",

  created() {
    this.no = this.$route.params.no;

    axios({
      method: "delete",
      url: `http://localhost:8080/plan/${this.no}`,
    });
    setTimeout(() => {
      this.$router.push("/plan");
    }, 100);
  },
};
</script>

<style></style>
