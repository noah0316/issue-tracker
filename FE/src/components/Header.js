import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

import { Profile } from './Profile';
import { Icon } from '../assets/Icon';
import { fontSize, fontType } from '../styles/font';

export const Header = ({ userData }) => {
  const navigate = useNavigate();

  const logoInfo = {
    iconType: 'logotypeLarge',
    width: 200,
    height: 40,
    onClick: () => navigate('/issues')
  };

  return (
    <MyHeader>
      <Icon {...logoInfo} />
      <Profile
        isLarge
        userInfo={{
          name: userData?.name,
          profileUrl: userData?.avatar_url
        }}
      />
    </MyHeader>
  );
};

export const MyHeader = styled.header`
  width: 1280px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  padding: 25px;
  box-sizing: border-box;
  ${fontSize.XXL}
  ${fontType.LIGHT}
`;
