<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-md-8">
        <div class="card">
          <div class="card-body">
            <h2 class="card-title text-center mb-4">게시물 작성하기</h2>
            <form @submit.prevent="createPost">
              <div class="form-group mb-3">
                <label for="imageUrl" class="form-label">이미지 URL:</label>
                <input type="text" id="imageUrl" v-model="post.imageUrl" required class="form-control" />
              </div>
              <div class="form-group mb-3">
                <label for="description" class="form-label">내용:</label>
                <textarea id="description" v-model="post.description" required class="form-control"></textarea>
              </div>
              <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-success">게시물 작성</button>
                <button type="button" @click="goToPostView" class="btn btn-secondary">닫기</button>
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

const router = useRouter();
const postStore = usePostStore();
const post = ref({
  userId: '1',
  description: '',
  imageUrl: '',
});

const createPost = function() {
  postStore.createPost(post.value);
};

function goToPostView() {
  router.push('/');
}
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

.btn {
  padding: 10px;
  font-size: 16px;
  transition: background-color 0.3s;
}

.btn-success:hover {
  background-color: #369b72;
}

.btn-secondary:hover {
  background-color: #6c757d;
}
</style>
