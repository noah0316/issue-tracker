import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

import { Button } from '../button/Button';

export const NewIssueFooter = ({ titleValue }) => {
  const navigate = useNavigate();

  return (
    <MyNewIssueFooter>
      <Button
        size={'s'}
        color={'ghostBlack'}
        iconType={'xSquare'}
        isIcon
        buttonText={'작성 취소'}
        isLeftPosition
        onClick={() => navigate(-1)}
      />
      <Button
        disabled={titleValue.length < 1}
        size={'m'}
        color={'containerBlue'}
        isIcon={false}
        buttonText={'완료'}
        buttonType={'submit'}
      />
    </MyNewIssueFooter>
  );
};

const MyNewIssueFooter = styled.div`
  width: 1280px;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  gap: 20px;
`;
