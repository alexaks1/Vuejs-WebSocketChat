import Vue from 'vue'
import VueRouter from 'vue-router'
import Chat from '../components/Chat.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/chats',
    name: 'chat',
    component: Chat
  },
  {
    path: '/admin',
    name: 'admin',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "Admin" */ '../components/Admin.vue')
  },
  {
    path: '/auth/sign-in',
    name: 'auth',
    component: () => import(/* webpackChunkName: "Auth" */ '../components/Auth.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
