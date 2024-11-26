<template>
  <!-- 로그인 화면 -->
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-body">
            <!-- 로그인 제목 -->
            <h2 class="card-title text-center mb-4">로그인</h2>
            <!-- 로그인 폼 -->
            <form @submit.prevent="login">
              <!-- 이메일 입력 -->
              <div class="form-group mb-3">
                <label for="email" class="form-label">이메일:</label>
                <input 
                  type="email" 
                  id="email" 
                  v-model.trim="email" 
                  required 
                  class="form-control" 
                />
              </div>
              <!-- 비밀번호 입력 -->
              <div class="form-group mb-3">
                <label for="password" class="form-label">비밀번호:</label>
                <input 
                  type="password" 
                  id="password" 
                  v-model.trim="password" 
                  required 
                  class="form-control" 
                />
              </div>
              <!-- 로그인 버튼 -->
              <button type="submit" class="btn btn-success w-100">로그인</button>
            </form>
            <!-- 회원가입 이동 버튼 -->
            <button 
              @click="goToSignup" 
              class="btn btn-outline-success w-100 mt-3"
            >회원가입</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/users';
import { usePostStore } from '@/stores/posts';

// 로그인 입력값
const email = ref(''); // 이메일
const password = ref(''); // 비밀번호

// Pinia Store 및 Vue Router
const postStore = usePostStore(); // 게시물 Store (미래 확장 가능)
const router = useRouter(); // 라우터 객체
const store = useUserStore(); // 사용자 Store

// 로그인 함수
const login = function() {
  store.userLogin(email.value, password.value); // 사용자 Store에서 로그인 호출
};

// 회원가입 페이지로 이동
const goToSignup = () => {
  router.push('/signup'); // 회원가입 페이지로 라우팅
};
</script>

<style scoped>
/* 컨테이너 스타일 */
.container {
  max-width: 500px; /* 화면 중앙에 500px 크기 제한 */
}

/* 카드 스타일 */
.card {
  border-radius: 8px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); /* 부드러운 그림자 */
}

/* 카드 제목 스타일 */
.card-title {
  font-size: 24px;
  color: #333;
}

/* 버튼 스타일 */
.btn {
  padding: 10px;
  font-size: 16px;
  transition: background-color 0.3s; /* 배경색 전환 애니메이션 */
}

/* 회원가입 버튼 호버 스타일 */
.btn-outline-success:hover {
  background-color: #42b983; /* Vue.js 기본 색상 */
  color: #ffffff;
}
</style>
