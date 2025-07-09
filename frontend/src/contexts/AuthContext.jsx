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
        if (authService.isAuthenticated()) {
          const currentUser = authService.getCurrentUser();
          setUser(currentUser);
        }
      } catch (error) {
        console.error('Error initializing auth:', error);
        authService.logout(); // clear invalid token
      } finally {
        setLoading(false);
      }
    };

    initializeAuth();
  }, []);

  // login function
  const login = async (credentials) => {
    try {
      setLoading(true);
      setError(null);
      
      const response = await authService.login(credentials);
      const { token, id, username, email, roles } = response.data;
      
      // store token
      localStorage.setItem('token', token);
      
      // set user data
      const userData = { id, username, email, roles };
      setUser(userData);
      
      return { success: true, user: userData };
    } catch (error) {
      const errorMessage = error.response?.data?.message || 'Login failed';
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

  // logout function
  const logout = () => {
    authService.logout();
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
    login,
    signup,
    logout,
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