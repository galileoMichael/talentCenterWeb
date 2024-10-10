import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LandingPage from './pages/LandingPage';
import MainPage from './pages/MainPage';
import Modal from './components/Modal';
import SignInForm from './components/SignInForm';
import RegisterForm from './components/RegisterForm';
import { UserProvider } from './context/UserContext';

const App = () => {
  const [isSignInModalOpen, setSignInModalOpen] = useState(false);
  const [isRegisterModalOpen, setRegisterModalOpen] = useState(false);
  const [wishlist, setWishlist] = useState([]);

  const openSignInModal = () => setSignInModalOpen(true);
  const closeSignInModal = () => setSignInModalOpen(false);

  const openRegisterModal = () => setRegisterModalOpen(true);
  const closeRegisterModal = () => setRegisterModalOpen(false);

  const handleSignIn = (event) => {
    event.preventDefault();
    
    console.log("Sign-in form submitted");
    closeSignInModal();
    window.location.href = "/main";
  };

  const handleRegister = (event) => {
    event.preventDefault();
    
    console.log("Register form submitted");
    closeRegisterModal();
    window.location.href = "/main";
  };

  const addToWishlist = (talent) => {
    setWishlist(prevWishlist => [...prevWishlist, talent]);
  };
  
  return (
    <UserProvider>
      <Router>
        <Routes>
          <Route 
            path="/" 
            element={
              <LandingPage 
                openSignInModal={openSignInModal} 
                openRegisterModal={openRegisterModal} 
              />
            } 
          />
          <Route 
            path="/main" 
            element={
              <MainPage 
                openSignInModal={openSignInModal} 
                openRegisterModal={openRegisterModal} 
                addToWishlist={addToWishlist}
              />
            } 
          />
        </Routes>
        <Modal isOpen={isSignInModalOpen} onClose={closeSignInModal}>
          <SignInForm onSubmit={handleSignIn} />
        </Modal>
        <Modal isOpen={isRegisterModalOpen} onClose={closeRegisterModal}>
          <RegisterForm onSubmit={handleRegister} />
        </Modal>
      </Router>
    </UserProvider>
  );
};

export default App;
