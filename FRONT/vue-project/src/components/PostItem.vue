<template>
    <li v-if="post">
      <div class="card w-100">
        <div class="card-header text-start">{{ post.author }}.</div>
        <img v-if="post.imageUrl" class="card-img-top" :src="post.imageUrl" alt="Card image cap"  style="object-fit: cover; width: 100%; aspect-ratio: 1 / 1;">
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-center">
                <h5 class="card-title text-start">{{ post.description }}</h5>
                <button @click="incrementLike" class="btn btn-primary">좋아요 {{ post.likes }}</button>
            </div>
        </div>
      </div>
    </li>
  </template>
  
  <script setup>
  import { usePostStore } from '../stores/posts';
  import { onMounted,ref } from 'vue';
 
  defineProps({
    post: {
      type: Object,
      required: true,
    }
  })
  const incrementLike = () => {
  post.likes++;
  };
  
  </script>
  