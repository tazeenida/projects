import React from 'react';
import '../App.css';
import { FaEnvelope, FaLinkedin, FaGithub } from 'react-icons/fa';

const contact = [
  {
    contact_id: "ca5db0da-e7f8-40c1-8bcd-b8b6737b4ea8",
    email: "nidatazeen@outlook.com",
    github: "https://github.com/tazeenida/projects",
    linkedin: "https://www.linkedin.com/in/nida-tazeen/"
  }
];

function Contact() {
  return (
    <div className="contact-info">
      {contact.length > 0 ? (
        <ul className="contact-list">
          {contact.map((item) => (
            <li key={item.contact_id} className="contact-item">
              <strong>Contact Information</strong>
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
