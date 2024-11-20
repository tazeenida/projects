
import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import ClubModal from './clubsModal';
import ClubsFilter from './clubsFilter';
import '../styles.css';
import clubsBanner from '../images/clubsBanner.jpg'

const backendUrl = 'https://django-mastodonhub-react-1.onrender.com';

function ClubsPage() {
  const [clubs, setClubs] = useState([]);
  const [filteredClubs, setFilteredClubs] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [activeItem, setActiveItem] = useState({});
  const [modal, setModal] = useState(false);

  useEffect(() => {
    const fetchClubs = async () => {
      setIsLoading(true);
      setError(null);
      try {
        const response = await axios.get(`${backendUrl}/api/mastodonhub/clubs/`);
        setClubs(response.data);
        setFilteredClubs(response.data); 
      } catch (error) {
        setError(error);
        console.error('Error fetching clubs:', error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchClubs();
  }, []);

  const handleFilterChange = (selectedCategory, searchTerm) => {
    let filtered = clubs;

    if (selectedCategory) {
      filtered = filtered.filter((club) => club.Category === selectedCategory);
    }

    if (searchTerm && searchTerm.trim() !== '') {
      filtered = filtered.filter((club) =>
        club.Title.toLowerCase().includes(searchTerm.toLowerCase())
      );
    }

    setFilteredClubs(filtered); 
  };

  const handleClubsClick = (club) => {
    console.log('Opening modal for', club);
    setActiveItem(club);
    setModal(true);
  };

  return (
    <main>
        <section id="Banner">
        <img src={clubsBanner} alt="Header" width="100%" height="400px" />
        <div className="Clubs-Banner">Discover exciting Clubs with MastodonHub</div>
      </section>
      <header>
        
        </header>
      <section id="FeaturedClubs" className="featured-events-section">
        <section>
      <ClubsFilter onFilterChange={handleFilterChange} />
      </section>
        <section className="filtered-results" styles={{margin:'20px'}}>
          {isLoading ? (
            <p>Loading...</p>
          ) : error ? (
            <p>Error: {error.message}</p>
          ) : (
            <div id="Clubs-container">
              {filteredClubs.map((club) => (
                <Link key={club.id} className="Events-item" onClick={() => handleClubsClick(club)}>
                  <div>
                  <img
                    className="event-images"
                    src={club.ImageUrl}
                    alt={club.Title}
                    style={{ width: '200px', height: '200px' }}
                  />
                  <div className="event-title">{club.Title}</div>
                  </div>
                  <ClubModal isOpen={modal} toggle={() => setModal(false)} activeItem={activeItem} />
                </Link>
              ))}
            </div>
          )}
        </section>
      </section>
    </main>
  );
}

export default ClubsPage;
