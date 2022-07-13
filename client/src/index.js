import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import Login from './routes/login';
import Dashboard from "./routes/dashboard";
import Register from './routes/register';
import Profile from './routes/profile';
import Users from './routes/users';
import User from './components/User/User';

import reportWebVitals from './reportWebVitals';
import {BrowserRouter, Route, Routes } from 'react-router-dom'

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path = "/" element = {<App />} />
        <Route path = "/login" element = {<Login />} />
        <Route path='/register' element = {<Register />} />
        <Route path = "/dashboard" element = {<Dashboard />} />
        <Route path = "/profile" element = {<Profile />} />
        <Route path='/users' element = {<Users />} />
        <Route path='/user/:id' element = {<User />} />
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
