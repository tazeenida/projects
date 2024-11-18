import React, { useState } from 'react';
import '../App.css';
import MastodonHub from '../images/mastodonHub.png';
import Food from '../images/food.jpg';
import Movie from '../images/movie.jpg';

const titlesToImages = {
  "Book Recommendation System": "https://images.ctfassets.net/cnu0m8re1exe/4KwrJVfCGeyOKwm8PS2tjI/30026753d97e3b41a50560063126ded8/shutterstock_135114548.jpg?fm=jpg&fl=progressive&w=660&h=433&fit=fill",
  "MastodonHub": MastodonHub,
  "Food Management System": Food,
  "Movie Analyzer": Movie,
};

function Project() {
  const projects = [
    {
      project_id: "eaf85498-1c66-4b5d-89ea-efca0a9f92dc",
      title: "Book Recommendation System",
      date: "2024-11-13",
      role: "Student",
      description: "Finding the right book can be challenging in today’s vast literary landscape. The Book Recommendation System aims to simplify this process by offering an organized, user-friendly platform where users can browse, add, search, update, and delete book entries. This system is designed to help users discover new reads based on personalized recommendations, making it easier to find books that align with their interests.",
      key_technologies: "Front-End: React\nBack-End: Django\nDatabase: SQLite\nDeployment Hosting: Render\nVersion Control: Git",
      link_to_github: "https://github.com/tazeenida/projects",
      app_url: "https://bookrecommendationsystem-qf5o.onrender.com/",
    },
    {
      project_id: "9776b7c3-52fb-460a-9bfa-9b0dc72361e3",
      title: "MastodonHub",
      date: "2024-11-13",
      role: "Student",
      description: "At Purdue University Fort Wayne (PFW), students have access to a variety of clubs and events. However, new students often face difficulties in finding and connecting with these opportunities due to the complexity of the PFW portal. MastodonHub aims to address this by providing a user-friendly app that consolidates information on clubs and events, alongside a comprehensive Purdue events calendar, to enhance student engagement and simplify navigation.",
      key_technologies: "Front-End: React\nBack-End: Django\nDatabase: SQLite\nDeployment Hosting: Render\nVersion Control: Git",
      link_to_github: "https://github.com/tazeenida/django-mastodonhub-react",
      app_url: "https://django-mastodonhub-react.onrender.com",
    },
    {
      project_id: "04b59d03-70d9-4d41-90f2-7d6e695f8d45",
      title: "Food Management System",
      date: "2024-11-18",
      role: "Student",
      description: "The Food Management System project aims to analyse food delivery orders from various restaurants, providing valuable insights into customer behaviour, restaurant preferences, and service efficiency. By evaluating key metrics such as delivery time, order cost, and food preparation time, this system strives to enhance decision-making processes for both customers and restaurants, leading to improved service quality and customer satisfaction.",
      key_technologies: "Front-End: Vaadin\nBack-End: Spring Boot Application\nDatabase: SQLite\nDeployment Hosting: Render\nVersion Control: Git",
      link_to_github: "https://github.com/tazeenida/Food-Management-System/tree/main/FoodManagementSystem",
      app_url: null,
    },
	{
	      project_id: "04b59d03-70d9-4d41-90f2-7d6e695f8d90",
	      title: "Movie Analyzer",
	      date: "2024-11-18",
	      role: "Student",
	      description: "The Movie Analyzer is a comprehensive web application designed to manage and analyze movie and TV show data. The project allows users to perform CRUD (Create, Read, Update, Delete) operations on a database of movies and TV shows. It includes detailed information such as movie titles, directors, release years, countries of origin, and their types (TV Show or Movie).",
	      key_technologies: "Front-End: Vaadin\nBack-End: Spring Boot Application\nDatabase: SQLite\nDeployment Hosting: Render\nVersion Control: Git",
	      link_to_github: "https://github.com/tazeenida/Homework_ACS560/tree/main/Movie_Analyzer_Vaadin",
	      app_url: null,
	    },
  ];

  return (
    <div className="Projects">
      <h1>Projects</h1>
      {projects.length > 0 ? (
        <div className="project-items">
          {projects.map((project) => (
            <ProjectCard project={project} key={project.project_id} />
          ))}
        </div>
      ) : (
        <p>No projects available. Check back later!</p>
      )}
    </div>
  );
}

const ProjectCard = ({ project }) => {
  const [isFlipped, setIsFlipped] = useState(false);

  const handleFlip = () => {
    setIsFlipped((prev) => !prev);
  };

  const backgroundImage = titlesToImages[project.title] || "https://images.ctfassets.net/cnu0m8re1exe/4KwrJVfCGeyOKwm8PS2tjI/30026753d97e3b41a50560063126ded8/shutterstock_135114548.jpg?fm=jpg&fl=progressive&w=660&h=433&fit=fill";

  return (
    <div
      className={`flip-card ${isFlipped ? "flipped" : ""}`}
      onClick={handleFlip}
    >
      <div className="flip-card-inner">
        <div className="flip-card-front" style={{ backgroundImage: `url(${backgroundImage})` }}>
          <div>
            <h2>{project.title}</h2>
            <div>
              <p><strong>Date:</strong> {project.date}</p>
              <p><strong>Role:</strong> {project.role}</p>
              <p><strong>Technologies:</strong> {project.key_technologies}</p>
            </div>
          </div>
        </div>
        <div className="flip-card-back">
          <div className="project-description">
            {project.description && <p>{project.description}</p>}
          </div>
          
          <div className="project-links">
            {project.link_to_github && (
              <button
                onClick={(e) => {
                  e.stopPropagation();
                  window.open(project.link_to_github, '_blank');
                }}
                className="project-link"
              >
                View on GitHub
              </button>
            )}
            {project.app_url && (
              <button
                onClick={(e) => {
                  e.stopPropagation();
                  window.open(project.app_url, '_blank');
                }}
                className="project-link"
              >
                Visit Project
              </button>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Project;
