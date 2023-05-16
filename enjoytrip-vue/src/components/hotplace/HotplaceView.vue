<template>
  <div class="regist">
    <h1 class="underline">SSAFY 글 상세보기</h1>
    <div class="regist_form">
      <label> 번호</label>
      <div class="view">{{ article.id }}</div>
      <label> 제목</label>
      <div class="view">{{ article.name }}</div>
      <label> 작성자</label>
      <div class="view">{{ article.userId }}</div>
      <label> 사진</label>
      <div class="view">
        <img v-for="i_src in img_src" :key="i_src" :src="i_src" alt="테스트" /> 
        <!-- <img :src="img_src" alt="ssafy.png"> -->
      </div> 
      <label> 내용</label>
      <div class="view">{{ article.content }}</div>
      <label> 방문일</label>
      <div class="view">{{ article.visitTime }}</div>
      <label> 작성일</label>
      <div class="view">{{ article.writeTime }}</div>
      
      <div style="padding-top: 15px">
        <router-link class="btn" :to="'/hotplace/modify/' + article.id"
          >수정</router-link
        >
        <router-link class="btn" :to="'/hotplace/delete/' + article.id"
          >삭제</router-link
        >
        <router-link class="btn" :to="'/hotplace/list/'">목록</router-link>
      </div>
    </div>
  </div>
</template>
<!-- 사진 나오게 하는 div 추가 후 article_img 배열 만큼 돌면서 img 뿌려주기-->
<!-- 클라이언트에서 서버의 사진 접근을 어떻게 ? -->
<script>
import axios from "axios";

export default {
  name: "HotplaceView",

  data() {
    return {
      article: {},
      article_img : {},
      img_src : []
    };
  },

  created() {
    this.no = this.$route.params.no;

    axios({
      method: "get",
      url: `http://localhost:8080/hotplace/${this.no}`,
      responseType: "json",
    })
      .then((response) => {
        this.article = response.data.resdata; 
        this.article_img = response.data.resImg;
        console.log(this.article)
        console.log(this.article_img)
        // console.log(this.article_img[0].name)
        // console.log(this.article_img[0].path)

        for (let i = 0; i < this.article_img.length; i++) {
          this.img_src[i] = "http://localhost:8080/upload"+this.article_img[i].path.split('upload')[1] +"/"+this.article_img[i].name ;
          console.log(this.img_src[i])
        }
        
       
        
      })
      .catch((error) => console.log(error));
  },
};
</script>

<style></style>
