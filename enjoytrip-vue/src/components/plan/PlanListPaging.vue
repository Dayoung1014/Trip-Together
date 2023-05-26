<template>
  <div>
    <div class="d-flex justify-content-center" style="width: 100%">
      <div class="col">
        <b-button
          @click="loadAll()"
          :class="{ 'btn-selected': selectedButton === 'all' }"
          class="btn m-0"
          variant="outline-dark"
          style="width: 20%; border-radius: 1rem 0 0 1rem"
        >
          나의 전체 Plan
        </b-button>
        <b-button
          @click="loadmadeByMe()"
          :class="{ 'btn-selected': selectedButton === 'madeByMe' }"
          class="btn m-0 rounded-0"
          variant="outline-dark"
          style="width: 20%"
        >
          내가 만든 Plan
        </b-button>
        <b-button
          @click="loadJoin()"
          :class="{ 'btn-selected': selectedButton === 'join' }"
          class="btn m-0"
          variant="outline-dark"
          style="width: 20%; border-radius: 0 1rem 1rem 0"
        >
          내가 참여한 Plan
        </b-button>
      </div>
    </div>

    <div class="row">
      <div class="col-6" style="display: flex; align-items: center">
        <label class="switch">
          <input type="checkbox" @click="toggleCheckbox" />
          <div class="slider round"></div>
        </label>
        <div style="display: flex; flex-direction: column; margin-left: 10px">
          <p v-if="this.dayBefore" style="font-size: 15px">
            앞으로의 Plan 조회
          </p>
          <p v-else style="font-size: 15px">전체 Plan 조회</p>
        </div>
      </div>
      <div class="col-6" style="text-align: right">
        <b-button
          @click="$router.push('/plan/write')"
          class="btn"
          variant="outline-dark"
          style="width: 20%"
        >
          일정 등록
        </b-button>
      </div>
    </div>
    <div v-if="articles.length">
      <table id="article-list">
        <colgroup>
          <col style="width: 5%" />
          <col style="width: 50%" />
          <col style="width: 10%" />
          <col style="width: 15%" />
          <col style="width: 15%" />
        </colgroup>
        <thead>
          <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>시작일</th>
            <th>종료일</th>
          </tr>
        </thead>
        <tbody>
          <plan-list-item
            v-for="article in displayedArticles"
            :key="article.id"
            :article="article"
          ></plan-list-item>
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
import PlanListItem from "./PlanListItem.vue";

export default {
  name: "PlanList",
  components: {
    PlanListItem,
  },
  data() {
    return {
      user_id: "",
      articles: [],
      madeByMeChecked: true,
      joinedPlanChecked: true,
      selectedButton: "all",

      dayBefore: false,

      currentPage: 1, // 현재 페이지 번호
      perPage: 2, // 페이지당 보여줄 article 개수
      totalRows: 0, // 전체 article 개수
      displayedArticles: [],
    };
  },
  created() {
    // 현재 세션 스토리지에서 "vuex" 키의 값을 가져오기
    const vuexData = sessionStorage.getItem("vuex");

    const userData = JSON.parse(vuexData);
    console.log(userData);
    // userInfo의 id 값 가져오기
    this.user_id = userData.memberStore.userInfo.id;
    console.log(this.user_id);

    // axios.get(`http://localhost:8080/plan/${this.user_id}`)
    // .then(response => {
    //   console.log(response)
    //   this.articles = response.data;
    // })
    // .catch(error => {
    //   console.error(error);
    // });
    this.loadAll();
  },
  methods: {
    toggleCheckbox() {
      this.dayBefore = !this.dayBefore;
      if (this.selectedButton === "all") this.loadAll();
      else if (this.selectedButton === "madeByMe") this.loadmadeByMe();
      else if (this.selectedButton === "join") this.loadJoin();
    },

    loadAll() {
      axios
        .get(`http://localhost:8080/plan/${this.user_id}`)
        .then((response) => {
          this.articles = response.data;
          this.totalRows = this.articles.length; // 전체 article 개수 설정

          axios
            .get(`http://localhost:8080/planuser/user/${this.user_id}`)
            .then((response) => {
              this.articles = this.articles.concat(response.data);
              this.totalRows += response.data.length; // 추가 article 개수 설정

              if (this.dayBefore) {
                const currentDate = new Date();
                this.articles = this.articles.filter((article) => {
                  const endDate = new Date(article.endDate);
                  return endDate >= currentDate;
                });
              }

              this.onPageChange(1); // 초기 페이지 설정
            })
            .catch((error) => {
              console.error(error);
            });
        })
        .catch((error) => {
          console.error(error);
        });
    },
    onPageChange(newPage) {
      console.log(newPage);
      console.log(this.articles);
      this.currentPage = newPage;
      // 현재 페이지에 해당하는 article 범위 설정
      const startIndex = (newPage - 1) * this.perPage;
      const endIndex = startIndex + this.perPage;
      this.displayedArticles = this.articles.slice(startIndex, endIndex);
    },
    loadmadeByMe() {
      this.selectedButton = "madeByMe";
      console.log(this.selectedButton);
      axios
        .get(`http://localhost:8080/plan/${this.user_id}`)
        .then((response) => {
          this.articles = response.data;
          if (response.data.length > 0) {
            if (this.dayBefore) {
              const currentDate = new Date();
              this.articles = this.articles.filter((article) => {
                const endDate = new Date(article.endDate);
                console.log(currentDate);
                console.log(endDate);
                return endDate >= currentDate;
              });
            }
          }
          this.onPageChange(1);
        })
        .catch((error) => {
          console.error(error);
        });
    },
    loadJoin() {
      this.selectedButton = "join";
      console.log(this.selectedButton);
      axios
        .get(`http://localhost:8080/planuser/user/${this.user_id}`)
        .then((response) => {
          this.articles = response.data;
          if (response.data.length > 0) {
            if (this.dayBefore) {
              const currentDate = new Date();
              this.articles = this.articles.filter((article) => {
                const endDate = new Date(article.endDate);
                return endDate >= currentDate;
              });
            }
          }
          this.onPageChange(1);
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style>
.switch {
  position: relative;
  display: inline-block;
  width: 60px;
  height: 34px;
}

.switch input {
  display: none;
}

.slider {
  position: absolute;
  cursor: pointer;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: #ccc;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

.slider:before {
  position: absolute;
  content: "";
  height: 26px;
  width: 26px;
  left: 4px;
  bottom: 4px;
  background-color: white;
  -webkit-transition: 0.4s;
  transition: 0.4s;
}

input:checked + .slider {
  background-color: #101010;
}

input:focus + .slider {
  box-shadow: 0 0 1px #101010;
}

input:checked + .slider:before {
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}

.slider.round {
  border-radius: 34px;
}

.slider.round:before {
  border-radius: 50%;
}

.btn-selected {
  background-color: black !important; /* 변경된 스타일 */
  color: white !important; /* 변경된 스타일 */
}

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
