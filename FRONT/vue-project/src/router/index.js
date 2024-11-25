import { createRouter, createWebHistory } from 'vue-router'

import PostListView from '@/views/PostListView.vue'
import LoginView from '@/views/LoginView.vue'
import SignUpView from '@/views/SignUpView.vue'
import CreatePostComponent from '@/components/CreatePostComponent.vue'
import UserView from '@/views/UserView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'PostList',
      component: PostListView,
    },
    {
      path: '/create-post',
      name: 'CreatePost',
      component: CreatePostComponent,
    },
    {
      path: '/user',
      name: 'User',
      component: UserView,
    },
    {
      path: '/login',
      name: 'Login',
      component: LoginView,
    },
    {
      path: '/signup',
      name: 'SignUp',
      component: SignUpView,
    },
  ],
})

// 전역 가드 설정
router.beforeEach((to, from, next) => {
  // 로그인 페이지가 아니면서 토큰이 없으면 로그인 페이지로 이동
  if (to.name !== 'Login' && !sessionStorage.getItem('access-token')) {
    next({ name: 'Login' });
  } else {
    next();
  }
});
export default router
