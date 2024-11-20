import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import FeatureModal from './FeatureModal';

const backendUrl = 'https://django-mastodonhub-react-1.onrender.com';

function FeaturedEvents() {
  const [FeaturedEvents, setEvents] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [activeItem, setActiveItem] = useState({});
  const [modal, setModal] = useState(false);

  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      setError(null);
      try {
        const response = await axios.get(`${backendUrl}/api/mastodonhub/events/`);
        setEvents(response.data);
      } catch (error) {
        console.error("Error fetching events:", error);
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

  const handleFeatureClick = (FeaturedEvents) => {
    console.log('Opening modal for', FeaturedEvents);
    setActiveItem(FeaturedEvents);
    setModal(true);
  };
  
  const addEventToCalendar = (event) => {
    const storedEvents = localStorage.getItem('calendarEvents');
    const currentEvents = storedEvents ? JSON.parse(storedEvents) : [];

    const isDuplicate = currentEvents.some(
      (e) => e.title === event.Title
    );

    if (isDuplicate) {
      alert('Event already added.');
      return;
    }

    currentEvents.push({
      title: event.Title,
      description: event.Description,
      location: event.Location,
      startTime: event.StartTime,
      endTime: event.EndTime,
      imageUrl: event.ImageUrl,
    });

    localStorage.setItem('calendarEvents', JSON.stringify(currentEvents));
    alert('Event successfully added.');
  };

  if (isLoading) {
    return <p>Loading...</p>;
  }

  if (error) {
    return <p>Error: {error.message}</p>;
  }
  const filteredFeaturedEvents = FeaturedEvents.filter((event) => event.Category === 'Featured');
  
  return (
    <div>
      <h1 class="event-title">Featured Events</h1>
      <section id="FeaturedEvents">
        <div style={{ display: 'flex', flexDirection: 'row', backgroundColor: 'black' }} className="event-images">
          {filteredFeaturedEvents.map((FeaturedEvents , index) => (
            <div  id="FeaturedEvents" key={index}>
            <Link className="featured-event-link"  onClick={() => handleFeatureClick(FeaturedEvents)}>
              <img className="event-images" src={FeaturedEvents.ImageUrl} alt="Event" />
              <div className="event-title">{FeaturedEvents.Title}</div>
            </Link>
             <button onClick={() => addEventToCalendar(FeaturedEvents)}>Add Event</button>
            </div>
          ))}
          <FeatureModal isOpen={modal} toggle={() => setModal(false)} activeItem={activeItem} />
        </div>

      </section>
    </div>
  );
}

export default FeaturedEvents;
