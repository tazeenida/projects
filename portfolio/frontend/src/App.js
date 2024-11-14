import './App.css';
import Education from './components/Education';
import Skill from './components/Skill';
import Home from './components/Home';
import Project from './components/Project';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Navbar from './components/Navigation';
function App() {
  return (
	<Router>
    <div className="App"> 
	  <Navbar />
        <Routes>
		  <Route path="/education" element={<Education />}/>
		  <Route path="/home" element={<Home />}/>
		  <Route path="/" element={<Navigate to="/Home" />} />
		  <Route path="/skill" element={<Skill />}/>
		  <Route path="/project" element={<Project />}/>
        </Routes>
    </div>
	</Router>
  );
}

export default App;
