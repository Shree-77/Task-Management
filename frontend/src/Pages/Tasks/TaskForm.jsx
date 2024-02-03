import React, { useState } from "react";
import './TaskPage.css'

const TaskForm = ({ onTaskSubmit }) => {
  const [taskData, setTaskData] = useState({
    title: "",
    description: "",
    dueDate: "",
    priority: "",
    completed: false,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setTaskData({ ...taskData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onTaskSubmit(taskData);
    setTaskData({
      title: "",
      description: "",
      dueDate: "",
      priority: "",
      completed: false,
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      <label>
        Task:
        <input
          type="text"
          name="title"
          value={taskData.title}
          onChange={handleChange}
        />
      </label>
      <label>
      <label htmlFor="description">
        Description:
        </label>
      <textarea id="description"
       name="description"
       ></textarea>
      </label>
      <label htmlFor="duedate">
        Due date : 
        <input type="datetime-local" 
        name="date"
        id="duedate" />
      </label>
      <label htmlFor="Priority">
        Priority :
        <select name="priority" id="priority">
          <option value="High">High</option>
          <option value="Medium">Medium</option>
          <option value="Low">Low</option>
        </select>
      </label>
      <label>Status:</label>
        <input type="radio" id="completed" name="status" value="completed" />
        <label for="completed">Completed</label>

        <input type="radio" id="pending" name="status" value="pending" />
        <label for="pending">Pending</label>

        <input type="radio" id="notStarted" name="status" value="notStarted"/>
        <label for="notStarted">Not Started</label>
      <button type="submit">Add Task</button>
    </form>
  );
};

export default TaskForm;
