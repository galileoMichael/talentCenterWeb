import React from 'react';
import './NotificationBar.css';

const NotificationBar = ({ message, onClose, onView }) => {
  return (
    <div className="notification-bar">
      <div className="notification-content">
        <span>{message}</span>
        <button onClick={onView} className="view-button">View</button>
        <button onClick={onClose} className="close-button">✕</button>
      </div>
    </div>
  );
};

export default NotificationBar;
