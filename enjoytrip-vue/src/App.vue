<template>
  <div id="app">
    <the-header></the-header>
    <!-- <app-board></app-board> --> 
    <router-view></router-view>
  </div>
</template>

<script type="module" src="/router/index.js"></script>
<script>
import TheHeader from "./components/TheHeader.vue";
import AppBoard from "./views/AppBoard.vue";
 
export default ({
  components : {
    TheHeader, 
    AppBoard
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
