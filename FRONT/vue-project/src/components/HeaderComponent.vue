<template>
  <!-- 헤더 영역 -->
  <header id="header">
    <div class="header-content">
      <!-- 애플리케이션 제목 클릭 시 메인 화면으로 이동 -->
      <h1 @click="navigateToMain" class="app-title">FitTogether</h1>
      <!-- 네비게이션 버튼 -->
      <nav v-if="true">
        <!-- 사용자 정보 화면으로 이동 버튼 -->
        <button @click="navigateToUserView" class="btn btn-secondary">My</button>
        <!-- 게시물 작성 화면으로 이동 버튼 -->
        <button @click="navigateToCreatePost" class="btn btn-secondary">+</button>
      </nav>
    </div>
  </header>
</template>

<script setup>
import { useRouter } from 'vue-router';

// Vue Router 인스턴스
const router = useRouter();

// 메인 화면으로 이동
function navigateToMain() {
  router.push('/');
}

// 게시물 작성 화면으로 이동
function navigateToCreatePost() {
  router.push('/create-post');
}

// 사용자 정보 화면으로 이동
function navigateToUserView() {
  router.push('/user');
}
</script>

<style scoped>
/* 헤더 스타일 */
#header {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  background-color: #ffffff;
  border-bottom: 1px solid #eaeaea;
  padding: 10px;
  text-align: center;
  z-index: 1000;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

/* 헤더 내부 컨텐츠 스타일 */
.header-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 20px;
}

/* 앱 제목 스타일 */
.app-title {
  font-size: 24px;
  color: #42b983;
  font-weight: bold;
  margin: 0;
  cursor: pointer;
}

/* 네비게이션 버튼 스타일 */
.btn {
  padding: 10px 15px;
  font-size: 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.btn-secondary {
  background-color: #6c757d;
  color: #ffffff;
}

.btn-secondary:hover {
  background-color: #5a6268;
  transform: scale(1.05);
}

/* 플러스 버튼 스타일 (사용하지 않음) */
.plus-button {
  background-color: #007bff;
  color: #ffffff;
  border: none;
  padding: 10px;
  border-radius: 50%;
  font-size: 24px;
  width: 50px;
  height: 50px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.plus-button:hover {
  background-color: #0056b3;
  transform: scale(1.1);
}
</style>
