<template>
  <div>
    <!-- 게시물 목록을 출력 -->
    <ul>
      <!-- PostItem 컴포넌트를 반복 렌더링 -->
      <PostItem 
        v-for="post in postStore.postList" 
        :key="post.id" 
        :post="post" 
      />
    </ul>
  </div>
</template>

<script setup>
import { onMounted, watch } from 'vue';
import { useRoute } from 'vue-router';
import PostItem from '../components/PostItem.vue';
import { usePostStore } from '../stores/posts';

// 게시물 Store
const postStore = usePostStore();
// 현재 라우터 정보
const route = useRoute();

// 컴포넌트가 마운트될 때 게시물 리스트를 가져옴
onMounted(() => {
  postStore.getPostList();
});

// 라우트 변경 시 게시물 리스트 갱신
watch(route, () => {
  postStore.getPostList();
});
</script>

<style scoped>
/* 전체 앱 스타일 */
#app {
  text-align: center;
  margin-top: 60px;
}

/* 내비게이션 스타일 */
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

/* 게시물 목록 스타일 */
ul {
  list-style-type: none; /* 불릿 제거 */
  padding: 0;
}

li {
  margin-bottom: 20px; /* 목록 간격 */
}

/* 이미지 스타일 */
img {
  max-width: 100%; /* 이미지가 컨테이너에 맞게 조정 */
  height: auto; /* 비율 유지 */
}

/* 모바일 환경에 최적화된 스타일 */
@media (max-width: 600px) {
  #app {
    margin: 10px; /* 모바일 화면에서 마진 축소 */
  }

  nav {
    font-size: 14px; /* 내비게이션 글꼴 크기 축소 */
  }

  h1, h2 {
    font-size: 24px; /* 제목 글꼴 크기 축소 */
  }
}
</style>
