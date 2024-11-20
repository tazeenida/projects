import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const backendUrl = 'https://django-mastodonhub-react-1.onrender.com';

function SportsEvents() {
  const [SportsEvents, setEvents] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

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
  const filteredSportsEvents = SportsEvents.filter((event) => event.Category === 'Sports');
  return (
    <div>
      <section id="SportsEvents">
        <h1>Sports Events</h1>
        <div style={{ display: 'flex', flexDirection: 'row', backgroundColor: 'black' }} className="event-images">
        <div id="Events-container" style={{ display: 'flex', flexDirection: 'row', backgroundColor: 'black' }} className="event-images">
          {filteredSportsEvents.map((SportsEvents) => (
            <Link className="Events-item">
              <img className="event-images" src={SportsEvents.ImageUrl} alt="Event" />
                <div className="event-title">{SportsEvents.Title}</div>
              <div className="event-date">{SportsEvents.Date}</div>
              <div className="event-description">{SportsEvents.Description}</div>
              <div className="event-time">{SportsEvents.StartTime} - {SportsEvents.EndTime}</div>
              <div className="event-location">{SportsEvents.Location}</div>
              <button onClick={() => addEventToCalendar(SportsEvents)}>Add Event</button>              
            </Link>
          ))}
        </div>
        </div>
      </section>
    </div>
  );
}

export default SportsEvents;
