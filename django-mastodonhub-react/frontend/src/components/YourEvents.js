import React, { useState, useEffect } from 'react';

const YourEvents = () => {
  const [calendarEvents, setCalendarEvents] = useState([]);

  useEffect(() => {
    const storedEvents = localStorage.getItem('calendarEvents');
    if (storedEvents) {
      setCalendarEvents(JSON.parse(storedEvents));
    }
  }, []);

  const eventExists = (event) => {
    return calendarEvents.some(
      (existingEvent) => 
        existingEvent.title === event.title && 
        existingEvent.startTime === event.startTime
    );
  };

  const removeEvent = (index) => {
    const updatedEvents = calendarEvents.filter((_, i) => i !== index);
    setCalendarEvents(updatedEvents);
    localStorage.setItem('calendarEvents', JSON.stringify(updatedEvents));
  };

  const clearEvents = () => {
    localStorage.removeItem('calendarEvents');
    setCalendarEvents([]);
  };

  const addEvent = (event) => {
    if (eventExists(event)) {
      console.log("Event already exists in the calendar.");
      return;
    }

    const updatedEvents = [...calendarEvents, event];
    setCalendarEvents(updatedEvents);
    localStorage.setItem('calendarEvents', JSON.stringify(updatedEvents));
  };

  return (
    <div>
      <h2>Your Events</h2>
      <button onClick={clearEvents}>Clear All Events</button>
      <div id="Events-container">
        {calendarEvents.length === 0 ? (
          <div>No events found.</div>
        ) : (
          calendarEvents.map((event, index) => (
              <div key={index} className="events-item">
                {event.imageUrl && (
                  <img
                  className="event-images"
                    src={event.imageUrl}
                    alt={event.title}
                  />
                )}
                <div className="event-title">{event.title}</div>
                <div className="event-description">{event.description}</div>
                <div className="event-location">{event.location}</div>
                <div className="event-time">{`${event.startTime} - ${event.endTime}`}</div>
                <button onClick={() => removeEvent(index)}>Remove Event</button>
              </div>
          ))
        )}
      </div>
    </div>
  );
};

export default YourEvents;
