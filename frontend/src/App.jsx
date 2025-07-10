import Router from './Router'
import { AuthProvider } from './contexts/AuthContext'
import { ThemeProvider } from './components/theme-provider'
import { ModeToggle } from './components/mode-toggle'
import './App.css'

export default function App() {
  return (
    <AuthProvider>
      <ThemeProvider defaultTheme='dark' storageKey='vite-ui-theme'>
        <div className='App relative'>
          <div className='fixed top-4 right-4 z-50'>
            <ModeToggle />
          </div>
          <Router />
        </div>
      </ThemeProvider>
    </AuthProvider>
  )
}