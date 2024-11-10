import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../App.css';

const backendUrl = 'http://127.0.0.1:8000';

function Education() {
    const [education, setEducation] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState(null);
	
	const navigate = useNavigate();

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
	
	const handleGoBack= () =>{
		navigate('/Home');
	};

    if (isLoading) return <p>Loading...</p>;
    if (error) return <p>Error: {error.message}</p>;

    return (
        <div className="Education">
            <h1>Education</h1>
            {education.length > 0 ? (
                <div className="education-items">
                    {education.map((item) => (
                        <div className="education-item" key={item.id}>
                            <strong>{item.name}</strong><br />
                            {item.degree && <span><strong>Degree:</strong> {item.degree}<br /></span>}
                            {item.university && <span><strong>University:</strong> {item.university}<br /></span>}
                            {item.level && <span><strong>Level:</strong> {item.level}<br /></span>}
                            {item.start_month && item.start_year && (
                                <span><strong>Start Date:</strong> {item.start_month} {item.start_year}<br /></span>
                            )}
                            {item.end_month && item.end_year && (
                                <span><strong>End Date:</strong> {item.end_month} {item.end_year}<br /></span>
                            )}
                        </div>
                    ))}
                </div>
            ) : (
                <p>No education available.</p>
            )}
            <br />
           <button onClick={handleGoBack} className="go-back-home">Go Back to Home</button>
        </div>
    );
}

export default Education;
