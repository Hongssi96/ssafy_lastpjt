<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-body">
            <h1 class="card-title text-center mb-4">{{ loginUser }}</h1>
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>제목</th>
                  <th>좋아요</th>
                  <th>생성 시간</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="post in userPosts" :key="post.id">
                  <td>{{ post.title }}</td>
                  <td>{{ post.likes }}</td>
                  <td>{{ post.createdAt }}</td>
                </tr>
              </tbody>
            </table>
            <div class="d-flex justify-content-between mt-4">
              <button @click="logoutUser" class="btn btn-primary">로그아웃</button>
              <button @click="deleteUser" class="btn btn-danger">유저 탈퇴</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/users';
import axios from 'axios';

const router = useRouter();
const userStore = useUserStore();

const loginUser = ref(userStore.loginUser);
const userPosts = ref([]); // 유저가 작성한 게시물 목록

// 유저 탈퇴 함수
const deleteUser = async () => {
  await userStore.deleteUser();
};

// 로그아웃 함수
const logoutUser = () => {
  sessionStorage.removeItem('access-token');
  location.reload();
};

const getUserPosts = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/posts?user=${loginUser.value}`);
    userPosts.value = response.data;
  } catch (err) {
    console.error('게시물 가져오기 중 오류가 발생했습니다:', err);
  }
};

onMounted(() => {
  getUserPosts();
});
</script>

<style scoped>
.container {
  max-width: 800px;
}

.card {
  border-radius: 8px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.card-title {
  font-size: 24px;
  color: #333;
}

.table {
  margin-top: 20px;
}

.btn {
  padding: 10px;
  font-size: 16px;
  transition: background-color 0.3s;
}

.btn-danger:hover {
  background-color: #e04142;
}

.btn-primary:hover {
  background-color: #0056b3;
}
</style>
