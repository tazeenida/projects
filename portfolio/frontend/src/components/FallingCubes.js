import React, { useEffect, useState } from 'react';
import '../App.css'; // Include the CSS from above here

const FallingCubes = () => {
  const [cubes, setCubes] = useState([]);

  useEffect(() => {
    // Generate random cubes
    const generateCubes = () => {
      const cubeArray = Array.from({ length: 50 }, (_, index) => ({
        id: index,
        size: Math.floor(Math.random() * 30) + 10, // Random size between 10px and 40px
        left: Math.random() * 100 + '%', // Random horizontal position
        duration: Math.random() * 5 + 5, // Random duration between 5s and 10s
        delay: Math.random() * 2, // Random delay between 0s and 2s
        rotateX: Math.random() * 360, // Random X-axis rotation
        rotateY: Math.random() * 360, // Random Y-axis rotation
      }));
      setCubes(cubeArray);
    };

    generateCubes();
  }, []);

  return (
    <div className="cube-container">
      {/* Fixed Text */}
      <div className="welcome-text">
        <h1 className="welcome-text">Welcome to My Portfolio!</h1>
      </div>

      {/* Falling Cubes */}
      {cubes.map((cube) => (
        <div
          key={cube.id}
          className="cube"
          style={{
            width: `${cube.size}px`,
            height: `${cube.size}px`,
            left: cube.left,
            animationDuration: `${cube.duration}s`,
            animationDelay: `${cube.delay}s`,
            transform: `rotateX(${cube.rotateX}deg) rotateY(${cube.rotateY}deg)`, // Apply random rotation
          }}
        />
      ))}
    </div>
  );
};

export default FallingCubes;
