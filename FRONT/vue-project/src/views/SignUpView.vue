<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-body">
            <h2 class="card-title text-center mb-4">회원가입</h2>
            <form @submit.prevent="signup">
              <div class="form-group mb-3">
                <label for="username" class="form-label">사용자 이름:</label>
                <input type="text" id="username" v-model="user.username" required class="form-control" />
              </div>
              <div class="form-group mb-3">
                <label for="email" class="form-label">이메일:</label>
                <input type="email" id="email" v-model="user.email" required class="form-control" />
              </div>
              <div class="form-group mb-3">
                <label for="password" class="form-label">비밀번호:</label>
                <input type="password" id="password" v-model="user.password" required class="form-control" />
              </div>
              <div class="form-group mb-3">
                <label for="confirmPassword" class="form-label">비밀번호 확인:</label>
                <input type="password" id="confirmPassword" v-model="confirmPassword" required class="form-control" />
              </div>
              <button type="submit" class="btn btn-success w-100">회원가입</button>
            </form>
            <router-link to="/login" class="d-block mt-3 text-center">
              <button class="btn btn-outline-success w-100">로그인 화면으로 돌아가기</button>
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

const user = ref({
  username: '',
  email: '',
  password: ''
});

const confirmPassword = ref('');
const router = useRouter();

async function signup() {
  if (user.value.password !== confirmPassword.value) {
    alert('비밀번호가 일치하지 않습니다.');
    return;
  }

  try {
    // 서버에 회원가입 요청을 보냅니다.
    const response = await axios.post('http://localhost:8080/api/users', user.value);
    
    if (response.status === 201) {  // HTTP 상태 코드 201 (Created) 확인
      alert('회원가입이 완료되었습니다.');
      router.push('/login');
    }
  } catch (error) {
    console.error('회원가입 실패:', error);
    alert('회원가입에 실패했습니다. 다시 시도해주세요.');
  }
}
</script>

<style scoped>
.container {
  max-width: 500px;
}

.card {
  border-radius: 8px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.card-title {
  font-size: 24px;
  color: #333;
}

.btn {
  padding: 10px;
  font-size: 16px;
  transition: background-color 0.3s;
}

.btn-outline-success:hover {
  background-color: #42b983;
  color: #ffffff;
}
</style>