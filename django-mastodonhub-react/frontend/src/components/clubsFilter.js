import React, { useEffect, useState } from 'react';
import axios from 'axios';

const backendUrl = 'https://django-mastodonhub-react-1.onrender.com';

function ClubsFilter({ onFilterChange }) {
  const [uniqueCategories, setUniqueCategories] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axios.get(`${backendUrl}/api/mastodonhub/clubs/`);
        const categories = [...new Set(response.data.map((club) => club.Category))];
        setUniqueCategories(['All', ...categories]); // Add "All" as the first option
      } catch (error) {
        console.error('Error fetching categories:', error);
      }
    };

    fetchCategories();
  }, []);

  const handleCategoryChange = (event) => {
    const selectedCategory = event.target.value;
    const category = selectedCategory === 'All' ? null : selectedCategory; 
    onFilterChange(category, searchTerm); 
  };

  return (
    <div>
      <label htmlFor="categoryFilter">Filter by Category:</label>
      <select id="categoryFilter" onChange={handleCategoryChange} defaultValue="">
        {uniqueCategories.map((category) => (
          <option key={category} value={category}>
            {category}
          </option>
        ))}
      </select>
    </div>
  );
}

export default ClubsFilter;
