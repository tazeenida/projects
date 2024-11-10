import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';

function Project() {
  return (
    <Router>
      <Routes>
        <Route path="/bookrecommendationsystem" element={<Navigate to="https://bookrecommendationsystem-qf5o.onrender.com/" replace />} />
      </Routes>
    </Router>
  );
}

export default Project;
