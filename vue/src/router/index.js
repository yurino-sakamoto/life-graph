import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Top from '../views/Top.vue'
import Edit from '../views/Edit.vue'
import Search from '../views/Search.vue'
import Result from '../views/Result.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/',
    name: 'Top',
    component: Top
  },
  {
    path: '/edit',
    name: 'Edit',
    component: Edit

  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
  {
    path: '/result',
    name: 'Result',
    component: Result
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
