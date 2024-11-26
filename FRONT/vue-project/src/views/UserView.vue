<template>
  <!-- 사용자 정보 및 게시물 관리 화면 -->
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-body">
            <!-- 로그인한 사용자 이름 -->
            <h1 class="card-title text-center mb-4">{{ loginUser }}</h1>
            <!-- 게시물 목록 테이블 -->
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
                    <!-- 게시물 삭제 버튼 -->
                    <button 
                      @click="deletePost(post.id)" 
                      class="btn btn-danger btn-sm"
                    >삭제</button>
                  </td>
                </tr>
              </tbody>
            </table>
            <!-- 로그아웃 및 유저 탈퇴 버튼 -->
            <div class="d-flex justify-content-between mt-4">
              <button 
                @click="logoutUser" 
                class="btn btn-primary"
              >로그아웃</button>
              <button 
                @click="deleteUser" 
                class="btn btn-danger"
              >유저 탈퇴</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue';
import { useUserStore } from '@/stores/users';
import { usePostStore } from '@/stores/posts';

// Store 인스턴스
const userStore = useUserStore();
const postStore = usePostStore();

// 로그인한 사용자 이름
const loginUser = sessionStorage.getItem('loginUser');

// 유저 탈퇴 함수
const deleteUser = async () => {
  await userStore.deleteUser();
};

// 로그아웃 함수
const logoutUser = () => {
  sessionStorage.removeItem('access-token'); // 액세스 토큰 제거
  sessionStorage.removeItem('loginUserId'); // 로그인 사용자 ID 제거
  location.reload(); // 페이지 새로고침으로 상태 초기화
};

// 게시물 삭제 함수
const deletePost = async (postId) => {
  try {
    await postStore.deletePost(postId); // 게시물 삭제
    await postStore.getPostListById(); // 게시물 목록 갱신
  } catch (error) {
    console.error('게시물 삭제 중 오류 발생:', error);
  }
};

// 컴포넌트 마운트 시 사용자 게시물 목록 불러오기
onMounted(async () => {
  await postStore.getPostListById();
});
</script>

<style scoped>
/* 컨테이너 스타일 */
.container {
  max-width: 800px;
}

/* 카드 스타일 */
.card {
  border-radius: 8px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1); /* 부드러운 그림자 */
}

/* 카드 제목 스타일 */
.card-title {
  font-size: 24px;
  color: #333;
}

/* 테이블 스타일 */
.table {
  margin-top: 20px;
}

/* 버튼 스타일 */
.btn {
  padding: 10px;
  font-size: 16px;
  transition: background-color 0.3s; /* 호버 효과 */
}

/* 삭제 버튼 호버 스타일 */
.btn-danger:hover {
  background-color: #e04142; /* 강렬한 붉은색 */
}

/* 로그아웃 버튼 호버 스타일 */
.btn-primary:hover {
  background-color: #0056b3; /* 짙은 파란색 */
}
</style>
