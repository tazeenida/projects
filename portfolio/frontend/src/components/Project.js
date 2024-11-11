import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../App.css';

const backendUrl = 'https://projects-yybm.onrender.com';

function Project() {
    const [project, setProject] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
	
	const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            setIsLoading(true);
            setError(null);
            try {
                const response = await axios.get(`${backendUrl}/api/portfolio/project/`);
                setProject(response.data);
            } catch (error) {
                console.error('Error fetching project:', error);
                setError(error);
            } finally {
                setIsLoading(false);
            }
        };
        fetchData();
    }, []);
	
	const handleGoBack= () =>{
		navigate('/Home');
	};

    if (isLoading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;


  return (
    <div className="Projects">
      <h1>Projects</h1>
      {project.length > 0 ? (
        <div className="project-items">
          {project.map((project) => (
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
