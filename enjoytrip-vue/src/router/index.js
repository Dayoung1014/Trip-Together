import Vue from 'vue'
import VueRouter from 'vue-router' 

import User from "@/views/AppUser.vue";
import Board from "@/views/AppBoard.vue";
import BoardList from "@/components/board/BoardList.vue";
import BoardWrite from "@/components/board/BoardWrite.vue";
import BoardView from "@/components/board/BoardView.vue";
import BoardDelete from "@/components/board/BoardDelete.vue";
import BoardModify from  "@/components/board/BoardModify.vue";

Vue.use(VueRouter)

const routes = [ 
  {
    path: '/user',
    name: 'user',
    component : User
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
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
