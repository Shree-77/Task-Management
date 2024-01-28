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
    // Reset the form or clear fields
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
        Title:
        <input
          type="text"
          name="title"
          value={taskData.title}
          onChange={handleChange}
        />
      </label>
      <button type="submit">Add Task</button>
    </form>
  );
};

export default TaskForm;
