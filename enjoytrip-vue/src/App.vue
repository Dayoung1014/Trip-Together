<template>
  <div id="app">
    <the-header></the-header>
    <div class="router-view-container m-0">
      <router-view></router-view>
    </div>
    <div class="py3-3 mt-auto">
      <the-footer></the-footer>
    </div>
  </div>
</template>

<script type="module" src="/router/index.js"></script>
<script>
import TheFooter from "./components/TheFooter.vue";
import TheHeader from "./components/TheHeader.vue";

export default {
  components: {
    TheHeader,
    TheFooter,
  },
};
</script>

<style scoped>
#app {
  margin-top: 0px !important;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* position: relative; */
}

a {
  text-decoration: none;
}

.router-view-container {
  /* padding: 100px 100px; */
  padding: 0 100px;
  position: relative; /* 추가 */
  height: 140vh; /* 추가 */
}
</style>
