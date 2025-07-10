import { Route, Routes, Navigate } from 'react-router-dom'
import Signin from './pages/auth/Signin'
import Dashboard from './pages/dashboard/Dashboard'
import ProtectedRoute from './components/ProtectedRoute'

export default function Router() {
    return (
        <Routes>
            <Route path='/signin' element={<Signin />} />
            <Route 
                path='/dashboard' 
                element={
                    <ProtectedRoute>
                        <Dashboard />
                    </ProtectedRoute>
                } 
            />
            <Route path='/' element={<Navigate to='/dashboard' />} />
        </Routes>
    )
}