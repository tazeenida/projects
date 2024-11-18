import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../App.css';

const backendUrl = 'https://projects-yybm.onrender.com';

function Experience() {
    const [experience, setExperience] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            setIsLoading(true);
            setError(null);
            try {
                const response = await axios.get(`${backendUrl}/api/portfolio/experience/`);
                setExperience(response.data);
            } catch (error) {
                console.error('Error fetching experience:', error);
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
        <div className="Experience">
            <h1 className="experience-header">Experience Timeline</h1>
            {experience.length > 0 ? (
                <div className="experience-timeline-container">
                    <div className="experience-timeline">
                        {experience.map((item) => (
                            <div className="timeline-item" key={item.experience_id}>
                                <div className="timeline-content">
                                    <strong>{item.role}</strong>
                                    <div className="timeline-date">
                                        {item.start_month && item.start_year && (
                                            <span>{item.start_month} {item.start_year}</span>
                                        )}
                                        {item.end_month && item.end_year && (
                                            <span> - {item.end_month} {item.end_year}</span>
                                        )}
                                    </div>
                                    {item.company && (
                                        <p>
                                            <strong className="item-text">Company:</strong> {item.company}
                                        </p>
                                    )}
                                    {item.job_description && (
                                        <p>
                                            <strong className="item-text">Description:</strong> {item.job_description}
                                        </p>
                                    )}
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            ) : (
                <p>No experience available.</p>
            )}
            <br />
        </div>
    );
}

export default Experience;
