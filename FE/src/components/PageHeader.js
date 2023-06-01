import styled from 'styled-components';

import { MyHeader } from './Header';
import { IconTextInput } from './textForm/IconTextInput';
import { colors } from '../styles/color';

export const PageHeader = ({
  leftChild,
  middleChild,
  rigthChild,
  value,
  inputValue,
  inputSetValue
}) => {
  return (
    <MyPageHeader>
      <MyLeftPageHeader>
        {middleChild
          ? (
            <>
              {value
                ? (
                  <>
                    {leftChild && (
                      <IconTextInput
                        inputValue={inputValue}
                        inputSetValue={inputSetValue}
                        label={'제목'}
                      />
                    )}
                  </>
                )
                : (
                  <>
                    {leftChild && <div>{leftChild}</div>}
                    {middleChild && <div>{middleChild}</div>}
                  </>
                )}
            </>
          )
          : (
            <>{leftChild && <div>{leftChild}</div>}</>
          )}
      </MyLeftPageHeader>
      {rigthChild && <MyrightPageHeader>{rigthChild}</MyrightPageHeader>}
    </MyPageHeader>
  );
};

const MyPageHeader = styled(MyHeader)``;
const MyLeftPageHeader = styled.div`
  display: flex;
  gap: 10px;
  >div: last-child {
    color: ${colors.gray600};
  }
`;
const MyrightPageHeader = styled.div`
  display: flex;
  gap: 10px;
`;
