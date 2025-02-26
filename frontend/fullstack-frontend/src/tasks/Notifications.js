import React, { useEffect, useState } from "react";
import axios from "axios";

export default function Notifications() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    loadTasks();
  }, []);

  const loadTasks = async () => {
    const result = await axios.get("http://localhost:8080/tasks");
    setTasks(result.data);
  };

  return (
    <div className="container">
      <div className="py-4">
        <h2>Notifications</h2>
        <ul className="list-group">
          {tasks.map((task, index) => (
            <li key={index} className="list-group-item">
              <b>{task.title}</b> - Deadline: {task.deadline}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}
