import Vue from 'vue'
import VueRouter from 'vue-router' 

import Board from "@/views/AppBoard.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardView from "@/components/board/BoardView.vue";
import BoardDelete from "@/components/board/BoardDelete.vue";
import BoardModify from  "@/components/board/BoardModify.vue";

import User from "@/views/AppUser.vue";
import UserLogin from "@/components/user/UserLogin.vue";
import UserJoin from "@/components/user/UserJoin.vue";

import Hotplace from "@/views/AppHotplace.vue";
import HotplaceList from "@/components/hotplace/HotplaceList.vue";
import HotplaceWrite from "@/components/hotplace/HotplaceWrite.vue";
import HotplaceView from "@/components/hotplace/HotplaceView.vue";
import HotplaceDelete from "@/components/hotplace/HotplaceDelete.vue";
import HotplaceModify from  "@/components/hotplace/HotplaceModify.vue";

Vue.use(VueRouter)

const routes = [ 
  {
    path: '/user',
    name: 'user',
    component: User, 
    children: [
      {
        path: 'login',
        name: 'userlogin',
        component: UserLogin,
      },
      {
        path: 'join',
        name: 'joinuser',
        component: UserJoin,
      },
    ]
  },
  {
    path: '/board',
    name: 'board',
    component: Board,
    redirect: '/board/list', 
    children: [
      {
        path: 'list',
        name: 'boardlist',
        component: BoardList,
      },
      {
        path: 'write',
        name: 'boardwrite',
        component: BoardWrite,
      },
      {
        path: 'detail/:no',
        name: 'boardview',
        component: BoardView,
      },
      {
        path: 'delete/:no',
        name: 'boarddelete',
        component: BoardDelete,
      },
      {
        path: 'modify/:no',
        name: 'boardmodify',
        component: BoardModify,
      },
    ],
  },
  {
    path: '/hotplace',
    name: 'hotplace',
    redirect: '/hotplace/list', 
    component: Hotplace, 
    children: [
      {
        path: 'list',
        name: 'hotplacelist',
        component: HotplaceList,
      },
      {
        path: 'write',
        name: 'hotplacewrite',
        component: HotplaceWrite,
      },
      {
        path: 'detail/:no',
        name: 'hotplaceview',
        component: HotplaceView,
      },
      {
        path: 'delete/:no',
        name: 'hotplacedelete',
        component: HotplaceDelete,
      },
      {
        path: 'modify/:no',
        name: 'hotplacemodify',
        component: HotplaceModify,
      },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
