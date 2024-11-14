import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../App.css';

const backendUrl = 'https://projects-yybm.onrender.com';

function Education() {
    const [education, setEducation] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            setIsLoading(true);
            setError(null);
            try {
                const response = await axios.get(`${backendUrl}/api/portfolio/education/`);
                setEducation(response.data);
            } catch (error) {
                console.error('Error fetching education:', error);
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
        <div className="Education">
            <h1 className="education-header">Education Timeline</h1>
            {education.length > 0 ? (
                <div className="education-timeline-container">
                    <div className="education-timeline">
                        {education.map((item) => (
                            <div className="timeline-item" key={item.id}>
                                <div className="timeline-content">
                                    <strong>{item.name}</strong>
                                    <div className="timeline-date">
                                        {item.start_month && item.start_year && (
                                            <span>{item.start_month} {item.start_year}</span>
                                        )}
                                        {item.end_month && item.end_year && (
                                            <span> - {item.end_month} {item.end_year}</span>
                                        )}
                                    </div>
                                    {item.degree && <p><strong className="item-text">Degree:</strong> {item.degree}</p>}
                                    {item.university && <p><strong  className="item-text">University:</strong> {item.university}</p>}
                                    {item.level && <p><strong className="item-text">Level:</strong> {item.level}</p>}
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            ) : (
                <p>No education available.</p>
            )}
            <br />
        </div>
    );
}

export default Education;
