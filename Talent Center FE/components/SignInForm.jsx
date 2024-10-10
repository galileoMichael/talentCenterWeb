import React, { useState } from 'react';
import eyeClosedIcon from '../components/assets/eye-slash.svg';
import eyeOpenIcon from '../components/assets/eye.svg';
import './SignInForm.css';
import useSignIn from '../hooks/useSignIn';

const SignInForm = ({ onSubmit }) => {
  const [showPassword, setShowPassword] = useState(false);
  const {
    email,
    password,
    error,
    handleEmailChange,
    handlePasswordChange,
    handleSubmit,
  } = useSignIn(onSubmit);

  const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

  return (
    <form className="sign-in-form" onSubmit={handleSubmit}>
      <h2>Welcome Back</h2>
      <p>Please sign in first to explore further on our website</p>
      {error && <p className="error-message">{error}</p>}
      <input
        id="email"
        type="email"
        placeholder="Email"
        value={email}
        onChange={handleEmailChange}
        required
      />
      <div className="password-container">
        <input
          id="password"
          type={showPassword ? 'text' : 'password'}
          placeholder="Password"
          value={password}
          onChange={handlePasswordChange}
          required
        />
        <img
          src={showPassword ? eyeClosedIcon : eyeOpenIcon}
          alt="Toggle Password Visibility"
          onClick={togglePasswordVisibility}
          className="toggle-password-icon"
        />
      </div>
      <button className='login-button' type="submit">
        Login
      </button>
      <button className='login-with-google-button' type="button">
        Login with Google
      </button>
      <p>Don't have an Account? <a href="#">Register Here</a></p>
    </form>
  );
};

export default SignInForm;