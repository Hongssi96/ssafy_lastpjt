// src/stores/posts.js
import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

// API URL 설정
const REST_API_URL = `http://localhost:8080/api/posts`; // 게시물 관련 API
const REST_API_URL_LIKE = `http://localhost:8080/api/likes`; // 좋아요 관련 API

// Pinia Store 정의
export const usePostStore = defineStore('posts', () => {
  const postList = ref([]); // 모든 게시물 리스트
  const userPosts = ref([]); // 특정 사용자가 작성한 게시물 리스트

  // 모든 게시물 가져오기
  const getPostList = function() {
    axios.get(REST_API_URL, {
      headers: {
        'access-token': sessionStorage.getItem('access-token'), // 인증 토큰 헤더
      }
    })
    .then((response) => {
      postList.value = response.data; // 가져온 게시물 데이터를 저장
    })
    .catch((err) => {
      console.error('모든 게시물 가져오기 중 오류 발생:', err);
    });
  };

  // 특정 사용자 ID로 게시물 가져오기
  const getPostListById = async function() {
    try {
      const userId = sessionStorage.getItem('loginUserId'); // 로그인된 사용자 ID
      const response = await axios.get(`${REST_API_URL}/user/${userId}`, {
        headers: {
          'access-token': sessionStorage.getItem('access-token'), // 인증 토큰 헤더
        }
      });
      userPosts.value = response.data; // 해당 사용자의 게시물 리스트 저장
    } catch (err) {
      console.error('사용자 게시물 가져오기 중 오류 발생:', err);
    }
  };

  // 게시물 생성 함수
  const createPost = function(post) {
    axios({
      url: REST_API_URL,
      method: 'POST',
      data: post, // 생성할 게시물 데이터
      headers: {
        'access-token': sessionStorage.getItem('access-token'), // 인증 토큰 헤더
      }
    })
    .then((res) => {
      getPostList(); // 게시물 생성 후 전체 게시물 리스트 갱신
    })
    .catch((err) => {
      console.error('게시물 생성 중 오류 발생:', err);
    });
  };

  // 게시물 삭제 함수
  const deletePost = async function(postId) {
    try {
      await axios.delete(`${REST_API_URL}/${postId}`, {
        headers: {
          'access-token': sessionStorage.getItem('access-token'), // 인증 토큰 헤더
        }
      });
      userPosts.value = userPosts.value.filter(post => post.id !== postId); // 삭제된 게시물을 목록에서 제거
    } catch (err) {
      console.error('게시물 삭제 중 오류 발생:', err);
    }
  };

  // Store에서 제공할 데이터와 함수 반환
  return { 
    postList, // 전체 게시물
    userPosts, // 특정 사용자 게시물
    getPostList, // 모든 게시물 가져오기
    createPost, // 게시물 생성
    getPostListById, // 특정 사용자 게시물 가져오기
    deletePost, // 게시물 삭제
  };
});
