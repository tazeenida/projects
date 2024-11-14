import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../App.css';

//const backendUrl = 'https://projects-yybm.onrender.com';
const backendUrl = 'http://127.0.0.1:8000';
function Skill() {
    const [skill, setSkill] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
	
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
        </div>
    );
}

export default Skill;
