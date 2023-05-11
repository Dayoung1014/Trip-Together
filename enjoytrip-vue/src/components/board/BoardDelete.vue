<template>
  <div class="regist">
    <h1>글번호 : {{this.$route.params.no}} 삭제하시겠습니까?</h1>  
    <button @click="deleteArticle">삭제</button>
  </div>
</template>

<script>
export default {
  name: "BoardDelete",
  props : { 
  },
  created() {
    // 비동기
    // TODO : 글번호에 해당하는 글을 삭제.  
    },
    methods: {
      deleteArticle() {
        fetch(`http://192.168.208.40:9999/vue/board/${this.$route.params.no}`, {
          method: "DELETE"
          })
          .then((response) => response.text())
          .then((result) => console.log(result))
          .then(() => { this.$router.push('/board/list')});
      }
    },

};
</script>

<style></style>
