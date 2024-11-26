<template>
  <!-- 개별 게시물을 나타내는 리스트 항목 -->
  <li v-if="post">
    <div class="card w-100">
      <!-- 게시물 작성자 이름을 헤더로 표시 -->
      <div class="card-header text-start">{{ post.author }}</div>
      <!-- 게시물에 이미지가 있을 경우 표시 -->
      <img v-if="post.imageUrl" 
           class="card-img-top" 
           :src="post.imageUrl" 
           alt="Card image cap" 
           style="object-fit: cover; width: 100%; aspect-ratio: 1 / 1;">
      <div class="card-body">
        <div class="d-flex justify-content-between align-items-center">
          <!-- 게시물 설명을 제목으로 표시 -->
          <h5 class="card-title text-start">{{ post.description }}</h5>
          <!-- 좋아요 버튼과 좋아요 수 -->
          <button @click="incrementLike" class="btn btn-primary">
            좋아요 {{ likes }}
          </button>
        </div>
      </div>
    </div>
  </li>
</template>

<script setup>
import { ref, onMounted } from 'vue';

defineProps({
  // 부모 컴포넌트에서 전달받은 게시물 데이터를 정의
  post: {
    type: Object,
    required: true,
  }
});

// 좋아요 수를 로컬로 관리하기 위한 상태
const likes = ref(0);

// 좋아요 버튼 클릭 시 호출되는 함수
const incrementLike = () => {
  likes.value++; // 좋아요 수를 1 증가
};

// 컴포넌트가 마운트될 때 초기 좋아요 수를 설정
onMounted(() => {
  likes.value = 0; // 기본값 0으로 초기화
});
</script>
