import React, { useState } from 'react';

const CreateProjectPage = () => {
  // Dummy data for projects
  const [projects, setProjects] = useState([
    { id: 1, title: 'Project 1' },
    { id: 2, title: 'Project 2' },
    { id: 3, title: 'Project 3' },
  ]);

  const handleCreateProject = () => {
    // Implement logic to create a new project
    // This can include opening a modal, navigating to a new page, etc.
    console.log('Create Project button clicked!');
  };

  return (
    <div>
      <h1>Your Projects</h1>

      {/* List of existing projects */}
      <ul>
        {projects.map((project) => (
          <li key={project.id}>{project.title}</li>
        ))}
      </ul>

      {/* Button to create a new project */}
      <div style={{ textAlign: 'center', marginTop: '20px' }}>
        <button onClick={handleCreateProject}>Create Project</button>
      </div>
    </div>
  );
};

export default CreateProjectPage;
