// src/stores/posts.js
import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_API_URL = `http://localhost:8080/api/posts`

export const usePostStore = defineStore('posts', () => {
  const postList = ref([])
  const getPostList = function() {
    axios.get(REST_API_URL)
    .then((response)=>{
      postList.value = response.data
    })
  }
  const createPost = function(post){
    console.log(post)
  }
  return { postList, getPostList, createPost }
});
