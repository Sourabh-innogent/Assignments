import React from "react";
import TaskItem from "./TaskItem";

function TaskList({ tasks, onDeleteTask, onEditTask }) {
  return (
    <div className="task-list">
      <center><h2>Tasks</h2></center>
      {tasks.length === 0 ? (
        <p>No tasks yet!</p>
      ) : (
        tasks.map((task, index) => (
          <TaskItem
            key={index}
            task={task}
            index={index+1}         
            onDeleteTask={onDeleteTask}
            onEditTask={onEditTask}
          />
        ))
      )}
    </div>
  );
}

export default TaskList;
