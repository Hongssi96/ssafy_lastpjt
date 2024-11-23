<template>
  <div id="app">
    <header id="header" v-if="isAuthenticated" @click="scrollToTopPost">
      <div class="header-content">
        <h1 class="app-title">FitTogether</h1>
        <nav>
          <router-link to="/">게시글 목록</router-link> |
          <button @click="logout">로그아웃</button>
        </nav>
      </div>
    </header>
    <router-view />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const isAuthenticated = ref(!!localStorage.getItem('user'));

function scrollToTopPost() {
  router.push('/');
  window.scrollTo({ top: 0, behavior: 'smooth' });
}

function logout() {
  localStorage.removeItem('user');
  isAuthenticated.value = false;
  router.push('/login');
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

.app-title {
  font-size: 24px;
  color: #42b983;
  font-weight: bold;
  margin: 0;
}

#app {
  text-align: center;
  margin-top: 100px;
}

nav {
  margin-bottom: 20px;
}

nav a {
  margin: 0 10px;
  text-decoration: none;
  color: #42b983;
}

nav a.router-link-exact-active {
  font-weight: bold;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  margin-bottom: 20px;
}

img {
  max-width: 100%;
  height: auto;
}

button {
  padding: 8px 16px;
  background-color: #42b983;
  color: #fff;
  border: none;
  cursor: pointer;
  border-radius: 4px;
}

button:hover {
  background-color: #369b72;
}

/* 모바일 화면에 최적화된 스타일 */
@media (max-width: 600px) {
  #app {
    margin: 10px;
  }

  nav {
    font-size: 14px;
  }

  h1, h2 {
    font-size: 24px;
  }

  .header-content {
    flex-direction: column;
  }

  .app-title {
    margin-bottom: 10px;
  }
}
</style>