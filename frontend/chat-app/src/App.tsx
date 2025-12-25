import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router'
import { Login } from './components/Login'
import { Dashboard } from './components/Dashboard'
import { UserList } from './components/UserList'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route index element={ <Login /> } />
        <Route path='dashboard' element={ <Dashboard />}>
          {/* <Route index element={ <UserList />} /> */}
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
