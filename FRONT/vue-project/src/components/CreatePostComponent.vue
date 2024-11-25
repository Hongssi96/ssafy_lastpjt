<template>
    <div class="create-post">
      <h2>게시물 작성하기</h2>
      <form @submit.prevent="createPost">
          <div>
              <label for="imageUrl">이미지 URL:</label>
              <input type="text" id="imageUrl" v-model="post.imageUrl" required />
              <div>
                <label for="description">내용:</label>
                <textarea id="description" v-model="post.description" required></textarea>
              </div>
            </div>
        <div class="button-group">
            <button @click="createPost">게시물 작성</button>
            <button type="button" @click="goToPostView" class="btn-close">×</button>
        </div>
      </form>
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
  })

  const createPost = function(){
    postStore.createPost(post.value)
  }

  function goToPostView() {
    router.push('/');
  }
  </script>
  
  <style scoped>
  .create-post {
    margin: 20px;
    padding: 20px;
    border: 1px solid #eaeaea;
    border-radius: 8px;
  }

  .button-group {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
  }
  
  form {
    display: flex;
    flex-direction: column;
  }
  
  label {
    margin-top: 10px;
    font-weight: bold;
  }
  
  textarea,
  input {
    margin-bottom: 10px;
    padding: 10px;
    font-size: 16px;
    width: 100%;
  }
  
  button {
    align-self: flex-start;
    padding: 10px 20px;
    background-color: #42b983;
    color: #fff;
    border: none;
    cursor: pointer;
    border-radius: 4px;
  }
  
  button:hover {
    background-color: #369b72;
  }
  </style>