<template>
  <div id="app">
    <HeaderComponent @navigateToCreatePost="navigateToCreatePost" />
    <router-view v-if="true" />
    <router-link to="/login" v-else />
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import { useAuthStore } from './stores/auth';
import HeaderComponent from './components/HeaderComponent.vue';

const router = useRouter();
const authStore = useAuthStore();
const isAuthenticated = ref(authStore.isAuthenticated);

function scrollToTopPost() {
  router.push('/');
  window.scrollTo({ top: 0, behavior: 'smooth' });
}

function logout() {
  authStore.logout();
  router.push('/login');
}

function navigateToCreatePost() {
  router.push('/create-post');
}
</script>

<style scoped>
#app {
  text-align: center;
  margin-top: 50px; /* 헤더 높이만큼 여백 추가 */
}
</style>