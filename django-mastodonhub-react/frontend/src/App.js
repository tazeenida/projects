import './styles.css';
import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Footer from './components/Footer';
import Navigation from './components/Navigation';
import Dashboard from './components/Dashboard';
import Events from './components/Events';
import Clubs from './components/ClubsPage';
import Login from './components/Login';
import Logout from './components/Logout';
import Signup from './components/Signup';
import UserProfile from './components/UserProfile';

const isLoggedIn = false;

function App() {
  const isLoggedIn = true; // example condition
      if (!isLoggedIn) {

        return <Navigate to="/login" />;
      }
  const isSignedUp = true; // example condition
      if (!isSignedUp) {

        return <Navigate to="/signUp" />;
      }
  return (
    <div>
      <Router>
        <div>
          <Navigation/>
          <Routes>
          <Route path="/" element={isLoggedIn ? <Dashboard />  : <Navigate to="/Dashboard" />} />
          <Route path="/dashboard" element={isLoggedIn ? <Dashboard /> : <Navigate to="/Dashboard" />} />
            <Route path="/Events" element={<Events />} />
            <Route path="/Clubs" element={<Clubs/>} />
            <Route path="/Login" element={<Login />} />
            <Route path="/Logout" element={<Logout />} />
            <Route path="/UserProfile" element={<UserProfile />} />
            <Route path="/Signup" element={<Signup />} />
            {isLoggedIn ? (
          <>
            <Route path="/dashboard" element={<Dashboard />} />
          </>
        ) : (
          <>
            <Route path="/" element={<Navigate to="/Login" />} />
            <Route path="/signup" element={<Signup />} />
          </>
        )}
        </Routes>
        </div>

      </Router>
      <Footer/>
    </div>
  );
}

export default App;
