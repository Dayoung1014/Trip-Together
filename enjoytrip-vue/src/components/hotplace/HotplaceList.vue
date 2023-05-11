<template>
  <div>
    <h1 class="underline">도서 목록</h1>
    <div style="text-align: right">
      <button @click="$router.push('/hotplace/write')" class="btn btn-primary">
        도서 등록
      </button>
    </div>
    <div v-if="articles.length">
      <table id="article-list">
        <colgroup>
          <col style="width: 5%" />
          <col style="width: 65%" />
          <col style="width: 10%" />
          <col style="width: 5%" />
          <col style="width: 15%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>방문일</th> 
            <th>작성일</th>
          </tr>
        </thead>
        <tbody>
          <hotplace-list-item
            v-for="article in articles"
            :key="article.id"
            :article="article"
          ></hotplace-list-item>
        </tbody>
      </table>
    </div>
    <div v-else class="text-center">게시글이 없습니다.</div>
  </div>
</template>

<script>
import axios from "axios";
import HotplaceListItem from "./HotplaceListItem.vue";

export default {
  name: "HotplaceList",
  components: {
    HotplaceListItem,
  },
  data() {
    return {
      articles: {},
    };
  },
  created() {
    axios.get('http://localhost:8080/hotplace/')
  .then(response => { 
    this.articles = response.data.data;
  })
  .catch(error => {
    console.error(error);
  });
  },
};
</script>

<style></style>
