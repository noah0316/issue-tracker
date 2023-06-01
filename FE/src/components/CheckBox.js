import styled from 'styled-components';

import { Icon } from '../assets/Icon';
import { colors } from '../styles/color';

const checkType = {
  initial: {
    iconType: 'checkBoxInitial',
    fill: `${colors.gray300}`
  },
  active: {
    iconType: 'checkBoxActive',
    fill: `${colors.blue}`
  },
  disable: {
    iconType: 'checkBoxDisable',
    fill: `${colors.blue}`
  }
};

export const CheckBox = ({ id, checked, onClick }) => {
  const type = checked
    ? id === 'select-all'
      ? 'disable'
      : 'active'
    : 'initial';

  return (
    <MyCheckBox id={id} type={'button'} onClick={onClick}>
      <Icon {...checkType[type]} />
    </MyCheckBox>
  );
};

const MyCheckBox = styled.div`
  cursor: pointer;
`;
