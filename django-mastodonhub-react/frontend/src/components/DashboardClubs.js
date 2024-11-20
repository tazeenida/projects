import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import ClubModal from './clubsModal';
import '../styles.css';

const backendUrl = 'https://django-mastodonhub-react-1.onrender.com';

function ClubsPage() {
  const [clubs, setClubs] = useState([]);
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
      } catch (error) {
        setError(error);
        console.error('Error fetching clubs:', error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchClubs();
  }, []);

  const handleClubsClick = (club) => {
    console.log('Opening modal for', club);
    setActiveItem(club);
    setModal(true);
  };

  return (
    <main>
      <section id="FeaturedEvents" className="featured-events-section">
        <header>
          <h1 className="club-title">Clubs</h1>
        </header>

        {isLoading ? (
          <p>Loading...</p>
        ) : error ? (
          <p>Error: {error.message}</p>
        ) : (
          <section className="filtered-results">
            <div id="Events-container" style={{ display: 'flex', gap: '20px', flexWrap: 'wrap', justifyContent: 'center' }}>
              {clubs.map((club) => (
                <Link key={club.id} className="Events-item" onClick={() => handleClubsClick(club)}>
                  <img
                    className="event-images"
                    src={club.ImageUrl}
                    alt={club.Title}
                    style={{ width: '200px', height: '200px' }}
                  />
                  <div className="event-title">{club.Title}</div>
                </Link>
              ))}
            </div>
            {modal && (
              <ClubModal isOpen={modal} toggle={() => setModal(false)} activeItem={activeItem} />
            )}
          </section>
        )}
      </section>
    </main>
  );
}

export default ClubsPage;
