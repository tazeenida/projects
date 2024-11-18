import React, { useState, useEffect } from 'react';
import axios from 'axios';
import '../App.css'; // Add your styles here

const WeatherBanner = () => {
  const [weather, setWeather] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch the weather data for Fort Wayne
    const fetchWeather = async () => {
      try {
        const response = await axios.get(
          'https://api.openweathermap.org/data/2.5/weather?q=Fort+Wayne&units=metric&appid=YOUR_API_KEY'
        );
        setWeather(response.data);
        setLoading(false);
      } catch (err) {
        setError('Failed to fetch weather data');
        setLoading(false);
      }
    };
    fetchWeather();
  }, []);

  if (loading) return <div>Loading weather...</div>;
  if (error) return <div>{error}</div>;

  const { main, weather: weatherInfo } = weather;
  const currentWeather = weatherInfo[0].main.toLowerCase(); // e.g., "clear", "rain", "clouds"
  const temperature = main.temp;

  // Background color and animation based on weather
  let bannerStyle = {};
  if (currentWeather === 'clear') {
    bannerStyle = { background: 'linear-gradient(to bottom, #ffb347, #ffcc33)' }; // Sunny
  } else if (currentWeather === 'rain') {
    bannerStyle = { background: 'linear-gradient(to bottom, #4b79a1, #283e51)' }; // Rainy
  } else if (currentWeather === 'clouds') {
    bannerStyle = { background: 'linear-gradient(to bottom, #e0e0e0, #c5c5c5)' }; // Cloudy
  } else {
    bannerStyle = { background: 'linear-gradient(to bottom, #81d4fa, #0288d1)' }; // Default background
  }

  return (
    <div className="banner-container" style={bannerStyle}>
      <h1>Welcome to My Portfolio!</h1>
      <div className="weather-info">
        <p>Current Weather in Fort Wayne: {currentWeather}</p>
        <p>Temperature: {temperature}°C</p>
      </div>
    </div>
  );
};

export default WeatherBanner;
