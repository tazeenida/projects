import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import banner_event from '../images/banner (2).jpg';
import SearchForm from './SearchForm';
import YourEvents from './YourEvents';
import FeaturedEvents from './FeaturedEvents';
import axios from 'axios';

const Events = () => {
  
  return (
    <div>
      <section id="Banner">
        <img src={banner_event} alt="banner_event" width="100%" height="400px" />
        <div className="Events-Banner">Discover exciting events with MastodonHub</div>
      </section>
      <SearchForm/>
      <FeaturedEvents/>
      <YourEvents/>
  </div>
  );
};

export default Events;
