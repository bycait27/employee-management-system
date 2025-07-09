import api from './api';

// authentication services
export const authService = {
  login: (credentials) => api.post('/auth/signin', credentials),
  signup: (userData) => api.post('/auth/signup', userData),
  logout: () => {
    localStorage.removeItem("token");
  },
  getCurrentUser: () => {
    const token = localStorage.getItem("token");
    if (!token) return null;
    
    try {
      // decode JWT token to get user info
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload;
    } catch (error) {
      console.error('Error decoding token:', error);
      localStorage.removeItem("token");
      return null;
    }
  },
  isAuthenticated: () => {
    const token = localStorage.getItem("token");
    if (!token) return false;
    
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      return payload.exp * 1000 > Date.now(); // check if token is not expired
    } catch {
      return false;
    }
  }
};