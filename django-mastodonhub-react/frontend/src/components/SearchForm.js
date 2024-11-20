import React, { useState } from 'react';
import MusicEvents from './MusicEvents';
import ArtEvents from './ArtEvents';
import WorkshopEvents from './WorkshopEvent';
import SportsEvents from './SportsEvents';

function SearchForm() {
  const [category, setCategory] = useState('');
  const [showMusicEvents, setShowMusicEvents] = useState(false);
  const [showArtEvents, setShowArtEvents] = useState(false);
  const [showWorkshopEvents, setShowWorkshopEvents] = useState(false);
  const [showSportsEvents, setShowSportsEvents] = useState(false);

  const handleCategoryChange = (e) => {
    setCategory(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    switch (category) {
      case 'Music':
        setShowMusicEvents(true);
        setShowArtEvents(false);
        setShowWorkshopEvents(false);
        setShowSportsEvents(false);
        break;
      case 'Art':
        setShowMusicEvents(false);
        setShowArtEvents(true);
        setShowWorkshopEvents(false);
        setShowSportsEvents(false);
        break;
      case 'Workshops':
        setShowMusicEvents(false);
        setShowArtEvents(false);
        setShowWorkshopEvents(true);
        setShowSportsEvents(false);
        break;
      case 'Sports':
        setShowMusicEvents(false);
        setShowArtEvents(false);
        setShowWorkshopEvents(false);
        setShowSportsEvents(true);
        break;
      default:
        setShowMusicEvents(false);
        setShowArtEvents(false);
        setShowWorkshopEvents(false);
        setShowSportsEvents(false);
        break;
    }
  };

  return (
    <div>
      <form onSubmit={handleSubmit} id="Search">
        <label>
          Choose a category:
          <select value={category} onChange={handleCategoryChange}>
            <option value="">Select</option>
            <option value="Music">Music</option>
            <option value="Art">Art</option>
            <option value="Workshops">Workshops</option>
            <option value="Sports">Sports</option>
          </select>
        </label>
        <button type="submit">Search</button>
      </form>
      <div id="SearchResult">
      {showMusicEvents && <MusicEvents />}
      {showArtEvents && <ArtEvents />}
      {showWorkshopEvents && <WorkshopEvents />}
      {showSportsEvents && <SportsEvents />}
      </div>
    </div>
  );
}

export default SearchForm;
