import React from 'react';
import { motion } from 'framer-motion';  // Import the necessary function from Framer Motion
import '../App.css';
import Education from './Education';
import Experience from './Experience';
import Project from './Project';
import Skill from './Skill';
import FallingCubes from './FallingCubes';


const Home = () => {
	
	const generateRaindrops = () => {
	  return [...Array(20)].map((_, index) => (
	    <div key={index} className="raindrop" style={{ left: `${Math.random() * 100}%`, animationDelay: `${Math.random() * 2}s` }} />
	  ));
	};
	
  return (
    <div className="home-container">
      {/* Banner Section with Motion */}
      <section id="home">
        <motion.div
          className="banner-container"
          transition={{ duration: 0.3, delay: 1, ease: 'linear' }}
        >
          <div className="Events-Banner">
			<div className="App">
			      <FallingCubes />
			    </div>
          </div>
        </motion.div>
      </section>

      {/* Other Content */}
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

      {/* Other Sections */}
      <div className="Education-Section" id="education">
        <Education />
      </div>
      <div className="Experience-Section" id="experience">
        <Experience />
      </div>
      <div className="Project-Section" id="project">
        <Project />
      </div>
      <div className="Skill-Section" id="skill">
        <Skill />
      </div>
    </div>
  );
};

export default Home;
