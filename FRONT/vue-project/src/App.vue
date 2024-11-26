<template>
  <div id="app">
    <!-- 헤더 컴포넌트 -->
    <HeaderComponent @navigateToCreatePost="navigateToCreatePost" />
    <!-- 라우터 뷰 -->
    <router-view v-if="true" />
    <!-- 로그인 페이지로 이동 링크 (조건에 따라 표시) -->
    <router-link to="/login" v-else />
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import HeaderComponent from './components/HeaderComponent.vue';

// Vue Router 인스턴스
const router = useRouter();

// 페이지 상단으로 스크롤 및 홈으로 이동
function scrollToTopPost() {
  router.push('/'); // 홈으로 라우팅
  window.scrollTo({ top: 0, behavior: 'smooth' }); // 부드럽게 스크롤
}

// 로그아웃 처리
function logout() {
  authStore.logout(); // 인증 스토어에서 로그아웃 처리
  router.push('/login'); // 로그인 페이지로 이동
}

// 게시물 작성 페이지로 이동
function navigateToCreatePost() {
  router.push('/create-post');
}
</script>

<style scoped>
#app {
  text-align: center;
  margin-top: 60px; /* 헤더 높이만큼 여백 추가 */
}
</style>
