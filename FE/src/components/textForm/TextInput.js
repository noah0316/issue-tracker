import React, { useState } from 'react';

import styled from 'styled-components';

import { colors } from '../../styles/color';
import { fontSize, fontType } from '../../styles/font';

export const TextInput = React.memo(
  ({ label, height, value, setValue, focusStyle, inputType, inputRef }) => {
    const [isTextInputFocus, setIsTextInputFocus] = useState(false);

    return (
      <MyTextInput
        height={height}
        value={value}
        focusStyle={focusStyle}
        isFocus={isTextInputFocus}
        onFocus={() => setIsTextInputFocus(true)}
        onBlur={() => setIsTextInputFocus(false)}
      >
        <input
          name="title"
          type={inputType}
          onChange={({ target }) => {
            setValue(target.value);
          }}
          ref={inputRef}
          value={value}
        />
        <label className={value && 'filled'}>{label}</label>
      </MyTextInput>
    );
  }
);

const MyTextInput = styled.div`
  height: ${({ height }) => height || '40px'};
  width: 100%;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-radius: 11px;
  background: ${({ isFocus }) => (isFocus ? `${colors.gray50}` : null)};
  box-shadow: ${({ isFocus, focusStyle }) =>
    isFocus ? `0 0 0 1px ${colors.blue}` : `0 0 0 1px ${focusStyle}`};
  &: focus-within label {
    transform: translate(0, 12px) scale(0.8);
  }

  &: filled {
    transform: translate(0, 12px) scale(0.8);
  }

  & label {
    position: absolute;
    ${({ value }) =>
    value.length > 0
      ? 'transform: translate(0, 12px) scale(0.8);'
      : 'transform: translate(0, 23px) scale(1);'}
    pointer-events: none;
    transform-origin: top left;
    transition: 200ms cubic-bezier(0, 0, 0.2, 1) 0ms;
    color: ${colors.gray600};
    font-size: 16px;
    line-height: 1;
    left: 16px;
  }

  & input {
    box-sizing: border-box;
    width: 100%;
    height: 100%;
    border-radius: 11px;
    border: none;
    outline: none;
    box-shadow: none;
    padding: 10px 30px;
    background: ${({ isFocus }) =>
    isFocus ? `${colors.gray50}` : `${colors.gray200}`};
    transition: 200ms cubic-bezier(0, 0, 0.2, 1) 0ms;
    ${fontSize.M};
    ${fontType.REGULAR};
  }
`;
