import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../App.css';

const backendUrl = 'https://projects-yybm.onrender.com';

function Skill() {
    const [skill, setSkill] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
	
	const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            setIsLoading(true);
            setError(null);
            try {
                const response = await axios.get(`${backendUrl}/api/portfolio/skill/`);
                setSkill(response.data);
            } catch (error) {
                console.error('Error fetching skill:', error);
                setError(error);
            } finally {
                setIsLoading(false);
            }
        };
        fetchData();
    }, []);

    if (isLoading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;
	
	const handleGoBack = () => {
	       navigate('/Home'); // Navigate programmatically to the Home page
	   };

    return (
        <div className="Skill">
            <h1>Skills</h1>
            {skill.length > 0 ? (
                <div className="skill-columns">
                    {skill.map((item) => (
                        <div className="skill-item" key={item.id}>
                            <strong>{item.name}</strong>
                            <strong>{item.skill && <p>{item.skill}</p>}</strong>
                        </div>
                    ))}
                </div>
            ) : (
                <p>No skill available.</p>
            )}
            <br />
           <button onClick={handleGoBack} className="go-back-button">Go back to Home</button>
        </div>
    );
}

export default Skill;
