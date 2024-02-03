import { Routes, Route } from "react-router-dom";
import TaskPage from './Pages/Tasks/TaskPage';
import NavBar from './Pages/Components/NavBar';
import TaskForm from "./Pages/Tasks/TaskForm";
import CreateProjectPage from "./Pages/Create-Project";

const App = () => {
    return (
  
        <div>
           <NavBar />
           <Routes>
        <Route path="/" element={<CreateProjectPage />} />
        <Route path="/tasks" element={<TaskPage />} />
        <Route path="/create-task" element={<TaskForm />} />
      </Routes>
        </div>
      
  );
};

export default App;
