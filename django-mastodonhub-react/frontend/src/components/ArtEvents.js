import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const backendUrl = 'https://django-mastodonhub-react-1.onrender.com';

function ArtEvents() {
  const [artEvents, setArtEvents] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      setIsLoading(true);
      setError(null);
      try {
        const response = await axios.get(`${backendUrl}/api/mastodonhub/events/`);
        setArtEvents(response.data);
      } catch (error) {
        console.error('Error fetching events:', error);
        setError(error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []);

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

  const filteredArtEvents = artEvents.filter((event) => event.Category === 'Art');

  return (
    <div>
      <section id="ArtEvents">
        <h1>Art Events</h1>
        <div
          id="Events-container"
          style={{ display: 'flex', flexDirection: 'row', backgroundColor: 'black' }}
          className="event-images"
        >
          {filteredArtEvents.map((event, index) => (
            <div key={index} className="Events-item">
              <Link to="#">
                <img className="event-images" src={event.ImageUrl} alt="Event" />
                <div className="event-title">{event.Title}</div>
                <div className="event-date">{event.Date}</div>
                <div className="event-description">{event.Description}</div>
                <div className="event-time">
                  {event.StartTime} - {event.EndTime}
                </div>
                <div className="event-location">{event.Location}</div>
              </Link>
              <button onClick={() => addEventToCalendar(event)}>Add Event</button>
            </div>
          ))}
        </div>
      </section>
    </div>
  );
}

export default ArtEvents;
