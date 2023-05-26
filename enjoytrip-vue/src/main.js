import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

// Import Bootstrap and BootstrapVue CSS files (order is important)
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

//google map
import * as VueGoogleMaps from "vue2-google-maps"; // Import package


//font awesome
import "@/api/lib/fontAwesomeIcon.js"; // fontAwesomeIcon.js 불러옴
import "@/api/lib/vueBootstrap.js";


Vue.config.productionTip = false
// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue);
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin);

Vue.config.productionTip = false;

Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyAh8dfzpQhMLMSLpfz9Lo6WZc750-hs7f4",
    libraries: "places",
    region: "KR", // 반드시 추가하셔야됩니다.(추가 안하시면 동해가 일본해로 나타납니다.)
  },
});

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
