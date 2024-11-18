import React from 'react';
import '../App.css';

function Experience() {
    const experience = [
        {
            experience_id: 1,
            role: "MuleSoft Integration Consultant / Developer",
            company: "Wise Labs for Thames Water",
            start_month: "September",
            start_year: "2021",
            end_month: "October",
            end_year: "2023",
            job_description: `
                • Conducted code and design reviews, documented best practices, and produced integration patterns.
                • Designed and promoted APIs to the API Manager.
                • Applied security and performance policies, including Client ID and Secret, SLA Policy, and JWT Policy.
                • Scripted custom data transformations using Dataweave.
                • Developed and implemented System API for payment token integration with Azure Database.
                • Engineered AWS Experience API to interact with customer enquiry, balance enquiry, and VoiceSage APIs.
                • Executed end-to-end automation for Pre-Due SMS Reminder through VoiceSage, enhancing customer notifications for approaching due dates for bill payment.
            `
        },
        {
            experience_id: 2,
            role: "MuleSoft Integration Consultant / Developer",
            company: "Wise Labs for Nestle",
            start_month: "September",
            start_year: "2020",
            end_month: "August",
            end_year: "2021",
            job_description: `
                • Utilized Dataweave scripting to access payloads and transform data.
                • Deployed APIs in the Design Center and API Manager, and allocated vcores on the MuleSoft Platform.
                • Developed and crafted API RAML for optimal usage, incorporating validation and security traits.
                • Implemented end-to-end integration for various business systems.
            `
        },
    ];

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
