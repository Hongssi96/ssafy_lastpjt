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
                  <th>내용</th>
                  <th>좋아요</th>
                  <th>삭제</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="post in postStore.userPosts" :key="post.id">
                  <td>{{ post.description }}</td>
                  <td>{{ post.likes }}</td>
                  <td>
                    <button @click="deletePost(post.id)" class="btn btn-danger btn-sm">삭제</button>
                  </td>
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
import { ref, onMounted, computed } from 'vue';
import { useUserStore } from '@/stores/users';
import { usePostStore } from '@/stores/posts';

const userStore = useUserStore();
const postStore = usePostStore();

const loginUser = sessionStorage.getItem('loginUser');

// 유저 탈퇴 함수
const deleteUser = async () => {
  await userStore.deleteUser();
};

// 로그아웃 함수
const logoutUser = () => {
  sessionStorage.removeItem('access-token');
  sessionStorage.removeItem('loginUserId')
  location.reload();
};

// 게시물 삭제 함수
const deletePost = async (postId) => {
  try {
    await postStore.deletePost(postId);
    await postStore.getPostListById(); // 게시물 목록을 다시 불러오기
  } catch (error) {
    console.error('게시물 삭제 중 오류 발생:', error);
  }
};
onMounted(async () => {
  await postStore.getPostListById();
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
