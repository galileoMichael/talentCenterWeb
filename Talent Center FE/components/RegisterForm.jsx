import React, { useState } from 'react';
import eyeClosedIcon from '../components/assets/eye-slash.svg';
import eyeOpenIcon from '../components/assets/eye.svg';
import './RegisterForm.css';


const RegisterForm = ({ onSubmit }) => {  
  const [showPassword1, setShowPassword1] = useState(false);  
  const [showPassword2, setShowPassword2] = useState(false);  

  const togglePassword1Visibility = () => {
    setShowPassword1(!showPassword1);
  };

  const togglePassword2Visibility = () => {
    setShowPassword2(!showPassword2);
  };
  
  return (
    <form className="register-form" onSubmit={onSubmit}>
      <h2>Register</h2>
      <p>Register so you can choose and request our talent</p>
      <div className="input-group">
        <input type="text" placeholder="First Name" required />
        <input type="text" placeholder="Last Name" required />
      </div>
      <input id="email" type="email" placeholder="Email" required />
      <div className="password-container">
        <input id="password" type={showPassword1 ? "text" : "password"}  placeholder="Password" required />
        <img
            src={showPassword1 ? eyeClosedIcon : eyeOpenIcon}
            alt="Toggle Password Visibility"
            onClick={togglePassword1Visibility}
            className="toggle-password-icon"              
        />
      </div>
      <ul className="password-requirements">
        <li>Password is at least 8 characters long</li>
        <li>Password contains at least one letter and one number</li>
      </ul>
      <div className="retype-password-container">
        <input id="password" type={showPassword2 ? "text" : "password"}  placeholder="Type in your password again" required />
        <img
            src={showPassword2 ? eyeClosedIcon : eyeOpenIcon}
            alt="Toggle Password Visibility"
            onClick={togglePassword2Visibility}
            className="toggle-password-icon"              
        />
      </div>
      <div className="input-group">
        <label>
          <input type="radio" name="gender" value="Male" required /> Male
        </label>
        <label>
          <input type="radio" name="gender" value="Female" required /> Female
        </label>
      </div>
      <input type="date" placeholder="Date of birth" required />
      <select placeholder="Client Position" required>
        <option value="">Client Position</option>
        <option value="position1">Position 1</option>
        <option value="position2">Position 2</option>
      </select>
      <input type="text" placeholder="Agency Name" required />
      <textarea placeholder="Agency Address" required></textarea>
      <button type="submit">
        Register
      </button>
      <button type="button">
        Register with Google
      </button>
      <p>Already have an Account? <a href="#">Sign In Here</a></p>
    </form>
  );
};

export default RegisterForm;