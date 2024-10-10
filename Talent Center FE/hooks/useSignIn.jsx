import { useState, useContext } from 'react';
import { signIn } from '../services/authService';
import { UserContext } from '../context/UserContext.jsx';

const useSignIn = (onSubmit) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const { setUser } = useContext(UserContext);

  const handleEmailChange = (e) => setEmail(e.target.value);
  const handlePasswordChange = (e) => setPassword(e.target.value);

  const handleSubmit = async (event) => {
    event.preventDefault();
    console.log('Submitting form with:', { email, password });

    try {
      const response = await signIn(email, password);
      console.log(response);

      if (response.data) {
        console.log('Login successful:', response.data);
        setUser(response.data); 
        localStorage.setItem('user', JSON.stringify(response.data));
        onSubmit(event);
      }
    } catch (error) {
      console.error('Error during sign-in:', error);
      setError('Login failed. Please check your credentials and try again.');
    }
  };

  return {
    email,
    password,
    error,
    handleEmailChange,
    handlePasswordChange,
    handleSubmit,
  };
};

export default useSignIn;
