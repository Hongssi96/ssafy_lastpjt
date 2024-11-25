<template>
    <header id="header">
      <div class="header-content">
        <h1 @click="navigateToMain" class="app-title">FitTogether</h1>
        <nav v-if="true">
          <button @click="navigateToUserView" class="btn btn-secondary">My</button>
          <button @click="navigateToCreatePost" class="btn btn-secondary">+</button>
        </nav>
      </div>
    </header>
  </template>
  
  <script setup>
  import { useRouter } from 'vue-router';
  
  const router = useRouter();
  

  
  function navigateToMain() {
    router.push('/');
  }

  function navigateToCreatePost() {
    router.push('/create-post');
  }

  function navigateToUserView() {
    router.push('/user');
  }
  </script>
  
  <style scoped>
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
  
  .header-content {
    display: flex;
    justify-content: space-between;
    align-items: center;
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;
  }
  
  .plus-button {
    background-color: 007bff;
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
  
  .app-title {
    font-size: 24px;
    color: #42b983;
    font-weight: bold;
    margin: 0;
  }
  </style>{}