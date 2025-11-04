import React, { useState } from "react";

function TaskItem({ task, index, onDeleteTask, onEditTask }) {
  const [isEditing, setIsEditing] = useState(false);
  const [newText, setNewText] = useState(task);

  const handleSave = () => {
    if (newText.trim() === "") return;
    onEditTask(index, newText);
    setIsEditing(false);
  };

  return (
    <div className="task-item">
      {isEditing ? (
        <>
          <input
            type="text"
            value={newText}
            onChange={(e) => setNewText(e.target.value)}
          />
          <button onClick={handleSave}>Save</button>
        </>
      ) : (
        <>
          <span>{task}</span>
          <div className="buttons">
            <button onClick={() => setIsEditing(true)}>Edit</button>
            <button onClick={() => onDeleteTask(index)}>Delete</button>
          </div>
        </>
      )}
    </div>
  );
}

export default TaskItem;
