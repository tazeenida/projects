import React from 'react';
import { Link } from 'react-router-dom';
import { FaHome, FaGraduationCap, FaProjectDiagram, FaTools } from 'react-icons/fa'; // Import necessary icons
import '../App.css';
import Contact from './Contact';

const Navbar = () => {
    return (
        <nav className="navbar">
            <div className="navbar-container">
                <Link to="/" className="navbar-logo">
                    Nida Tazeen
                </Link>
                <ul className="navbar-menu">
                    <li className="navbar-item">
                        <a href="#Banner" className="navbar-link">
                            <FaHome style={{ marginRight: '8px' }} /> Home
                        </a>
                    </li>
                    <li className="navbar-item">
                        <a href="#education" className="navbar-link">
                            <FaGraduationCap style={{ marginRight: '8px' }} /> Education
                        </a>
                    </li>
                    <li className="navbar-item">
                        <a href="#project" className="navbar-link">
                            <FaProjectDiagram style={{ marginRight: '8px' }} /> Projects
                        </a>
                    </li>
                    <li className="navbar-item">
                        <a href="#skill" className="navbar-link">
                            <FaTools style={{ marginRight: '8px' }} /> Skills
                        </a>
                    </li>
                </ul>
                <div className="contact-info">
                    <Contact />
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
