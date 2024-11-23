<template>
    <div>
      <h2>게시글 목록</h2>
      <ul>
        <li v-for="post in posts" :key="post.id">
          <h3>{{ post.description }}</h3>
          <img :src="post.imageUrl" alt="Post Image" />
        </li>
      </ul>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  
  const posts = ref([
    { id: 1, description: '첫 번째 게시글입니다.', imageUrl: 'http://example.com/image1.jpg' },
    { id: 2, description: '두 번째 게시글입니다.', imageUrl: 'http://example.com/image2.jpg' },
    { id: 3, description: '세 번째 게시글입니다.', imageUrl: 'http://example.com/image3.jpg' }
  ]);
  </script>
  
  // 10. 모바일 환경을 위한 스타일 추가
  // 모바일 사용자 경험을 개선하기 위해 반응형 디자인을 적용합니다.
  <style scoped>
  #app {
    text-align: center;
    margin-top: 60px;
  }
  
  nav {
    margin-bottom: 20px;
  }
  
  nav a {
    margin: 0 10px;
    text-decoration: none;
    color: #42b983;
  }
  
  nav a.router-link-exact-active {
    font-weight: bold;
  }
  
  ul {
    list-style-type: none;
    padding: 0;
  }
  
  li {
    margin-bottom: 20px;
  }
  
  img {
    max-width: 100%;
    height: auto;
  }
  
  /* 모바일 화면에 최적화된 스타일 */
  @media (max-width: 600px) {
    #app {
      margin: 10px;
    }
  
    nav {
      font-size: 14px;
    }
  
    h1, h2 {
      font-size: 24px;
    }
  }
  </style>