import Vue from "vue";
import VueRouter from "vue-router";

import Home from "@/views/HomeView.vue";

import Map from "@/views/AppMap.vue";

import Board from "@/views/AppBoard.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardView from "@/components/board/BoardView.vue";
import BoardDelete from "@/components/board/BoardDelete.vue";
import BoardModify from "@/components/board/BoardModify.vue";

import Hotplace from "@/views/AppHotplace.vue";
import HotplaceList from "@/components/hotplace/HotplaceListCard.vue";
import HotplaceWrite from "@/components/hotplace/HotplaceWrite.vue";
import HotplaceView from "@/components/hotplace/HotplaceView.vue";
import HotplaceDelete from "@/components/hotplace/HotplaceDelete.vue";
import HotplaceModify from "@/components/hotplace/HotplaceModify.vue";

import Plan from "@/views/AppPlan.vue";
import PlanList from "@/components/plan/PlanList.vue";
import PlanWrite from "@/components/plan/PlanWrite.vue";
import PlanView from "@/components/plan/PlanView.vue";
import PlanDelete from "@/components/plan/PlanDelete.vue";
import PlaneModify from "@/components/plan/PlanModify.vue";
import PlanCommunityList from "@/components/plan/PlanCommunityList.vue";
import PlanShareView from "@/components/plan/PlanShareView.vue";

import store from "@/store";

Vue.use(VueRouter);

//https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.getters["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");
  console.log("로그인 처리 전", checkUserInfo, token);
  if (checkUserInfo != null && token) {
    console.log("토큰 유효성 체크하러 가자!!!!");
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    router.go(0); // 현재 페이지 새로고침
  } else {
    console.log("로그인 했다!!!!!!!!!!!!!.");
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "home",
    component: Home,
  },
  {
    path: "/search",
    name: "map",
    component: Map,
  },
  {
    path: "/board",
    name: "board",
    component: Board,
    redirect: "/board/list",
    beforeEnter: onlyAuthUser,
    children: [
      {
        path: "list",
        name: "boardlist",
        component: BoardList,
      },
      {
        path: "write",
        name: "boardwrite",
        component: BoardWrite,
      },
      {
        path: "detail/:no",
        name: "boardview",
        component: BoardView,
      },
      {
        path: "delete/:no",
        name: "boarddelete",
        component: BoardDelete,
      },
      {
        path: "modify/:no",
        name: "boardmodify",
        component: BoardModify,
      },
    ],
  },
  {
    path: "/hotplace",
    name: "hotplace",
    redirect: "/hotplace/list",
    component: Hotplace,
    beforeEnter: onlyAuthUser,
    children: [
      {
        path: "list",
        name: "hotplacelist",
        component: HotplaceList,
      },
      {
        path: "write",
        name: "hotplacewrite",
        component: HotplaceWrite,
      },
      {
        path: "detail/:no",
        name: "hotplaceview",
        component: HotplaceView,
      },
      {
        path: "delete/:no",
        name: "hotplacedelete",
        component: HotplaceDelete,
      },
      {
        path: "modify/:no",
        name: "hotplacemodify",
        component: HotplaceModify,
      },
    ],
  },
  {
    path: "/plan",
    name: "plan",
    redirect: "/plan/list",
    component: Plan,
    beforeEnter: onlyAuthUser,
    children: [
      {
        path: "list",
        name: "planlist",
        component: PlanList,
      },
      {
        path: "community",
        name: "plancommunitylist",
        component: PlanCommunityList,
      },
      {
        path: "write",
        name: "planwrite",
        component: PlanWrite,
      },
      {
        path: "detail/:no",
        name: "planview",
        component: PlanView,
      },
      {
        path: "delete/:no",
        name: "plandelete",
        component: PlanDelete,
      },
      {
        path: "modify/:no",
        name: "planmodify",
        component: PlaneModify,
      },
      {
        path: "share/:no",
        name: "planshareview",
        component: PlanShareView,
      },
    ],
  },
];

const router = new VueRouter({
  //mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
