<template>
  <div>
    <!-- <h1 class="underline">Notice 글 목록</h1> -->
    <div style="text-align: right">
      <b-button
        v-if="data.manager === 1"
        @click="$router.push('/board/write')"
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
    <div v-if="articles.length">
      <table id="article-list">
        <colgroup>
          <col style="width: 5%" />
          <col style="width: 50%" />
          <col style="width: 5%" />
          <col style="width: 15%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
          </tr>
        </thead>
        <tbody>
          <board-list-item
            v-for="article in displayedArticles"
            :key="article.articleNo"
            :article="article"
          ></board-list-item>
        </tbody>
      </table>
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
import BoardListItem from "./BoardListItem.vue";

export default {
  name: "BoardList",
  components: {
    BoardListItem,
  },
  data() {
    return {
      articles: {},
      data: {
        userId: "",
        manager: "",
      },

      currentPage: 1, // 현재 페이지 번호
      perPage: 8, // 페이지당 보여줄 article 개수
      totalRows: 0, // 전체 article 개수
      displayedArticles: [],

      searchKeyword: "", // 검색어
    };
  },
  created() {
    // 현재 세션 스토리지에서 "vuex" 키의 값을 가져오기
    const vuexData = sessionStorage.getItem("vuex");
    const userData = JSON.parse(vuexData);
    // userInfo의 id 값 가져오기
    this.data.manager = userData.memberStore.userInfo.manager;
    console.log(this.data.manager);

    axios({
      method: "get",
      url: "http://localhost:8080/board/",
      responseType: "json",
    })
      .then((data) => {
        this.articles = data.data;
        this.totalRows = this.articles.length; // 전체 article 개수 설정
        this.onPageChange(1); // 초기 페이지 설정
      })
      .catch((error) => console.log(error));
  },

  methods: {
    searchArticles() {
      // 검색어에 따라 displayedArticles 필터링
      const keyword = this.searchKeyword.trim().toLowerCase();
      if (this.articles && this.articles.length > 0) {
        // articles 배열이 존재하고 비어있지 않은지 확인
        this.displayedArticles = this.articles.filter((article) => {
          return article.title && article.title.toLowerCase().includes(keyword);
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
table {
  width: 100%;
  border-top: 1px solid #444444;
  border-collapse: collapse;
}
th,
td {
  background-color: white;
  border-bottom: 1px solid #444444;
  padding: 10px;
}
</style>
