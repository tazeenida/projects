import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../App.css';

const backendUrl = 'https://projects-yybm.onrender.com';

function Project() {
    const [projects, setProjects] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    const navigate = useNavigate();

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

    const handleGoBack = () => {
        navigate('/home');
    };

    if (isLoading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;

    return (
        <div className="Projects">
            <h1>Projects</h1>
            {projects.length > 0 ? (
                <div className="project-items">
                    {projects.map((project) => (
                        <div className="project-item" key={project.project_id}>
                            <h2>{project.title}</h2>
                            <p><strong>Date:</strong> {project.date}</p>
                            <p><strong>Role:</strong> {project.role}</p>
                            {project.description && <p>{project.description}</p>}
                            <p><strong>Technologies:</strong> {project.key_technologies}</p>
                            {project.outcome && <p><strong>Outcome:</strong> {project.outcome}</p>}
                            
                            <div className="project-links">
                                {project.link_to_github && (
                                    <button
                                        onClick={() => window.location.href = project.link_to_github}
                                        className="project-link"
                                    >
                                        View on GitHub
                                    </button>
                                )}
                                {project.app_url && (
                                    <button
                                        onClick={() => window.location.href = project.app_url}
                                        className="project-link"
                                    >
                                        Visit Project
                                    </button>
                                )}
                            </div>
                        </div>
                    ))}
                </div>
            ) : (
                <p>No projects available.</p>
            )}
            <button onClick={handleGoBack} className="go-back-home">Go Back to Home</button>
        </div>
    );
}

export default Project;
