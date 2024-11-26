<template>
  <li v-if="post">
    <div class="card w-100">
      <div class="card-header text-start">{{ post.author }}.</div>
      <img v-if="post.imageUrl" class="card-img-top" :src="post.imageUrl" alt="Card image cap" style="object-fit: cover; width: 100%; aspect-ratio: 1 / 1;">
      <div class="card-body">
        <div class="d-flex justify-content-between align-items-center">
          <h5 class="card-title text-start">{{ post.description }}</h5>
          <button @click="incrementLike" class="btn btn-primary">좋아요 {{ likes }}</button>
        </div>
      </div>
    </div>
  </li>
</template>

<script setup>
import { ref, onMounted } from 'vue';

defineProps({
  post: {
    type: Object,
    required: true,
  }
});

// 좋아요 상태를 로컬로 관리
const likes = ref(0);

// 좋아요 수 증가 함수
const incrementLike = () => {
  likes.value++;
};

// 마운트 시 초기 좋아요 수 설정
onMounted(() => {
  likes.value = 0;
});
</script>
