// src/stores/auth.js
import { defineStore } from 'pinia';

export const useAuthStore = defineStore('auth', {
  state: () => ({
    isAuthenticated: !!localStorage.getItem('jwt'),
    user: JSON.parse(localStorage.getItem('user')) || null,
  }),
  actions: {
    login(user, token) {
      this.isAuthenticated = true;
      this.user = user;
      localStorage.setItem('user', JSON.stringify(user));
      localStorage.setItem('jwt', token);
    },
    logout() {
      this.isAuthenticated = false;
      this.user = null;
      localStorage.removeItem('user');
      localStorage.removeItem('jwt');
    }
  },
});
