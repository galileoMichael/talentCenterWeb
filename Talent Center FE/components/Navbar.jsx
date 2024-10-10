import React from 'react';
import './Navbar.css';
import LogoIcon from '../components/assets/logotujuhsembilan 3.png';
import UserIcon from './assets/User Icon.svg';
import RegisterButton from './RegisterButton';
import SignInButton from './SignInButton';

const Navbar = ({ openSignInModal, openRegisterModal }) => {
    return (
        <nav className="navbar">
            <div className="logo-container">
                <img src={LogoIcon} alt='Logo' className='logo' />
            </div>
            <input type="text" className="search-bar" placeholder="Search Tags" />
            <div className="button-container">
                <RegisterButton onClick={openRegisterModal} />
                <SignInButton onClick={openSignInModal} />
            </div>
            <div className="user-icon-container">
                <img src={UserIcon} alt='user-icon-logo' className="user-icon" />
                <span className="user-name">user79</span>
            </div>
        </nav>
    );
};

export default Navbar;
