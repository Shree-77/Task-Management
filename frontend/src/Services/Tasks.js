import React from "react";

const TaskList = ({ tasks }) => {
  return (
    <ul>
      {tasks.map((task) => (
        <li key={task.taskId}>
          <strong>{task.title}</strong>
          <p>{task.description}</p>
          <p>Due Date: {task.dueDate}</p>
          <p>Priority: {task.priority}</p>
          <p>Completed: {task.completed ? "Yes" : "No"}</p>
        </li>
      ))}
    </ul>
  );
};

export default TaskList;
