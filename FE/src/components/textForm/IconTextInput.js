import { useState } from 'react';

import styled from 'styled-components';

import { Icon } from '../../assets/Icon';
import { colors } from '../../styles/color';
import { fontSize, fontType } from '../../styles/font';

export const IconTextInput = ({
  inputValue,
  inputSetValue,
  label,
  inputWidth,
  isIcon,
  iconType,
  iconFill,
  iconWidth,
  iconClick
}) => {
  const [isFocus, setIsFocus] = useState(false);
  return (
    <MyInputPageHeader isFocus={isFocus} inputWidth={inputWidth}>
      <label>{label}</label>
      <input
        type="text"
        value={inputValue}
        onChange={(e) => {
          inputSetValue(e.target.value);
        }}
        onFocus={() => setIsFocus(true)}
        onBlur={() => setIsFocus(false)}
      />
      {isIcon && (
        <Icon
          iconType={iconType}
          fill={iconFill}
          width={iconWidth}
          onClick={iconClick}
        />
      )}
    </MyInputPageHeader>
  );
};

const MyInputPageHeader = styled.form`
  display: grid;
  grid-template-columns: 70px auto 1fr;
  align-items: center;
  border-radius: 11px;
  width: ${({ inputWidth }) => inputWidth || `900px`};
  height: 40px;
  gap: 10px;
  padding: 0px 24px;
  ${fontType.REGULAR}
  background: ${({ isFocus }) =>
    isFocus ? `${colors.gray50}` : `${colors.gray200}`};
  box-shadow: ${({ isFocus }) =>
    isFocus ? `0 0 0 1px ${colors.gray900}` : null};
  label {
    width: max-content;
    height: 20px;
    ${fontSize.S}
  }
  & input {
    height: 100%;
    background: transparent;
    border: none;
    outline: none;
    font-size: 15px;
    ${fontType.REGULAR}
    color: ${colors.gray900};
    transition: 200ms cubic-bezier(0, 0, 0.2, 1) 0ms;
  }
`;
