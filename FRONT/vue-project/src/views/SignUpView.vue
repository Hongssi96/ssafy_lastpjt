<template>
    <div>
      <h2>회원가입</h2>
      <form @submit.prevent="signup">
        <div>
          <label for="username">사용자 이름:</label>
          <input type="text" id="username" v-model="username" required />
        </div>
        <div>
          <label for="email">이메일:</label>
          <input type="email" id="email" v-model="email" required />
        </div>
        <div>
          <label for="password">비밀번호:</label>
          <input type="password" id="password" v-model="password" required />
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
  
  const username = ref('');
  const email = ref('');
  const password = ref('');
  const confirmPassword = ref('');
  const router = useRouter();
  
  function signup() {
    if (password.value !== confirmPassword.value) {
      alert('비밀번호가 일치하지 않습니다.');
      return;
    }
  
    if (username.value && email.value && password.value) {
      // 서버로 회원가입 정보 전송 (예시: 실제 서버와 통신 필요)
      localStorage.setItem('user', JSON.stringify({ username: username.value, email: email.value }));
      router.push('/');
    } else {
      alert('모든 필드를 입력해주세요.');
    }
  }
  </script>
  
  <style scoped>
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