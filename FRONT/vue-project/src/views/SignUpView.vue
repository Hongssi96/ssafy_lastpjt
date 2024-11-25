<template>
  <div>
    <h2>회원가입</h2>
    <form @submit.prevent="signup">
      <div>
        <label for="username">사용자 이름:</label>
        <input type="text" id="username" v-model="user.username" required />
      </div>
      <div>
        <label for="email">이메일:</label>
        <input type="email" id="email" v-model="user.email" required />
      </div>
      <div>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <div>
        <label for="confirmPassword">비밀번호 확인:</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" required />
      </div>
      <button type="submit">회원가입</button>
    </form>
    <router-link to="/login"><button>로그인 화면으로 돌아가기</button></router-link>
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
/* 스타일은 그대로 유지 */
form {
  display: flex;
  flex-direction: column;
  align-items: center;
}

label {
  margin-top: 10px;
}

input {
  margin-bottom: 10px;
  padding: 5px;
  font-size: 16px;
}

button {
  padding: 10px 20px;
  background-color: #42b983;
  color: #fff;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}

button:hover {
  background-color: #369b72;
}
</style>
