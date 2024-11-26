<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-body">
            <!-- 게시물 작성 제목 -->
            <h2 class="card-title text-center mb-4">게시물 작성하기</h2>
            <!-- 게시물 작성 폼 -->
            <form @submit.prevent="uploadImageAndCreatePost">
              <!-- 이미지 업로드 입력 -->
              <div class="form-group mb-3">
                <label for="image" class="form-label">이미지 업로드:</label>
                <input 
                  type="file" 
                  id="image" 
                  @change="handleFileChange" 
                  required 
                  class="form-control" 
                />
              </div>
              <!-- 게시물 내용 입력 -->
              <div class="form-group mb-3">
                <label for="description" class="form-label">내용:</label>
                <textarea 
                  id="description" 
                  v-model="post.description" 
                  required 
                  class="form-control"
                ></textarea>
              </div>
              <!-- 버튼 그룹 -->
              <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-success">게시물 작성</button>
                <button 
                  type="button" 
                  @click="goToPostView" 
                  class="btn btn-secondary"
                >닫기</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { usePostStore } from '../stores/posts';
import { useRouter } from 'vue-router';
import axios from 'axios';

// Vue Router 객체
const router = useRouter();
// 게시물 관리를 위한 Pinia Store
const postStore = usePostStore();
// 현재 로그인한 사용자 ID를 세션에서 가져옴
const id = sessionStorage.getItem('loginUserId');

// 게시물 데이터 초기화
const post = ref({
  userId: id, // 사용자 ID
  description: '', // 게시물 내용
  imageUrl: '', // 업로드된 이미지 URL
});

// 사용자가 선택한 이미지 파일을 저장
const imageFile = ref(null); 

// 파일 선택 시 호출되는 핸들러
function handleFileChange(event) {
  const file = event.target.files[0];
  if (file) {
    imageFile.value = file; // 선택된 파일을 저장
  }
}

// 이미지 업로드 및 게시물 생성
async function uploadImageAndCreatePost() {
  // 이미지가 선택되지 않은 경우
  if (!imageFile.value) {
    alert('이미지를 업로드하세요.');
    return;
  }

  try {
    // FormData에 이미지 파일 추가
    const formData = new FormData();
    formData.append('key', '0413020ffd7983a9cfd98d1860b9b659'); // 이미지 업로드 API 키
    formData.append('image', imageFile.value);

    // 이미지 업로드 API 호출
    const response = await axios.post('https://api.imgbb.com/1/upload', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    });

    // 업로드된 이미지의 URL을 가져옴
    const imageUrl = response.data.data.url;
    post.value.imageUrl = imageUrl;

    // 게시물 생성 요청
    await postStore.createPost(post.value);

    // 홈 화면으로 이동
    router.push('/');
  } catch (error) {
    console.error('이미지 업로드 중 오류 발생:', error);
    alert('이미지 업로드에 실패했습니다.');
  }
}

// 게시물 작성 취소 시 홈 화면으로 이동
function goToPostView() {
  router.push('/');
}
</script>

<style scoped>
/* 컨테이너 스타일 */
.container {
  max-width: 800px;
}

/* 카드 스타일 */
.card {
  border-radius: 8px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

/* 카드 제목 스타일 */
.card-title {
  font-size: 24px;
  color: #333;
}

/* 버튼 기본 스타일 */
.btn {
  padding: 10px;
  font-size: 16px;
  transition: background-color 0.3s;
}

/* 성공 버튼 호버 스타일 */
.btn-success:hover {
  background-color: #369b72;
}

/* 닫기 버튼 호버 스타일 */
.btn-secondary:hover {
  background-color: #6c757d;
}
</style>
