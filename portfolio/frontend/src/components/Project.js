import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../App.css';

const backendUrl = 'https://projects-yybm.onrender.com';

function Project() {
  const [projects, setProjects] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      setError(null);
      try {
        const response = await axios.get(`${backendUrl}/api/portfolio/project/`);
        setProjects(response.data);
      } catch (error) {
        console.error('Error fetching projects:', error);
        setError(error);
      } finally {
        setIsLoading(false);
      }
    };
    fetchData();
  }, []);

  if (isLoading) return <p>Loading...</p>;
  if (error) return (
    <div>
      <p>Error: {error.message}</p>
      <button onClick={() => window.location.reload()}>Retry</button>
    </div>
  );

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

  return (
    <div
      className={`flip-card ${isFlipped ? "flipped" : ""}`}
      onClick={handleFlip}
    >
      <div className="flip-card-inner">
        <div className="flip-card-front">
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
