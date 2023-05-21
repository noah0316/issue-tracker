import { useContext } from 'react';

import styled from 'styled-components';

import { Profile } from './Profile';
import { Icon } from '../assets/Icon';
import { IssueListContext } from '../pages/IssueList';
import { fontSize, fontType } from '../styles/font';

const MyHeader = styled.header`
  width: 1280px;
  margin: 0 auto;
  display: flex;
  justify-content: space-between;
  padding: 25px;
  box-sizing: border-box;
  ${fontSize.XXL}
  ${fontType.LIGHT}
`;

export const Header = ({ text }) => {
  const issueData = useContext(IssueListContext);

  const logoInfo = {
    iconType: 'logotypeLarge',
    width: 200,
    height: 40,
    isSmall: false,
    userInfo: issueData?.userInfo
  };

  return (
    <MyHeader>
      {text || (
        <>
          <Icon {...logoInfo} />
          <Profile isSmall={logoInfo.isSmall} userInfo={logoInfo.userInfo} />
        </>
      )}
    </MyHeader>
  );
};