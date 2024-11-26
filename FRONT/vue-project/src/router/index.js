import { createRouter, createWebHistory } from 'vue-router'

// 뷰 컴포넌트 임포트
import PostListView from '@/views/PostListView.vue'
import LoginView from '@/views/LoginView.vue'
import SignUpView from '@/views/SignUpView.vue'
import CreatePostComponent from '@/components/CreatePostComponent.vue'
import UserView from '@/views/UserView.vue'

// 라우터 설정
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL), // HTML5 히스토리 모드를 사용
  routes: [
    {
      path: '/', // 메인 화면 (게시물 목록)
      name: 'PostList',
      component: PostListView,
    },
    {
      path: '/create-post', // 게시물 작성 페이지
      name: 'CreatePost',
      component: CreatePostComponent,
    },
    {
      path: '/user', // 사용자 정보 페이지
      name: 'User',
      component: UserView,
    },
    {
      path: '/login', // 로그인 페이지
      name: 'Login',
      component: LoginView,
    },
    {
      path: '/signup', // 회원가입 페이지
      name: 'SignUp',
      component: SignUpView,
    },
  ],
})

// 전역 가드 설정
router.beforeEach((to, from, next) => {
  // 로그인 및 회원가입 페이지를 제외한 모든 페이지에 대해 인증 확인
  if (to.name !== 'Login' && to.name !== 'SignUp' && !sessionStorage.getItem('access-token')) {
    next({ name: 'Login' }); // 토큰이 없으면 로그인 페이지로 리다이렉트
  } else {
    next(); // 인증이 되어 있거나 로그인/회원가입 페이지일 경우 통과
  }
});

export default router
