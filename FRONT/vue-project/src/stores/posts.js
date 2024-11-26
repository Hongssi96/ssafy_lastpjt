// src/stores/posts.js
import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_API_URL = `http://localhost:8080/api/posts`
const REST_API_URL_LIKE = `http://localhost:8080/api/likes`
const id = sessionStorage.getItem('loginUserId')
const postId = ref();
export const usePostStore = defineStore('posts', () => {
  const postList = ref([])
  const userPosts = ref([]); // 유저가 작성한 게시물 목록

  // 모든 게시물 찾기
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

  // 아이디로 게시물 찾기
  const getPostListById = async function() {
    try {
      const userId = sessionStorage.getItem('loginUserId')
      const response = await axios.get(`${REST_API_URL}/user/${userId}`, {
        headers: {
          'access-token': sessionStorage.getItem('access-token')
        }
      })
      userPosts.value = response.data;
    } catch (err) {
      console.error('게시물 가져오기 중 오류가 발생했습니다:', err);
    }
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
  // 게시물 삭제 함수
  const deletePost = async function(postId) {
    try {
      await axios.delete(`${REST_API_URL}/${postId}`, {
        headers: {
          'access-token': sessionStorage.getItem('access-token')
        }
      });
      userPosts.value = userPosts.value.filter(post => post.id !== postId); // 삭제 후 목록 갱신
    } catch (err) {
      console.error('게시물 삭제 중 오류가 발생했습니다:', err);
    }
  }
  return { postList, getPostList, createPost, getPostListById, userPosts,deletePost }
});
