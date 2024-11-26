<template>
  <!-- 회원가입 화면 -->
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-body">
            <!-- 회원가입 제목 -->
            <h2 class="card-title text-center mb-4">회원가입</h2>
            <!-- 회원가입 폼 -->
            <form @submit.prevent="signup">
              <!-- 사용자 이름 입력 -->
              <div class="form-group mb-3">
                <label for="username" class="form-label">사용자 이름:</label>
                <input 
                  type="text" 
                  id="username" 
                  v-model="user.username" 
                  required 
                  class="form-control" 
                />
              </div>
              <!-- 이메일 입력 -->
              <div class="form-group mb-3">
                <label for="email" class="form-label">이메일:</label>
                <input 
                  type="email" 
                  id="email" 
                  v-model="user.email" 
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
                  v-model="user.password" 
                  required 
                  class="form-control" 
                />
              </div>
              <!-- 비밀번호 확인 입력 -->
              <div class="form-group mb-3">
                <label for="confirmPassword" class="form-label">비밀번호 확인:</label>
                <input 
                  type="password" 
                  id="confirmPassword" 
                  v-model="confirmPassword" 
                  required 
                  class="form-control" 
                />
              </div>
              <!-- 회원가입 버튼 -->
              <button type="submit" class="btn btn-success w-100">회원가입</button>
            </form>
            <!-- 로그인 화면으로 돌아가기 버튼 -->
            <router-link to="/login" class="d-block mt-3 text-center">
              <button class="btn btn-outline-success w-100">
                로그인 화면으로 돌아가기
              </button>
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

// 회원가입에 필요한 사용자 정보
const user = ref({
  username: '', // 사용자 이름
  email: '',    // 이메일 주소
  password: ''  // 비밀번호
});

const confirmPassword = ref(''); // 비밀번호 확인 입력값
const router = useRouter(); // 라우터 객체

// 회원가입 함수
async function signup() {
  // 비밀번호 확인
  if (user.value.password !== confirmPassword.value) {
    alert('비밀번호가 일치하지 않습니다.');
    return;
  }

  try {
    // 서버에 회원가입 요청
    const response = await axios.post('http://localhost:8080/api/users', user.value);
    
    if (response.status === 201) { // 회원가입 성공
      alert('회원가입이 완료되었습니다.');
      router.push('/login'); // 로그인 페이지로 이동
    }
  } catch (error) {
    console.error('회원가입 실패:', error); // 오류 로그 출력
    alert('회원가입에 실패했습니다. 다시 시도해주세요.');
  }
}
</script>

<style scoped>
/* 컨테이너 스타일 */
.container {
  max-width: 500px; /* 중앙 정렬 및 너비 제한 */
}

/* 카드 스타일 */
.card {
  border-radius: 8px; /* 모서리를 둥글게 */
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); /* 부드러운 그림자 */
}

/* 카드 제목 스타일 */
.card-title {
  font-size: 24px;
  color: #333;
}

/* 버튼 기본 스타일 */
.btn {
  padding: 10px;
  font-size: 16px;
  transition: background-color 0.3s; /* 호버 효과 */
}

/* 회원가입 버튼 호버 스타일 */
.btn-outline-success:hover {
  background-color: #42b983;
  color: #ffffff;
}
</style>
