import { Route, Routes, Navigate } from 'react-router-dom'
import Login from './pages/auth/Login'
import Dashboard from './pages/dashboard/Dashboard'

export default function Router() {
    return (
        <Routes>
            <Route path='/signin' element={<Login />} />
            <Route path='/dashboard' element={<Dashboard />} />
            <Route path='/' element={<Navigate to='/dashboard' />} />
        </Routes>
    )
}