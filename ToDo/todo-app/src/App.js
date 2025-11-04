import React, { useState, useEffect } from "react";
import "./App.css";
import TaskForm from "./component/TaskForm";
import TaskList from "./component/TaskList";

function App() {
  const [tasks, setTasks] = useState(() => {
    const savedTasks = localStorage.getItem("tasks");
    return savedTasks ? JSON.parse(savedTasks) : [];
  });

  useEffect(() => {
    localStorage.setItem("tasks", JSON.stringify(tasks));
  }, [tasks]);

  const addTask = (taskText) => {
    setTasks([...tasks, taskText]);
  };

  const deleteTask = (index) => {
    let newtasks = tasks.filter((_,i) => i !== index);
    setTasks(newtasks);
  };

  const editTask = (index, newText) => {
    const updatedTasks = [...tasks];
    updatedTasks[index] = newText;
    setTasks(updatedTasks);
  };

  return (
    <div>
      <center>
        <h1>To-Do List App</h1>
        <TaskForm onAddTask={addTask} />
        <TaskList
          tasks={tasks}
          onDeleteTask={deleteTask}
          onEditTask={editTask}
        />
      </center>
    </div>
  );
}

export default App;
