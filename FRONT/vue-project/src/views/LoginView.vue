<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card">
          <div class="card-body">
            <h2 class="card-title text-center mb-4">로그인</h2>
            <form @submit.prevent="login">
              <div class="form-group mb-3">
                <label for="email" class="form-label">이메일:</label>
                <input type="email" id="email" v-model.trim="email" required class="form-control" />
              </div>
              <div class="form-group mb-3">
                <label for="password" class="form-label">비밀번호:</label>
                <input type="password" id="password" v-model.trim="password" required class="form-control" />
              </div>
              <button type="submit" class="btn btn-success w-100">로그인</button>
            </form>
            <button @click="goToSignup" class="btn btn-outline-success w-100 mt-3">회원가입</button>
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

const email = ref('');
const password = ref('');
const postStore = usePostStore();
const router = useRouter();
const store = useUserStore();
const login = function() {
  store.userLogin(email.value, password.value);
};
const goToSignup = () => {
router.push('/signup');
};
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
