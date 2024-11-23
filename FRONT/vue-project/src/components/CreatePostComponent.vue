<template>
    <div class="create-post">
      <h2>게시물 작성하기</h2>
      <form @submit.prevent="createPost">
        <div>
          <label for="description">내용:</label>
          <textarea id="description" v-model="description" required></textarea>
        </div>
        <div>
          <label for="imageUrl">이미지 URL:</label>
          <input type="text" id="imageUrl" v-model="imageUrl" required />
        </div>
        <button @click="createPost">게시물 작성</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { usePostStore } from '../stores/posts';
  
  const postStore = usePostStore();
  const post = ref({
    user_id: '',
    description: '',
    imageUrl: '',
  })
  const createPost = function(){
    postStore.createPost(post.value)
  }
  </script>
  
  <style scoped>
  .create-post {
    margin: 20px;
    padding: 20px;
    border: 1px solid #eaeaea;
    border-radius: 8px;
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