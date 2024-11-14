// src/components/Contact.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../App.css';

const backendUrl = 'http://127.0.0.1:8000';

function Contact() {
  const [contact, setContact] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

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

  if (isLoading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div className="contact-info">
      {contact.length > 0 ? (
        <ul>
          {contact.map((item) => (
            <li key={item.id}>
              <strong>{item.name}</strong> <br />
              {item.email && <span><strong>Email: </strong>{item.email}<br /></span>}
              {item.github && (
                <span>
                  <strong>GitHub: </strong><a href={item.github} target="_blank" rel="noopener noreferrer">{item.github}</a><br />
                </span>
              )}
              {item.linkedin && (
                <span>
                  <strong>LinkedIn: </strong><a href={item.linkedin} target="_blank" rel="noopener noreferrer">{item.linkedin}</a><br />
                </span>
              )}
            </li>
          ))}
        </ul>
      ) : (
        <p>No contacts available.</p>
      )}
    </div>
  );
}

export default Contact;
