import React, { useState } from 'react';
import axios from 'axios';

const backendUrl = 'https://django-mastodonhub-react-1.onrender.com';

const SignUp = () => {
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');

  const submit = async (e) => {
    e.preventDefault();
    
    const user = { username, email, password };

    try {
      const response = await axios.post(
        `${backendUrl}/signUp/`,
        user,
        {
          headers: {
            'Content-Type': 'application/json', 
          },
          withCredentials: true,
        }
      );

      if (response.status === 201) { 
        alert('User created successfully!');
        window.location.href = '/login';
      }
    } catch (error) { 
      if (error.response && error.response.data) {
        setErrorMessage(error.response.data.error); 
      } else {
        setErrorMessage('An unexpected error occurred.'); 
      }
    }
  };

  return (
    <div className="Auth-form-container">
      <form className="Auth-form" onSubmit={submit}> 
        <div className="Auth-form-content">
          <h3 className="Auth-form-title">Signup</h3>
    
          {/* Username input */}
          <div className="form-group mt-3">
            <label>Username</label>
            <input 
              type="text" 
              className="form-control" 
              placeholder="Enter username" 
              value={username} 
              onChange={(e) => setUsername(e.target.value)} 
            />
          </div>
    
          {/* Email input */}
          <div class="form-group mt-3">
            <label>Email</label>
            <input 
              type="email" 
              className="form-control" 
              placeholder="Enter email" 
              value={email} 
              onChange={(e) => setEmail(e.target.value)} 
            />
          </div>
    
          {/* Password input */}
          <div class="form-group mt-3">
            <label>Password</label>
            <input 
              type="password" 
              className="form-control" 
              placeholder="Enter password" 
              value={password} 
              onChange={(e) => setPassword(e.target.value)} 
            />
          </div>
    
          {/* Submit button */}
          <div className="d-grid gap-2 mt-3">
            <button type="submit" className="btn btn-primary">Sign Up</button> 
          </div>
          
          {/* Display error message if signup fails */}
          {errorMessage && (
            <div className="alert alert-danger mt-3">{errorMessage}</div>
          )}
        </div>
      </form>
    </div>
  );
}

export default SignUp;
