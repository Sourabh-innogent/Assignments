import React, { useState } from "react";

function TaskForm({ onAddTask }) {
  const [taskText, setTaskText] = useState("");

  const handleAdd = () => {
    onAddTask(taskText); // send to parent (App)
    setTaskText(""); // clear input field
  };

  return (
    <div className="task-form">
      <input
        type="text"
        placeholder="Enter a task..."
        value={taskText}
        onChange={(event) => setTaskText(event.target.value)}
      />
      <button onClick={handleAdd}>Add</button>
    </div>
  );
}

export default TaskForm;
