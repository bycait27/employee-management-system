import Router from './Router'
import { AuthProvider } from './contexts/AuthContext'
import './App.css'

export default function App() {
  return (
    <AuthProvider>
      <div className='App'>
        <Router />
      </div>
    </AuthProvider>
  )
}