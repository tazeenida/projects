import React, { useEffect } from 'react';
import '../App.css';
import banner from '../images/home-banner.png';
import Education from './Education';
import Experience from './Experience';
import Project from './Project';
import Skill from './Skill';

const Home = () => {

	useEffect(() => {
	  const aboutMeSection = document.getElementById('banner');
	  if (aboutMeSection) {
	    console.log('Scrolling to About Me section');  // Log to check if the code runs
	    aboutMeSection.scrollIntoView({ behavior: 'smooth' });
	  } else {
	    console.log('Home section not found');
	  }
	}, []);

  return (
    <>
      <div className="home-container">
        <section id="home">
          <img src={banner} alt="banner" width="100%" height="400px" />
          <div className="Events-Banner"><h1 className="fade-in">Welcome to My Portfolio!</h1></div>
        </section>

        <section id="home">
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
        </section>

        <div className="Education-Section" id="education">
          <Education />
        </div>
        <div className="Project-Section" id="project">
          <Project />
        </div>
        <div className="Skill-Section" id="skill">
          <Skill />
        </div>
      </div>
    </>
  );
};

export default Home;
