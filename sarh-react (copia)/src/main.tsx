//import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { Provider } from 'react-redux'
//import { BrowserRouter } from 'react-router'
//import { AuthProvider } from './context/AuthProvider.tsx'
import { store } from './store/index';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

createRoot(document.getElementById('root')!).render(
  <BrowserRouter>
    <Provider store={store}>
      <Routes>
        <Route path="/*" element={<App />} />
        
      </Routes>

    </Provider>
  </BrowserRouter>

)
