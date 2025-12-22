import './index.css'
import { Route, Routes } from 'react-router-dom'

//import { Login } from './components/Login'
//import { ListOfUsers } from './pages/user/ListOfUsers'
import { Login } from './components/Login'
import Layout from './components/Layout'
import { RequireAuth } from './components/RequireAuth'
import { Roles } from './constants/Roles'
import { Home } from './components/Home'
import { Unauthorized } from './components/Unauthorized'



function App() {

  return (
    <Routes>
      <Route path="/" element={<Layout/>} >
      <Route path="login" element={<Login/>}/>
      <Route path="unauthorized" element={<Unauthorized/>} /> 
      <Route element={<RequireAuth allowedRoles={[Roles.ROLE_USER]}/>} >
         <Route path="/" element={<Home/>} /> 
      </Route>
      </Route>
    </Routes>  
    )
}

export default App
