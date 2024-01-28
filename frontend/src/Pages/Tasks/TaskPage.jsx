import React, { useEffect, useState } from "react";
import axios from "axios";
import TaskList from "../../Services/Tasks";
import './TaskPage.css';


const TaskPage = () => {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        const response = await axios.get("/tasks/all");
        setTasks(response.data);
      } catch (error) {
        console.error("Error fetching tasks:", error);
      }
    };

    fetchTasks();
  }, []);

  return (
    <div className="Tasks">
      <TaskList tasks={tasks} />
    </div>
  );
};

export default TaskPage;
