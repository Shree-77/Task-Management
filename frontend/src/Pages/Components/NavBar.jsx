import { Link } from 'react-router-dom';
import '../Tasks/TaskPage.css';

const NavBar = ()=>{
    return (
         <header className="Tasks-header">
            <h1>Task Manager</h1>
           <Link to="/create-task">Create Task</Link>
        </header>
        
    )
}
export default NavBar