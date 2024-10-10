import React from 'react';
import './LandingPage.css';
import backgroundImage from '../components/assets/LandingBackground.svg';
import LogoIcon from '../components/assets/logotujuhsembilan.png';
import LogoIcon2 from '../components/assets/logotujuhsembilan 2.png';
import FBIcon from '../components/assets/facebook.png';
import IGIcon from '../components/assets/instagram.png';
import YTIcon from '../components/assets/youtube.png';
import RegisterButton from '../components/RegisterButton';
import SignInButton from '../components/SignInButton';

const LandingPage = ({ openSignInModal, openRegisterModal }) => {
  return (
    <div className="landing-container" style={{ backgroundImage: `url(${backgroundImage})` }}>
      <header>
        <div className="logo-container">
          <img src={LogoIcon} alt="Logo" className="logo" />
          <h1>Talent Center 79</h1>
        </div>
        <div className="button-container">
          <RegisterButton onClick={openRegisterModal} />
          <SignInButton onClick={openSignInModal} />
        </div>
      </header>
      <main>
        <h2 className="welcome-text">Welcome to Talent Center 79</h2>
        <div className="input-container">
          <input type="text" placeholder='Try "JavaScript"' />
        </div>
        <div className="popular-section">
          <p className="popular-text">Popular</p>
          <div className="popular-tags">
            <button>JavaScript</button>
            <button>Scrum Master</button>
            <button>VueJS</button>
            <button>Web Front-End Developer</button>
            <button>ReactJS</button>
          </div>
        </div>
      </main>
      <footer>
        <div className="footer-links">
          <div className="footer-column">
            <h4>Useful Links</h4>
            <a href="#">Home</a>
            <a href="#">Our Technologies</a>
            <a href="#">Why Choose Us</a>
            <a href="#">Testimonials</a>
            <a href="#">Contact</a>
          </div>
          <div className="footer-column">
            <h4>Contact Us</h4>
            <p>Address: Kompleks Terasana No.6A</p>
            <p>Jalan Cihampelas (Bawah)</p>
            <p>Bandung 40171</p>
            <p>Phone: (022) 20504545</p>
            <div className="social-links">
              <a href="#">Follow Us On</a>
              <img src={FBIcon} alt="FBIcon" className="FBIcon" />
              <img src={IGIcon} alt="IGIcon" className="IGIcon" />
              <img src={YTIcon} alt="YTIcon" className="YTIcon" />
            </div>
            <img src={LogoIcon2} alt="Logo2" className="logo2" />
          </div>
        </div>
        <div className="copyright-text">
          <h4>© Copyright 2020</h4>
          <h2>Privacy Policy Design</h2>
          <h2>By Tujuh Sembilan</h2>
        </div>
      </footer>
    </div>
  );
};

export default LandingPage;