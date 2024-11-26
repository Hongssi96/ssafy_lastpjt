// src/stores/users.js
import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

// API URL 설정
const REST_API_URL = `http://localhost:8080/api/users`;

export const useUserStore = defineStore('users', () => {
  const loginUser = ref(null); // 현재 로그인한 사용자 이름
  const loginUserId = ref(null); // 현재 로그인한 사용자 ID
  const userList = ref([]); // 전체 사용자 리스트

  // 사용자 로그인
  const userLogin = function(email, password) {
    axios.post(`${REST_API_URL}/login`, {
      email: email, // 로그인 이메일
      password: password, // 로그인 비밀번호
    })
    .then((res) => {
      // 서버에서 받은 토큰 저장
      sessionStorage.setItem('access-token', res.data['access-token']);

      // 토큰에서 사용자 정보 파싱
      const token = res.data['access-token'].split(".");
      const payload = JSON.parse(atob(token[1])); // Base64 디코딩
      const name = payload['name'];
      const userId = payload['id'];

      // 로그인 사용자 정보 저장
      loginUser.value = name;
      loginUserId.value = userId;
      sessionStorage.setItem('loginUserId', userId);
      sessionStorage.setItem('loginUser', name);

      alert("로그인 성공!");

      // 로그인 성공 시 메인 화면으로 이동
      router.push("/");
    })
    .catch((e) => {
      // 에러 처리
      if (e.response && e.response.status === 404) {
        alert("존재하지 않는 아이디입니다.");
      } else if (e.response && e.response.status === 401) {
        alert("비밀번호가 틀렸습니다.");
      } else {
        alert("로그인에 실패했습니다. 다시 시도해주세요");
      }
      console.error(e);
    });
  };

  // 전체 사용자 목록 가져오기
  const getUserList = function() {
    axios.get(REST_API_URL)
    .then((response) => {
      userList.value = response.data; // 사용자 목록 저장
    })
    .catch((err) => {
      console.error('사용자 목록 가져오기 중 오류 발생:', err);
    });
  };

  // 사용자 생성
  const createUser = function(user) {
    axios({
      url: REST_API_URL,
      method: 'POST',
      data: user, // 사용자 정보 데이터
    })
    .then((res) => {
      console.log("유저 생성 성공:", res);
    })
    .catch((err) => {
      console.error("유저 생성 중 오류 발생:", err);
    });
  };

  // 사용자 삭제 (탈퇴)
  const deleteUser = async function() {
    const userId = sessionStorage.getItem('loginUserId'); // 로그인한 사용자 ID
    try {
      await axios.delete(`${REST_API_URL}/${userId}`);
      
      // 로그인 정보 초기화 및 세션 데이터 삭제
      loginUser.value = null;
      sessionStorage.removeItem('access-token');
      sessionStorage.removeItem('loginUser');
      sessionStorage.removeItem('loginUserId');
      
      // 메인 화면으로 이동
      router.push('/');
      alert('유저 탈퇴가 완료되었습니다.');
    } catch (err) {
      console.error('유저 탈퇴 중 오류 발생:', err);
      alert('유저 탈퇴에 실패했습니다.');
    }
  };

  // Store에서 제공할 데이터와 함수 반환
  return {
    userList, // 전체 사용자 리스트
    getUserList, // 사용자 목록 가져오기
    createUser, // 사용자 생성
    userLogin, // 사용자 로그인
    loginUser, // 현재 로그인한 사용자 이름
    deleteUser, // 사용자 삭제
  };
});
