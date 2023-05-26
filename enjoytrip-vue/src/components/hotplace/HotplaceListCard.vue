<template>
  <div>
    <!-- <h1 class="underline">Hotplace 글 목록</h1> -->
    <div style="text-align: right">
      <b-button
        @click="$router.push('/hotplace/write')"
        class="btn"
        variant="outline-dark"
      >
        글쓰기
      </b-button>
    </div>
    <!-- 검색 입력란 -->
    <div class="d-flex justify-content-end">
      <input
        type="text"
        v-model="searchKeyword"
        @input="searchArticles"
        placeholder="검색어를 입력하세요"
      />
    </div>
    <div v-if="articles.length" class="card-container row">
      <b-card
        class="card-item col-4 col-md-4 col-xl-2"
        v-for="article in displayedArticles"
        :key="article.id"
        :title="`${article.name}`"
        :footer="`${article.userId}`"
        img-alt="Card Image"
        tag="article"
        @click="navigateToArticle(article.id)"
      >
        <template v-if="article.image">
          <b-img :src="article.image" class="card-img-top"></b-img>
        </template>
        <template v-else>
          <div class="card-img-top-placeholder">이미지 로딩 중...</div>
        </template>
        <!-- <p class="card-text">작성자: {{article.userId}}</p>  -->
        <!-- <p class="card-text">{{ article.visitDate }}</p> -->
      </b-card>
    </div>
    <div v-else class="text-center">게시글이 없습니다.</div>
    <!-- 페이징 처리를 위한 버튼들 -->
    <div class="d-flex justify-content-center mt-4">
      <b-pagination
        v-model="currentPage"
        :total-rows="totalRows"
        :per-page="perPage"
        aria-controls="article-list"
        align="center"
        size="sm"
        class="mb-0"
        @change="onPageChange"
      ></b-pagination>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "HotplaceList",
  components: {},
  data() {
    return {
      articles: [],
      article_img: {},

      perPage: 10, // 페이지당 보여줄 article 개수
      totalRows: 0, // 전체 article 개수
      displayedArticles: [],

      searchKeyword: "", // 검색어
    };
  },
  created() {
    axios
      .get("http://localhost:8080/hotplace/")
      .then((response) => {
        this.articles = response.data.data;

        const imagePromises = this.articles.map((article) =>
          axios
            .get(`http://localhost:8080/hotplace/${article.id}`)
            .then((response) => {
              this.article_img = response.data.resImg;
              article.image =
                "http://localhost:8080/upload" +
                this.article_img[0].path.split("upload")[1] +
                "/" +
                this.article_img[0].name;
              this.totalRows = this.articles.length; // 전체 article 개수 설정
              this.onPageChange(1); // 초기 페이지 설정
              console.log(article.image);
            })
        );

        Promise.all(imagePromises).then(() => {
          this.$forceUpdate();
        });
      })
      .catch((error) => {
        console.error(error);
      });
  },
  methods: {
    navigateToArticle(id) {
      this.$router.push("/hotplace/detail/" + id);
    },
    searchArticles() {
      // 검색어에 따라 displayedArticles 필터링
      const keyword = this.searchKeyword.trim().toLowerCase();
      if (this.articles && this.articles.length > 0) {
        // articles 배열이 존재하고 비어있지 않은지 확인
        this.displayedArticles = this.articles.filter((article) => {
          return article.name && article.name.toLowerCase().includes(keyword);
        });
      }
      //this.totalRows = this.displayedArticles.length; // 전체 article 개수 설정
      this.onPageChangeSearch(1); // 검색 결과 페이지 초기화
      console.log(this.displayedArticles);
    },
    onPageChangeSearch(newPage) {
      this.totalRows = this.displayedArticles.length; // 전체 article 개수 설정
      this.currentPage = newPage;
      const startIndex = (newPage - 1) * this.perPage;
      const endIndex = startIndex + this.perPage;
      this.displayedArticles = this.displayedArticles.slice(
        startIndex,
        endIndex
      );
    },
    onPageChange(newPage) {
      this.currentPage = newPage;
      // 현재 페이지에 해당하는 article 범위 설정
      const startIndex = (newPage - 1) * this.perPage;
      const endIndex = startIndex + this.perPage;
      this.displayedArticles = this.articles.slice(startIndex, endIndex);
    },
  },
};
</script>

<style>
.card-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.card-item {
  margin-bottom: 20px;
  margin: 10px;
}
</style>
