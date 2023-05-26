<template>
  <div class="regist"> 
    <div class="view text-start" style="border: none;"></div>
      <div class="row">
      <div class="col-6">
        <div class="row">
          <div class="view text-start" style="border: none; font-size: 40px">{{ article.name }}</div>
          <div class="view text-start" style="border: none; font-size: 20px">
            <b-icon icon="person-fill" font-scale="1" ></b-icon> {{ article.userId }}</div>
        </div>
      </div>
      <div class="col-6 d-flex justify-content-end align-items-end">
        <div class="view text-end" style="border: none; font-size: 20px">
          <b-icon icon="pencil" font-scale="1"></b-icon> {{ article.writeTime }}</div>
      </div>
    </div>
    
    <div class="row">
      <div class="col-6"> 
        <b-carousel id="carouselExample" :interval="5000" controls indicators >
          <b-carousel-slide
            v-for="(i_src, index) in img_src"
            :key="index"
            :img-src="i_src" 
          ></b-carousel-slide>
        </b-carousel>
      </div>
      <div class="col-6 d-flex align-items-center justify-content-center" style="flex-direction: column;">
        <!-- <label> 내용</label> --> 
        <div class="view text-start" style="border: none; font-size: 20px">{{ article.content }}</div>

        <!-- <label> 장소 </label> -->
        <div class="view text-start" style="border: none; font-size: 20px">
          <b-icon icon="geo-alt" font-scale="1"></b-icon> {{ article.dataNum }}</div>

        <!-- <label> 방문일</label> -->
        <div class="view text-start" style="border: none; font-size: 20px">
          <b-icon icon="calendar" font-scale="1"></b-icon> {{ article.visitTime }}</div>

      </div>
      <div class="col-6 offset-6 d-flex justify-content-end" style="padding-top: 15px">
        <b-button  v-if="this.userId === article.userId" class="btn"  @click="$router.push('/hotplace/modify/' + article.id)" variant="outline-dark" style="width:10%">수정</b-button>
        <b-button  v-if="this.userId === article.userId" class="btn"  @click="$router.push('/hotplace/delete/' + article.id)" variant="outline-dark" style="width:10%">삭제</b-button>
        <b-button class="btn"  @click="$router.push('/hotplace/list/')" variant="outline-dark" style="width:10%">목록</b-button>
      </div>
    </div>
  </div>
</template> 

<script>
import axios from "axios";

export default {
  name: "HotplaceView",

  data() {
    return {
      article: {},
      article_img : {},
      img_src : [],
      userId:{}
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

<style>
.carousel-control-prev span {
  display: none !important;
}

.carousel-control-prev::before {
  content: "<";
  font-size: 40px;
}

.carousel-control-next span {
  display: none !important;
}

.carousel-control-next::before {
  content: ">";
  font-size: 40px;
}
</style>
