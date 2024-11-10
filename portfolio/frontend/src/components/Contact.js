import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../App.css'

const backendUrl = 'https://projects-yybm.onrender.com';

function Contact() {
  const [contact, setContact] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      setError(null);
      try {
        const response = await axios.get(`${backendUrl}/api/portfolio/contact/`);
        setContact(response.data);
      } catch (error) {
        console.error('Error fetching contacts:', error);
        setError(error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []);
  
  const handleGoBack =() =>{
	navigate('/Home');
  };

  if (isLoading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div className="Contact">
      <h1>Nida Tazeen</h1>
      {contact.length > 0 ? (
        <ul>
          {contact.map((item) => (
            <li key={item.id}>
              <strong>{item.name}</strong> <br/>
              {item.email && <span><strong>Email: </strong>{item.email}<br/></span>}
              {item.github && (
                <span>
                  <strong>GitHub: </strong><a href={item.github} target="_blank" rel="noopener noreferrer">{item.github}</a><br/>
                </span>
              )}
              {item.linkedin && (
                <span>
                  <strong>LinkedIn: </strong><a href={item.linkedin} target="_blank" rel="noopener noreferrer">{item.linkedin}</a><br/>
                </span>
              )}
            </li>
          ))}
        </ul>
      ) : (
        <p>No contacts available.</p>
      )}
	  <br/>
      <button onClick={handleGoBack} class-name="go-back-button">Go Back to Home</button>
    </div>
  );
}

export default Contact;
