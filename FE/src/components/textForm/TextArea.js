import React, { useEffect, useState } from 'react';

import styled, { css } from 'styled-components';

import { Icon } from '../../assets/Icon';
import { colors } from '../../styles/color';
import { fontSize, fontType } from '../../styles/font';
import { Button } from '../button/Button';

export const TextArea = React.memo(
  ({ label, size, value, setValue, inputRef, isEdit }) => {
    const areaSize = areaSizes[size];
    const fileSize = fileSizes[size];

    const [isTextAreaFocus, setIsTextAreaFocus] = useState(false);
    const [isCount, setIsCount] = useState(true);
    const fileInput = React.useRef(null);

    const handleButtonClick = (e) => {
      fileInput.current.click();
    };

    const handleChange = (e) => {
      setValue(
        `${value}\n![${e.target.files[0].name}](https://github.com/codesquad-members-2023/issue-tracker/assets/104904719/${e.target.files[0].name})`
      );
    };

    useEffect(() => {
      setIsCount(true);
      let timerId;
      if (value.length > 0) {
        timerId = setTimeout(() => setIsCount(false), 2000);
      }
      return () => clearTimeout(timerId);
    }, [value]);

    return (
      <MyTextArea
        isFocus={isTextAreaFocus}
        isEdit={isEdit}
        areaSize={areaSize}
        value={value}
      >
        <textarea
          name="comment"
          onChange={({ target }) => {
            setValue(target.value);
          }}
          onFocus={() => setIsTextAreaFocus(true)}
          onBlur={() => setIsTextAreaFocus(false)}
          ref={inputRef}
          value={value}
        />
        {isEdit || <label className={value && 'filled'}>{label}</label>}
        <MyTextCount isFocus={isTextAreaFocus} isEidt={isEdit}>
          {isCount && <span>{`띄어쓰기 포함 ${value?.length}자`}</span>}
          <Icon iconType={'grip'} />
        </MyTextCount>
        <MyFileArea
          isFocus={isTextAreaFocus}
          isEdit={isEdit}
          fileSize={fileSize}
        >
          <Button
            size={'m'}
            color={'ghostBlack'}
            iconType={'paperclip'}
            isIcon
            buttonText={`파일 첨부하기`}
            isLeftPosition
            onClick={handleButtonClick}
          />
          <input
            type="file"
            ref={fileInput}
            onChange={handleChange}
            style={{ display: 'none' }}
          />
        </MyFileArea>
      </MyTextArea>
    );
  }
);

const areaSizes = {
  l: css`
    width: 912px;
    height: 384px;
  `,
  s: css`
    width: 938px;
    height: 188px;
  `
};

const fileSizes = {
  l: css`
    width: 942px;
    height: 52px;
  `,
  s: css`
    width: 958px;
    height: 52px;
  `
};

const MyTextArea = styled.form`
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 938px;
  border-radius: ${({ isEdit }) => (isEdit ? `0 0 16px 16px` : '11px')};
  background: ${({ isFocus, isEdit }) =>
    isEdit || isFocus ? `${colors.gray50}` : `${colors.gray200}`};
  box-shadow: ${({ isFocus, isEdit }) =>
    !isEdit && isFocus ? `0 0 0 1px ${colors.blue}` : null};
  &: focus-within label {
    transform: translate(0, 12px) scale(0.8);
  }
  &: filled {
    transform: translate(0, 12px) scale(0.8);
  }
  & label {
    position: absolute;
    ${({ value }) =>
    value?.length > 0
      ? 'transform: translate(0, 12px) scale(0.8);'
      : 'transform: translate(0, 23px) scale(1);'}
    pointer-events: none;
    transform-origin: top left;
    transition: 200ms cubic-bezier(0, 0, 0.2, 1) 2ms;
    color: ${colors.gray600};
    font-size: 16px;
    line-height: 1;
    left: 16px;
    top: 3px;
  }
  & textarea {
    box-sizing: border-box;
    ${({ areaSize }) => areaSize};
    width: 100%;
    padding: 30px;
    transition: 200ms cubic-bezier(0, 0, 0.2, 1) 0ms;
    resize: none;
    ${fontSize.M};
    ${fontType.REGULAR};
    background: transparent;
    border: none;
    outline: none;
    border-radius: 11px 11px 0px 0px;
  }
`;

const MyFileArea = styled.div`
  ${({ fileSize }) => fileSize};
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  border-top: ${({ isFocus, isEdit }) =>
    isFocus && !isEdit
      ? `1px dashed ${colors.blue}`
      : `1px dashed ${colors.gray300}`};
  > div {
    padding: 0px 20px 0px 0px;
  }
  > button {
    padding: 0px 0px 0px 20px;
    justify-content: flex-start;
  }
`;

const MyTextCount = styled.div`
  display: flex;
  width: 100%;
  align-items: center;
  justify-content: flex-end;
  color: ${colors.gray600};
  ${fontSize.S};
  ${fontType.REGULAR};
  background: transparent;
`;
