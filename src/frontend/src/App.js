import React, { useState, useEffect } from 'react';
import logo from './logo.svg';
import axios from 'axios';
import './App.css';

function App() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await axios.get('/tasks/all');
        setTasks(response.data);
      } catch (error) {
        console.error('Error fetching tasks:', error);
      }
    };

    fetchTasks();
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          {/* Display Task Name if needed */}
          {/* Task Name: {taskName} */}
        </p>
        <h2>Task List</h2>
        <ul>
          {tasks.map((task) => (
            <li key={task.taskId}>
              {`Title: ${task.title}, Description: ${task.description}, Due Date: ${task.dueDate}, Priority: ${task.priority}, Completed: ${task.completed}`}
            </li>
          ))}
        </ul>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
