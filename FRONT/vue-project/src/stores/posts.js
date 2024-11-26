// src/stores/posts.js
import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_API_URL = `http://localhost:8080/api/posts`
const REST_API_URL_LIKE = `http://localhost:8080/api/likes`

export const usePostStore = defineStore('posts', () => {
  const postList = ref([])
  const getPostList = function() {
    axios.get(REST_API_URL, {
      headers: {
        'access-token': sessionStorage.getItem('access-token')
      }
    })
    .then((response)=>{
      postList.value = response.data
    })
  }
  const createPost = function(post){
    axios({
      url: REST_API_URL,
      method: 'POST',
      data: post,
      headers: {
        'access-token': sessionStorage.getItem('access-token')}
    })
    .then((res)=> {
      console.log("출력 : ",res)
    })
    .catch((err)=>{
      console.log("에러 : ",err)
    })
  }
  return { postList, getPostList, createPost }
});
