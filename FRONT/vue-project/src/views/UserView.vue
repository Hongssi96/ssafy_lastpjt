<template>
    <div class="user-view">
      <h1>{{ loginUser }}</h1>
      <table class="post-table">
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
      <button @click="deleteUser" class="delete-button">유저 탈퇴</button>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useRouter } from 'vue-router';
  import { useUserStore } from '@/stores/users';
  
  const router = useRouter();
  const userStore = useUserStore();
  
  const loginUser = ref(userStore.loginUser);
  const userPosts = ref([]); // 유저가 작성한 게시물 목록
  // 유저 탈퇴 함수
  const deleteUser = async () =>{
    await userStore.deleteUser();
  }
  
  const getUserPosts = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/posts?user=${loginUser.value}`);
    userPosts.value = response.data;
  } catch (err) {
    console.error('게시물 가져오기 중 오류가 발생했습니다:', err);
    }
  };

  onMounted(()=>{
    getUserPosts();
  })
  </script>
  
  <style scoped>
  .user-view {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    text-align: center;
  }
  
  .post-table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
  }
  
  .post-table th, .post-table td {
    border: 1px solid #ddd;
    padding: 8px;
  }
  
  .post-table th {
    background-color: #f2f2f2;
    font-weight: bold;
  }
  
  .delete-button {
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #ff4d4f;
    color: white;
    border: none;
    cursor: pointer;
    border-radius: 4px;
  }
  
  .delete-button:hover {
    background-color: #e04142;
  }
  </style>
  