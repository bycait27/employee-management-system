import axios from "axios";

// axios config

const API_BASE_URL = "http://localhost:8080/api";
const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// add request interceptor for auth tokens
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error),
);

// add response interceptor for error handling
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // clear token on unauthorized response
      localStorage.removeItem("token");
      // redirect to login
      window.location.href = "/login";
    }
    return Promise.reject(error);
  }
);

export default api;