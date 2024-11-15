import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../App.css';
import { FaEnvelope, FaLinkedin, FaGithub } from 'react-icons/fa';

const backendUrl = 'https://projects-yybm.onrender.com';

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

  if (isLoading) return <p>Loading contact information...</p>;
  if (error) return <p>Error fetching contact details: {error.message}</p>;

  return (
    <div className="contact-info">
      {contact.length > 0 ? (
        <ul className="contact-list">
          {contact.map((item) => (
            <li key={item.id} className="contact-item">
              <strong>{item.name}</strong>
              <div className="contact-details">
                {item.email && (
                  <a href={`mailto:${item.email}`} className="contact-link">
                    <FaEnvelope size={24} /> {item.email}
                  </a>
                )}
                {item.github && (
                  <a
                    href={item.github}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="contact-link"
                  >
                    <FaGithub size={24} /> GitHub
                  </a>
                )}
                {item.linkedin && (
                  <a
                    href={item.linkedin}
                    target="_blank"
                    rel="noopener noreferrer"
                    className="contact-link"
                  >
                    <FaLinkedin size={24} /> LinkedIn
                  </a>
                )}
              </div>
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
