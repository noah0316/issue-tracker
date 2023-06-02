import { useEffect } from 'react';

import { useNavigate } from 'react-router-dom';

import { fetchData } from '../utils/fetch';

export const Auth = () => {
  const navigate = useNavigate();
  const AUTH_URI = `${process.env.REACT_APP_BASE_URI}/githublogin`;
  const url = new URL(window.location.href);
  const queryCode = url.searchParams.get('code');

  useEffect(() => {
    const getToken = async () => {
      try {
        const response = await fetchData(`${AUTH_URI}?code=${queryCode}`);
        const token = response.token;
        if (!localStorage.getItem('jwtToken')) {
          localStorage.setItem('jwtToken', token);
        }
        navigate('/issues');
      } catch (error) {
        // console.error(error);
      }
    };
    getToken();
  }, [location]);

  return <></>;
};
