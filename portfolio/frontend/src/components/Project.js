import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';
import '../App.css'

const backendUrl = 'https://projects-yybm.onrender.com';

function Project() {
  return (
    <iframe
      src="https://bookrecommendationsystem-qf5o.onrender.com/"
      style={{ width: '100%', height: '100vh', border: 'none' }}
      title="App1"
    />
  );
}

export default Project;