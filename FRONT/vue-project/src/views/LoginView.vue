<template>
    <div>
      <h2>로그인</h2>
      <form @submit.prevent="login">
        <div>
          <label for="email">이메일:</label>
          <input type="email" id="email" v-model="email" required />
        </div>
        <div>
          <label for="password">비밀번호:</label>
          <input type="password" id="password" v-model="password" required />
        </div>
        <button type="submit">로그인</button>
      </form>
      <router-link to="/signup"><button>회원가입</button></router-link>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';
  
  const email = ref('');
  const password = ref('');
  const router = useRouter();
  
  function login() {
    if (email.value && password.value) {
      localStorage.setItem('user', JSON.stringify({ email: email.value }));
      router.push('/');
    } else {
      alert('이메일과 비밀번호를 입력해주세요.');
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
  }
  
  button:hover {
    background-color: #369b72;
  }
  </style>