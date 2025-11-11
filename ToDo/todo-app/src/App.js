import React, { useState, useEffect } from "react";
import "./App.css";
import TaskForm from "./component/TaskForm";
import TaskList from "./component/TaskList";

function App() {
  const [tasks, setTasks] = useState(() => {
    try{
    const savedTasks = localStorage.getItem("tasks");
    return savedTasks ? JSON.parse(savedTasks) : [];
    }catch{
      return [];
    }
  });

  useEffect(() => {
    localStorage.setItem("tasks", JSON.stringify(tasks));
  }, [tasks]);

  const addTask = (taskText) => {
    setTasks([...tasks, taskText]);
  };

  const deleteTask = (index) => {
   const filteredTasks = tasks.filter((_, i) => i !== index);
    setTasks(filteredTasks);
  };

  const editTask = (index, newText) => {
    const updatedTasks = [...tasks];
    updatedTasks[index] = newText;
    setTasks(updatedTasks);
  };

 return (
  <div className="app">
    <h1>To-Do List App</h1>
    <TaskForm onAddTask={addTask} />
    <TaskList
      tasks={tasks}
      onDeleteTask={deleteTask}
      onEditTask={editTask}
    />
  </div>
);
}

export default App;
