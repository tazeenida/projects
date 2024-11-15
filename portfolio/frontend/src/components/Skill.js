import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../App.css';

const backendUrl = 'https://projects-yybm.onrender.com';

function Skill() {
    const [skills, setSkills] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            setIsLoading(true);
            setError(null);
            try {
                const response = await axios.get(`${backendUrl}/api/portfolio/skill/`);
                setSkills(response.data);
            } catch (error) {
                console.error('Error fetching skills:', error);
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
            <h1 className="Skill-header">Skills</h1>
            {skills.length > 0 ? (
                <div className="skill-columns">
                    {skills.map((skill) => (
                        <div className="skill-item" key={skill.id}>
                            <div className="skill-header">
                                <strong>{skill.name}</strong>
                                <div className="skill-level">
                                    {skill.level && <span>{skill.level}</span>}
                                </div>
                            </div>
                            {skill.skill && <p>{skill.skill}</p>}
                            {skill.level && (
                                <div className="progress-bar">
                                    <div
                                        className="progress"
                                        style={{ width: `${skill.level}%` }}
                                    ></div>
                                </div>
                            )}
                        </div>
                    ))}
                </div>
            ) : (
                <p>No skills available.</p>
            )}
        </div>
    );
}

export default Skill;
