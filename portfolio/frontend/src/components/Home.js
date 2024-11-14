import React from 'react';
import '../App.css'
import profileImage from '../images/profile.png';

const Home = () => {
  return (
    <>
      <div className="home-container">
        <img src={profileImage} alt="portfolio" className="home-image" />
        <h1 className="fade-in">Welcome to My Portfolio!</h1>
        <p className="slide-up">
          Hello! I'm <strong>Nida Tazeen</strong>, a passionate Software Engineer with a keen interest in solving complex problems through code.
          This portfolio is a glimpse into my journey—featuring my skills, projects, and experiences.
        </p>
        <p className="slide-up">
          Whether you're here to explore my work or learn more about my skills, I hope you find something that resonates with you.
        </p>
        <p className="slide-up">
          Feel free to browse through the different sections, and don't hesitate to reach out if you have any questions.
          Thanks for visiting—I’m excited to share my story with you!
        </p>
      </div>
    </>
  );
};

export default Home;
