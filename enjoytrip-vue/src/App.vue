<template>
  <div id="app">
    <the-header></the-header> 
    <router-view></router-view>
    <the-footer></the-footer>
  </div>
</template>

<script type="module" src="/router/index.js"></script>
<script>
import TheFooter from "./components/TheFooter.vue";
import TheHeader from "./components/TheHeader.vue";
import AppBoard from "./views/AppBoard.vue";
import AppHotplace from "./views/AppHotplace.vue";
import AppUser from "./views/AppUser.vue";
  
 
export default ({
  components : {
    TheHeader,
    TheFooter, 
    AppBoard,
    AppHotplace,
    AppUser
  }
});
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

a {
  text-decoration: none;
}
</style>
