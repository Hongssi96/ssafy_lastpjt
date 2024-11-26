// src/stores/posts.js
import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_API_URL = `http://localhost:8080/api/users`

export const useUserStore = defineStore('users', () => {
  const loginUser = ref(null)
  const loginUserId = ref(null)
  const userLogin = function(email, password){
    axios.post(`${REST_API_URL}/login`, {
      email:email,
      password:password,
    })
    .then((res)=>{
      sessionStorage.setItem('access-token', res.data['access-token'])
      
      const token = res.data['access-token'].split(".")
      const payload = JSON.parse(atob(token[1]));
      const name = payload['name'];
      const userId = payload['id'];

      loginUser.value = name;
      loginUserId.value = userId;
      sessionStorage.setItem('loginUserId', userId);
      sessionStorage.setItem('loginUser', name)

      router.push("/");
    })
    .catch((e) => {
      console.log(e)
    })
  }
  const userList = ref([])
  const getUserList = function() {
    axios.get(REST_API_URL)
    .then((response)=>{
      userList.value = response.data
    })
  }
  const createUser = function(user){
    axios({
      url: REST_API_URL,
      method: 'POST',
      data: user
    })
    .then((res)=> {
      console.log("출력 : ",res)
    })
    .catch((err)=>{
      console.log("에러 : ",err)
    })
  }

  const deleteUser = async function() {
    const userId = sessionStorage.getItem('loginUserId')
    try {
      await axios.delete(`${REST_API_URL}/${userId}`);
      loginUser.value = null;
      sessionStorage.removeItem('access-token');
      sessionStorage.removeItem('loginUser')
      sessionStorage.removeItem('loginUserId')
      router.push('/');
      alert('유저 탈퇴가 완료되었습니다.');
    } catch (err) {
      console.error('유저 탈퇴 중 오류가발생했습니다:', err);
      alert('유저 탈퇴에 실패했습니다.')
    }
    
  }
  return { userList, getUserList, createUser, userLogin, loginUser, deleteUser }
});
