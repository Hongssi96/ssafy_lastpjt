import { createRouter, createWebHistory } from 'vue-router'

import PostListView from '@/views/PostListView.vue'
import LoginView from '@/views/LoginView.vue'
import SignUpView from '@/views/SignUpView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'PostList',
      component: PostListView,
    },
    {
      path: '/login',
      name: 'LoginPage',
      component: LoginView,
    },
    {
      path: '/signup',
      name: 'SignUp',
      component: SignUpView,
    },
  ],
})

export default router