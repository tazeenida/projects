import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const backendUrl = `https://django-mastodonhub-react-1.onrender.com`;

const UserProfile = () => {
  const [userData, setUserData] = useState(null);
  const [editMode, setEditMode] = useState(false);
  const navigate = useNavigate(); // Use navigate for redirection

  const logoutAndRedirect = () => {
    localStorage.removeItem('access_token'); // Clear the token
    navigate('/login'); // Redirect to the login page
  };

  useEffect(() => {
    const fetchUserData = async () => {
      const token = localStorage.getItem('access_token');
      if (!token) {
        logoutAndRedirect(); // If there's no token, redirect to login
        return;
      }

      try {
        const response = await axios.get(`${backendUrl}/profile/`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setUserData(response.data); // Set user data after successful fetch
      } catch (error) {
        if (error?.response && error.response.status === 401) { // Ensure 'response' exists
          logoutAndRedirect(); // If 401, likely expired
        } else {
          console.error('Error fetching user data:', error);
        }
      }
    };

    fetchUserData(); // Fetch user data when the component mounts
  }, [navigate]); // Dependency array, include navigate to avoid warning

  const handleEdit = () => {
    setEditMode(true); // Enter edit mode
  };

  const handleCancel = () => {
    setEditMode(false); 
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserData((prevUserData) => ({
      ...prevUserData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem('access_token');
    if (!token) {
      logoutAndRedirect(); // If no token, redirect to login
      return;
    }

    try {
      const response = await axios.put(`${backendUrl}/profile/`, userData, {
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
      });
      setUserData(response.data); // Update user data after successful put request
      setEditMode(false); // Exit edit mode
    } catch (error) {
      if (error?.response && error.response.status === 401) { // Check if 'response' exists
        logoutAndRedirect(); // If 401, session timeout, redirect to login
      } else {
        console.error('Error updating user data:', error); // Handle other errors
      }
    }
  };

  if (!userData) {
    return <p>Loading...</p>; // Display loading message while data is fetched
  }

  return (
    <div className="profile">
      <h2>Profile</h2>
      {editMode ? (
        <form onSubmit={handleSubmit}>
          <div>
            <label>First Name</label>
            <input
              type="text"
              name="first_name"
              value={userData.first_name}
              onChange={handleChange}
            />
          </div>
          <div>
            <label>Last Name</label>
              <input
                type="text"
                name="last_name"
                value={userData.last_name}
                onChange={handleChange}
              />
          </div>
          <button type="submit">Save</button>
          <button onClick={handleCancel}>Cancel</button>
        </form>
      ) : (
        <div>
          <p>Username: {userData.username}</p>
          <p>Email: {userData.email}</p>
          <p>First Name: {userData.first_name}</p>
          <p>Last Name: {userData.last_name}</p>
          <button onClick={handleEdit}>Edit Profile</button>
        </div>
      )}
    </div>
  );
};

export default UserProfile;
