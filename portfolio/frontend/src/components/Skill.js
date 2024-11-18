import React from 'react';
import '../App.css';

const skills = [
  {
    skill_id: "0ae12852-4816-4f3b-bc7f-71fd3d3aaf91",
    skill: "Mulesoft Integration"
  },
  {
    skill_id: "19459974-1743-418f-bfc0-e24c36574356",
    skill: "Web Development"
  },
  {
    skill_id: "cd5ae196-7f3a-43f9-b4ea-8b3653f9b52c",
    skill: "UI/UX Design"
  },
  {
    skill_id: "3e5815cb-94f4-4493-9145-7a8eb67026a8",
    skill: "MySQL"
  },
  {
    skill_id: "9f727b54-205b-4111-8ed9-8793f3dd5cbc",
    skill: "Database Design & Normalization"
  },
  {
    skill_id: "696e23db-c333-49c7-ae95-342722d1a7b4",
    skill: "Cascading Style Sheets (CSS)"
  },
  {
    skill_id: "050da328-d67a-4868-81ef-f0e11f6666d4",
    skill: "Problem Solving"
  },
  {
    skill_id: "4819d628-7335-46d6-a37c-8488b4ab79d2",
    skill: "Leadership"
  },
  {
    skill_id: "36530595-6269-4527-acec-8e8b4b484400",
    skill: "Java"
  },
  {
    skill_id: "cbdade6b-ff29-4e93-87b3-ab1534b24933",
    skill: "Vaadin"
  },
  {
    skill_id: "36a5e978-f5b6-408d-879c-5e7a6e01c98e",
    skill: "Spring Boot"
  },
  {
    skill_id: "18324eff-9cec-4ba8-aac7-172752cd24a2",
    skill: "Django"
  },
  {
    skill_id: "bfa4e5d1-1d32-4c1f-83f6-04d4d4238141",
    skill: "PostgreSQL"
  },
  {
    skill_id: "60fe0af1-a6fe-4b28-85eb-fc7015bfc049",
    skill: "React.js"
  },
  {
    skill_id: "1629bea5-0c09-477b-8a86-3daa86ad6830",
    skill: "HyperText Markup Language (HTML)"
  },
  {
    skill_id: "52dd21fb-0c89-4148-86aa-a4e8e0c02b9a",
    skill: "JavaScript"
  },
  {
    skill_id: "6bbaae28-4427-46d7-ab3d-94d3bdc7fc8d",
    skill: "Computer Science"
  },
  {
    skill_id: "7f5f8f34-62bb-49d8-b675-949449231331",
    skill: "REST APIs"
  },
  {
    skill_id: "e815693e-7b41-4389-91b9-d7db0b67257a",
    skill: "Oracle"
  },
  {
    skill_id: "a04fd0ad-6d5c-4698-bc38-3ee5a6f489f4",
    skill: "Web Design"
  }
];

function Skill() {
  return (
    <div className="Skill">
      <h1 className="Skill-header">Skills</h1>
      {skills.length > 0 ? (
        <div className="skill-columns">
          {skills.map((skill) => (
            <div className="skill-item" key={skill.skill_id}>
              <div className="skill-header">
                <strong>{skill.skill}</strong>
              </div>
            </div>
          ))}
        </div>
      ) : (
        <p>No skills available.</p>
      )}
    </div>
  );
}

export default Skill;
