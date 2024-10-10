import React from 'react';
import featuredIcon from './assets/Featured icon.svg';
import './DownloadModal.css'; 

const DownloadModal = ({ isOpen, onClose }) => {
  if (!isOpen) return null;

  return (
    <div className="download-modal-overlay">
      <div className="download-modal">
        <div className="modal-content">
          <img src={featuredIcon} alt="Success Icon" />
          <h2>Download successful!</h2>
          <p>Your CV has been successfully downloaded.</p>
          <button className='download-cv-button' onClick={onClose}>Confirm</button>
        </div>
      </div>
    </div>
  );
};

export default DownloadModal;
