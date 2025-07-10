import { Navigate, useLocation } from 'react-router-dom'
import { useAuth } from '../contexts/AuthContext'

const ProtectedRoute = ({ children, roles = [] }) => {
  const { user, isAuthenticated } = useAuth()
  const location = useLocation()

  // if not authenticated, redirect to signin
  if (!isAuthenticated) {
    return <Navigate to="/signin" state={{ from: location }} replace />
  }

  // if authenticated but no user data, show loading or redirect
  if (!user) {
    return <Navigate to="/signin" replace />
  }

  // if roles are specified, check if user has required role
  if (roles.length > 0) {
    const userRoles = user.roles || []
    const hasRequiredRole = roles.some(role => 
      userRoles.includes(role) || userRoles.includes(`ROLE_${role}`)
    )
    
    if (!hasRequiredRole) {
      // user doesn't have required role - redirect to unauthorized page or dashboard
      return <Navigate to="/dashboard" replace />
    }
  }

  // user is authenticated and has required permissions
  return children
}

export default ProtectedRoute