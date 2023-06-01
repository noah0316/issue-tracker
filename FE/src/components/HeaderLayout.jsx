import { useState, useEffect } from 'react';

import { Outlet } from 'react-router-dom';
import styled from 'styled-components';

import { Header } from './Header';
import { parseJwt } from '../utils/parseJwt';

export const HeaderLayout = () => {
  const [user, setUser] = useState(null);

  const initData = async () => {
    const userData = parseJwt(localStorage.jwtToken);
    setUser(userData);
  };

  useEffect(() => {
    initData();
  }, []);

  return (
    <>
      <Header userData={user?.userProfile} />
      <DefaultLayout>
        <Outlet context={{ user }} />
      </DefaultLayout>
    </>
  );
};

const DefaultLayout = styled.div`
  width: 100%;
`;
