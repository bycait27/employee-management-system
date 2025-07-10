import React, { createContext, useContext, useEffect, useState } from 'react'
import { authService } from '../services'

// create AuthContext
const AuthContext = createContext(undefined);

// custom hook to use AuthContext
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

// AuthProvider component
export const AuthProvider = ({ children }) => {
  // state management
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // initialize authentication on app startup
  useEffect(() => {
    const initializeAuth = () => {
      try {
        const token = localStorage.getItem('token');
        const userStr = localStorage.getItem('user');
        
        if (token && userStr && authService.isAuthenticated()) {
          const currentUser = JSON.parse(userStr);
          setUser(currentUser);
        } else {
          // clear invalid data
          localStorage.removeItem('token');
          localStorage.removeItem('user');
        }
      } catch (error) {
        console.error('Error initializing auth:', error);
        authService.logout(); // clear invalid token
        localStorage.removeItem('user');
      } finally {
        setLoading(false);
      }
    };

    initializeAuth();
  }, []);

  // sign in function
  const signin = async (credentials) => {
    try {
      setLoading(true);
      setError(null);
      
      const response = await authService.login(credentials);
      
      const { accessToken, id, username, email, roles } = response.data;
      
      // store token and user data separately
      localStorage.setItem('token', accessToken);
      const userData = { id, username, email, roles };
      localStorage.setItem('user', JSON.stringify(userData));
      
      setUser(userData);
      
      return { success: true, user: userData };
    } catch (error) {
      const errorMessage = error.response?.data?.message || 'Sign in failed';
      setError(errorMessage);
      return { success: false, error: errorMessage };
    } finally {
      setLoading(false);
    }
  };

  // signup function
  const signup = async (userData) => {
    try {
      setLoading(true);
      setError(null);
      
      const response = await authService.signup(userData);
      return { success: true, message: response.data.message };
    } catch (error) {
      const errorMessage = error.response?.data?.message || 'Signup failed';
      setError(errorMessage);
      return { success: false, error: errorMessage };
    } finally {
      setLoading(false);
    }
  };

  // sign out function
  const signout = () => {
    authService.logout();
    localStorage.removeItem('user'); // remove user data
    setUser(null);
    setError(null);
  };

  // check if user is authenticated
  const isAuthenticated = () => {
    return user !== null && authService.isAuthenticated();
  };

  // role checking functions
  const hasRole = (role) => {
    return user?.roles?.includes(`ROLE_${role.toUpperCase()}`) || false;
  };

  const isAdmin = () => hasRole('ADMIN');
  const isHR = () => hasRole('HR') || isAdmin();

  // clear error function
  const clearError = () => setError(null);

  // context value
  const value = {
    user,
    loading,
    error,
    signin,
    signup,
    signout,
    isAuthenticated,
    hasRole,
    isAdmin,
    isHR,
    clearError
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};