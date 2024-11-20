import React from 'react';
import headerImage from '../images/header img.jpg'
import FeaturedEvents from './FeaturedEvents';
import FeaturedClubs from './FeaturedClubs';
const Dashboard = () => {
  return (
    <div>
      <div id="header-image-container">
      <img src={headerImage} alt="Header" />
        <div className="header-image-text">
          <h1>Welcome to MastodonHub</h1>
          <p>Discover amazing events and clubs on your campus!</p>
        </div>
      </div>
      <main>
        <div className="clubs-container">
          <FeaturedClubs/>
          <FeaturedEvents/>
        </div>
      </main>
    </div>
  );
}

export default Dashboard;
