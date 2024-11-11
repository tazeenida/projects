import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../App.css';

const backendUrl = 'https://projects-yybm.onrender.com';

function Project() {
  const [projects, setProjects] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProjects = async () => {
      setIsLoading(true);
      setError(null);
      try {
        const response = await axios.get(`${backendUrl}/api/portfolio/projects/`);
        setProjects(response.data);
      } catch (error) {
        console.error('Error fetching projects:', error);
        setError(error);
      } finally {
        setIsLoading(false);
      }
    };
    fetchProjects();
  }, []);

  if (isLoading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div className="Projects">
      <h1>Projects</h1>
      {projects.length > 0 ? (
        <div className="project-items">
          {projects.map((project) => (
            <div className="project-item" key={project.id}>
              <strong>{project.name}</strong><br />
              {project.description && <p>{project.description}</p>}
              <button
                onClick={() => window.location.href = project.url}
                className="project-link"
              >
                Visit Project
              </button>
            </div>
          ))}
        </div>
      ) : (
        <p>No projects available.</p>
      )}
    </div>
  );
}

export default Project;
