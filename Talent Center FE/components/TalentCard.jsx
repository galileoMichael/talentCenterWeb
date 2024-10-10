import React, { useState, useContext } from 'react';
import { addTalentToList, downloadCV } from '../services/talentService';
import { UserContext } from '../context/UserContext';
import AddToListIcon from './assets/AddToList Button.svg';
import InWishlistIcon from './assets/InWishlist Button.svg';
import DownloadCVIcon from './assets/Download CV Button.svg';
import SeeDetailsIcon from './assets/See Details Button.svg';
import AvailableIcon from './assets/Available Icon.svg';
import NotAvailableIcon from './assets/Not Available Icon.svg';
import NotificationBar from './NotificationBar';
import DownloadModal from './DownloadModal';
import './TalentCard.css';

const TalentCard = ({ talent }) => {
    const [inWishlist, setInWishlist] = useState(false);
    const [showNotification, setShowNotification] = useState(false);
    const [showDownloadModal, setShowDownloadModal] = useState(false);
    const { user } = useContext(UserContext);

    const handleAddToList = async () => {
        if (!user) {
            alert("Please sign in to add to wishlist");
            return;
        }

        try {
            const requestBody = {
                userId: user.userId,
                talentId: talent.talentId,
            };
            const response = await addTalentToList(requestBody);
            console.log(response);
            if (response.statusCode === 200) {
                setInWishlist(true);
                setShowNotification(true);
            }
        } catch (error) {
            console.error("Failed to add talent to list", error);
            alert("Failed to add talent to wishlist");
        }
    };

    const closeNotification = () => setShowNotification(false);

    const handleDownloadCV = async () => {
        if (!user) {
          alert("Please sign in to download CV");
          return;
        }
    
        try {
          const requestBody = {
            talentId: talent.talentId,
          };
    
          const response = await downloadCV(requestBody);
          console.log('CV Download Response:', response);
          setShowDownloadModal(true);
    
          if (response.statusCodeValue === 200) {
            setShowDownloadModal(true);
          } else {
            console.error('Unexpected response:', response);
            alert('Failed to download CV');
          }
        } catch (error) {
          console.error("Failed to download CV", error);
          alert("Failed to download CV");
        }
      };
    

    return (
        <div className="talent-card">
            <div className="header">
                <img src={talent.talentPhotoUrl} alt={`${talent.talentName}'s profile`} className="profile-picture" />
                <div className="status">
                    <span className={talent.talentAvailability ? 'available' : 'not-available'}>
                        {talent.talentAvailability ? (
                            <img src={AvailableIcon} alt='Available Icon' />) : (
                            <img src={NotAvailableIcon} alt='Not Available Icon' />
                            )}
                    </span>
                    <h3>{talent.talentName}</h3>
                    <p>{talent.talentExperience}+ Years of Experience • {talent.talentLevel}</p>
                </div>
            </div>
            <div className="info">
                <div className="info-item">
                    <strong>Position:</strong> {talent.positions.map(position => position.positionName).join(' • ')}
                </div>
                <div className="info-item">
                    <strong>Skill Set:</strong> {talent.skillsets.map(skill => skill.skillName).join(', ')}
                </div>
            </div>
            <div className="actions">
                <button onClick={handleDownloadCV}><img src={DownloadCVIcon} alt='Download CV'/></button>
                <button onClick={handleAddToList} disabled={inWishlist}>
                    {inWishlist ? (
                    <img src={InWishlistIcon} alt="In Wishlist" /> ) : (
                    <img src={AddToListIcon} alt="Add to List" /> )} 
                </button>
                <button className="see-details"><img src={SeeDetailsIcon} alt='See Details'/></button>
            </div>
            {showNotification && (
                <NotificationBar 
                    message="Talent added to wishlist" 
                    onClose={closeNotification} 
                    onView={() => { console.log('View button clicked'); }}
                />
            )}
            <DownloadModal 
                isOpen={showDownloadModal} 
                onClose={() => setShowDownloadModal(false)} />
        </div>
    );
};

export default TalentCard;
