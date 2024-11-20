import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import ClubModal from './clubsModal';

const backendUrl = 'https://django-mastodonhub-react-1.onrender.com';

function FeaturedClubs() {
  const [FeaturedClubs, setClubs] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [activeItem, setActiveItem] = useState({});
  const [modal, setModal] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      setError(null);
      try {
        const response = await axios.get(`${backendUrl}/api/mastodonhub/clubs/`);
        setClubs(response.data);
      } catch (error) {
        console.error("Error fetching clubs:", error);
        setError(error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []);

  if (isLoading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }

  const handleFeatureClick = (FeaturedClubs) => {
    console.log('Opening modal for', FeaturedClubs);
    setActiveItem(FeaturedClubs);
    setModal(true);
  };

  const filteredFeaturedClubs = FeaturedClubs.filter((event) => event.Category === 'Featured');
  
  return (
    <div>
      <h1 class="event-title">Featured Clubs</h1>
      <section id="FeaturedClubs">
        <div style={{ display: 'flex', flexDirection: 'row', backgroundColor: 'black' }} className="event-images">
          {filteredFeaturedClubs.map((item) => (
            <Link className="featured-club-link"  onClick={() => handleFeatureClick(item)}>
              <img className="club-images" src={item.ImageUrl} alt="club" />
              <div className="club-title">{item.Title}</div>
            </Link>
          ))}
          <ClubModal isOpen={modal} toggle={() => setModal(false)} activeItem={activeItem} />
        </div>

      </section>
    </div>
  );
}

export default FeaturedClubs;
