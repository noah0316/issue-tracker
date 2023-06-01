import { useEffect } from 'react';

import { useNavigate } from 'react-router-dom';

import { fetchData } from '../utils/fetch';
export const Auth = () => {
  const navigate = useNavigate();
  const AUTH_URI = 'http://13.209.232.172:8080/githublogin';
  const url = new URL(window.location.href);
  const queryCode = url.searchParams.get('code');

  const parseJwt = (token) => {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64)
        .split('')
        .map(function (c) {
          return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
        })
        .join('')
    );

    return JSON.parse(jsonPayload);
  };

  useEffect(() => {
    const getToken = async () => {
      try {
        const response = await fetchData(`${AUTH_URI}?code=${queryCode}`);
        const token = response.token;
        const result = parseJwt(token);
        console.log(result);
        // console.log(response);
        // navigate('/issues');
      } catch (error) {
        console.log(error);
      }
    };
    getToken();
  }, [location, AUTH_URI]);

  return <></>;
};
