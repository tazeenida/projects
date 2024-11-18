import React from 'react';
import '../App.css';
import collegeLogo from '../images/college-logo.png';
import pfwCollegeLogo from '../images/Purdue_Fort_Wayne_Mastodons_logo.svg.png';

const imageMap = {
    "St. Francis College for Women": collegeLogo,
    "Purdue University, Fort Wayne, IN": pfwCollegeLogo,
};

function Education() {
    const education = [
        {
            id: 1,
            name: "Purdue University, Fort Wayne, IN",
            start_month: "August",
            start_year: "2023",
            end_month: "December",
            end_year: "2025",
            degree: "Master’s degree in Computer Science",
            university: "Purdue University, Fort Wayne, IN",
            level: "Graduate",
        },
        {
            id: 2,
            name: "St. Francis College for Women, Hyderabad, India",
            start_month: "August",
            start_year: "2015",
            end_month: "April",
            end_year: "2017",
            degree: "Post Graduate Diploma in Human Resource Management",
            university: "St. Francis College for Women, Hyderabad, India",
            level: "Postgraduate",
        },
        {
            id: 3,
            name: "St. Francis College for Women, Hyderabad, India",
            start_month: "August",
            start_year: "2012",
            end_month: "April",
            end_year: "2016",
            degree: "Bachelor of Commerce in International Business",
            university: "St. Francis College for Women, Hyderabad, India",
            level: "Undergraduate",
        },
    ];

    return (
        <div className="Education">
            <h1 className="education-header">Education Timeline</h1>
            {education.length > 0 ? (
                <div className="education-timeline-container">
                    <div className="education-timeline">
                        {education.map((item) => (
                            <div className="timeline-item" key={item.id}>
                                <div className="timeline-content">
                                    <div className="timeline-image">
                                        <img
                                            src={imageMap[item.university] || collegeLogo}
                                            alt={`${item.name} logo`}
                                            className="education-logo"
                                        />
                                    </div>
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
                                    {item.university && <p><strong className="item-text">University:</strong> {item.university}</p>}
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
