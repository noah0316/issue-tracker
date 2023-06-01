import React from 'react';

import styled from 'styled-components';

import { DropdownPanelItem } from './DropdownPanelItem';
import { Icon } from '../../assets/Icon';
import { colors } from '../../styles/color';
import { fontSize, fontType } from '../../styles/font';

export const DropdownPanel = ({
  tabId,
  tabName,
  type,
  options,
  isLeft,
  optionalArea,
  selectedOption,
  handleSelectedOption,
  isSelected
}) => {
  const handleOptionClick = ({ currentTarget }) => {
    handleSelectedOption(currentTarget.getAttribute('value'), currentTarget.id);
  };
  const iconType =
    selectedOption === 'none' ? 'checkOnCircle' : 'checkOffCircle';
  const MyDropdownPanel = type === 'sidebar' ? MySidebarPanel : MyDefaultPanel;
  return (
    <MyDropdownPanel isLeft={isLeft}>
      {type === 'sidebar' || (
        <MyDropdownHeader>
          {tabId === 'filter' ? '이슈' : tabName} 필터
        </MyDropdownHeader>
      )}
      <MyDropdownList>
        {type === 'tabs' && (
          <li id={'none'} value={tabId} onMouseUp={handleOptionClick}>
            {optionalArea}
            <Icon iconType={iconType} fill={colors.gray700} />
          </li>
        )}
        {options &&
          options.map((option) => (
            <DropdownPanelItem
              key={option.id}
              tabId={tabId}
              id={option.id}
              {...option}
              isSelected={isSelected?.(option.id, selectedOption, tabId)}
              onMouseUp={handleOptionClick}
            />
          ))}
      </MyDropdownList>
    </MyDropdownPanel>
  );
};

const MyDefaultPanel = styled.div`
  position: absolute;
  top: 45px;
  right: ${({ isLeft }) => isLeft || 0};
  left: ${({ isLeft }) => isLeft && '-2px'};
  width: 240px;
  border: 1px solid ${colors.gray300};
  box-shadow: 0px 0px 8px rgba(20, 20, 43, 0.04);
  border-radius: 16px;
`;

const MyDropdownHeader = styled.h3`
  height: 36px;
  padding: 8px 16px;
  background-color: ${colors.gray100};
  border-radius: 16px 16px 0px 0px;
  border-bottom: 1px solid ${colors.gray300};
  display: flex;
  align-items: center;
  ${fontSize.S};
  ${fontType.REGULAR};
  color: ${colors.gray900};
`;

const MyDropdownList = styled.ul`
  li {
    cursor: pointer;
    height: 45px;
    padding: 8px 16px;
    background-color: ${colors.gray50};
    display: flex;
    align-items: center;
    justify-content: space-between;
    ${fontSize.M};
    ${fontType.LIGHT};
    color: ${colors.gray700};
    gap: 8px;
    ${({ isSelected }) => (isSelected ? fontType.BOLD : fontType.REGULAR)};

    &:last-child {
      border-radius: 0px 0px 16px 16px;
    }

    &:not(:last-child) {
      border-bottom: 1px solid ${colors.gray300};
    }

    &:hover {
      font-weight: 500;
    }

    div {
      width: 150px;
    }
  }
`;

const MySidebarPanel = styled(MyDefaultPanel)`
  width: 210px;
  top: 80px;
  right: -1px;
  z-index: 10;

  li:first-child {
    border-radius: 16px 16px 0px 0px;
  }
`;
