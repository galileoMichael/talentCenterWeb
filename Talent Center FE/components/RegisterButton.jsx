import React from 'react';
import './RegisterButton.css'

const RegisterButton = ({ onClick }) => {
  return <button className='register-button' onClick={onClick}>Register</button>;
};

export default RegisterButton;
