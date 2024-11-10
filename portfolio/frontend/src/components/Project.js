import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axios from 'axios';
import '../App.css'

const backendUrl = 'http://127.0.0.1:8000';

function Project(){
	const[project, setProject] = useState([]);
	const[isLoading, setIsLoading] = useState(false);
	const[error, setError] =  useState(null);
	
	const navigation = useNavigate();
	
	useEffect(()=>{
		const fetchData = async () =>{
			setIsLoading(true);
			setError(null);
			try{
				const response = await axios.get(`${backendUrl}/api/portfolio/project/`);
				setProject(response.data);
			} catch(error){
				console.error('Error fetching project:', error);
				setError(error);
			} finally{
				setIsLoading(false);
			}
		};
		fetchData();
	},[]);
	const handleGoBack = () =>{
				navigation('/Home')

	};

	
	if(isLoading) return<p>Loading...</p>;
	if(error) return <p> Error: {error.message}</p>;
	
	return (
		<div className="Project">
		   <h1>Project</h1>
		   {project.length > 0 ?(
			<ul>
			{project.map((item)=>(
				<li key={item.id}>
				   <strong>{item.name}</strong><br/>
				   {item.project && <span><strong>{item.project}</strong><br/></span>}	
				</li>
			))}
			</ul>
			
		   ):(
			
			<p>No project available.</p>
		   )}
		   <br/>
		  <button onClick={handleGoBack} className="go-back-home">Go back to Home</button>
		</div>
	)
}

export default Project;