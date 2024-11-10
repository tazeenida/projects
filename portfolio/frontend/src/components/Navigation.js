import React from 'react';
import { Link } from 'react-router-dom';
import '../App.css'

const Navbar = () => {
    return (
        <nav className="navbar">
            <div className="navbar-container">
                <Link to="/" className="navbar-logo">
                    Nida Tazeen
                </Link>
                <ul className="navbar-menu">
                    <li className="navbar-item">
                        <Link to="/home" className="navbar-link">Home</Link>
                    </li>
                    <li className="navbar-item">
                        <Link to="/education" className="navbar-link">Education</Link>
                    </li>
                    <li className="navbar-item">
                        <Link to="/project" className="navbar-link">Projects</Link>
                    </li>
                    <li className="navbar-item">
                        <Link to="/skill" className="navbar-link">Skills</Link>
                    </li>
                    <li className="navbar-item">
                        <Link to="/contact" className="navbar-link">Contact</Link>
                    </li>
                </ul>
            </div>
        </nav>
    );
};

export default Navbar;
