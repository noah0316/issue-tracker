import { useEffect, useState } from 'react';

import styled from 'styled-components';

import { colors } from '../../styles/color';
import { fontSize, fontType } from '../../styles/font';
import { Button } from '../button/Button';
import { LabelTag } from '../LabelTag';
import { IconTextInput } from '../textForm/IconTextInput';

export const NewLabelSection = ({
  id,
  isEditLabel,
  setIsEditLabel,
  labelTitle,
  labelDescription,
  labelBackgroundColor,
  labelFontColor,
  postLabel,
  putLabel
}) => {
  const [labelName, setLabelName] = useState('레이블');
  const [explain, setExplain] = useState(null);
  const [bgColor, setBgColor] = useState(colors.gray300);
  const [isDark, setIsDark] = useState(false);
  console.log('POST1', postLabel, 'PUT1', putLabel);
  useEffect(() => {
    if (isEditLabel) {
      setLabelName(labelTitle);
      setExplain(labelDescription);
      setBgColor(labelBackgroundColor);
      setIsDark(labelFontColor);
    }
  }, []);

  const handleChangeColor = () => {
    setBgColor(randomColor());
  };

  const randomColor = () => {
    return '#' + Math.random().toString(16).substring(2, 8);
  };
  console.log(isEditLabel);
  const labelInfo = {
    labelName: {
      inputValue: labelName,
      inputSetValue: setLabelName,
      label: '레이블 이름'
    },
    explainLabel: {
      inputValue: explain,
      inputSetValue: setExplain,
      label: '설명(선택)'
    },
    labelColor: {
      inputValue: bgColor,
      inputSetValue: setBgColor,
      inputWidth: `max-content`,
      label: '배경 색상',
      isIcon: true,
      iconType: 'refreshCcw',
      iconWidth: 20
    },
    colorBtn: {
      size: 's',
      color: 'ghostGray',
      iconType: 'chevronDown',
      iconWidth: '16',
      isIcon: true,
      buttonText: isDark ? 'dark text' : 'light text',
      isLeftPosition: false
    },

    cancleBtn: {
      size: 's',
      color: 'outlineBlue',
      iconType: 'xSquare',
      iconWidth: '10',
      isIcon: true,
      buttonText: '취소',
      isLeftPosition: true,
      onClick: () => setIsEditLabel(false)
    },
    completeBtn: {
      size: 's',
      color: 'containerBlue',
      iconType: isEditLabel ? 'edit' : 'plus',
      iconWidth: '16',
      isIcon: true,
      buttonText: isEditLabel ? '편집 완료' : '완료',
      isLeftPosition: true,
      onClick: isEditLabel
        ? () =>
          putLabel({
            id,
            labelName,
            explain,
            bgColor,
            isDark,
            setIsEditLabel
          })
        : () =>
          postLabel({
            labelName,
            explain,
            bgColor,
            isDark,
            setLabelName,
            setExplain,
            setBgColor,
            setIsEditLabel
          })
    },
    labelTag: {
      tagType: 'labels',
      text: labelName,
      hasIcon: false,
      backgroundColor: bgColor,
      fontColor: isDark ? colors.gray900 : colors.gray50
    }
  };

  return (
    <MyNewLabelSection>
      <p>{isEditLabel ? '레이블 편집' : '새로운 레이블 추가'}</p>
      <MyLabel>
        <MyViewLabel>
          <LabelTag {...labelInfo.labelTag} />
        </MyViewLabel>
        <MyNewLabel>
          <IconTextInput {...labelInfo.labelName} />
          <IconTextInput {...labelInfo.explainLabel} />
          <MyNewLabelColor>
            <IconTextInput
              {...labelInfo.labelColor}
              iconClick={handleChangeColor}
            />
            <Button
              {...labelInfo.colorBtn}
              onClick={() => (isDark ? setIsDark(false) : setIsDark(true))}
            />
          </MyNewLabelColor>
        </MyNewLabel>
      </MyLabel>
      <MyNewLabelContent>
        {isEditLabel && <Button {...labelInfo.cancleBtn} />}
        <Button {...labelInfo.completeBtn} />
      </MyNewLabelContent>
    </MyNewLabelSection>
  );
};

const MyNewLabelSection = styled.div`
  // width: 1280px;
  width: 100%;
  height: 337px;
  background: ${colors.gray50};
  border-radius: 16px;
  border: 1px solid ${colors.gray300};
  & p {
    ${fontSize.L};
    ${fontType.BOLD};
    ${colors.gray900};
    margin-top: 30px;
    margin-left: 22px;
    margin-bottom: 30px;
  }
`;

const MyViewLabel = styled.div`
  display: flex;
  width: 288px;
  height: 153px;
  border: 1px solid ${colors.gray300};
  border-radius: 11px;
  align-items: center;
  justify-content: center;
`;

const MyNewLabel = styled.form`
  display: flex;
  flex-direction: column;
  gap: 17px;
`;
const MyNewLabelColor = styled.div`
  display: flex;
  label {
    width: max-content;
  }
`;
const MyLabel = styled.div`
  display: flex;
  gap: 10px;
  align-items: center;
  justify-content: center;
`;

const MyNewLabelContent = styled.div`
  display: flex;
  justify-content: flex-end;
  margin-top: 20px;
  margin-right: 17px;
  gap: 10px;
`;
