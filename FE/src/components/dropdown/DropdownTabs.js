import styled from 'styled-components';

import { Dropdown } from './Dropdown';
import { tabTypes } from './DropdownTypes';

export const DropdownTabs = () => {
  const tabButtons = ['author', 'labels', 'milestone', 'assignees'];
  const dropdownTabInfo = tabButtons.map((tab) => tabTypes[tab]);
  const getParticleType = (tabId) =>
    ['author', 'assignees'].includes(tabId) ? '가' : '이';

  return (
    <MyDropdownTabs>
      {dropdownTabInfo.map(({ tabId, tabName, filterOptions }) => (
        <Dropdown
          key={tabId}
          type={'tabs'}
          tabId={tabId}
          tabName={tabName}
          filterOptions={filterOptions}
          buttonOption={{ size: 's' }}
          optionalArea={
            <>
              {tabName}
              {getParticleType(tabId)} 없는 이슈
            </>
          }
        />
      ))}
    </MyDropdownTabs>
  );
};

const MyDropdownTabs = styled.div`
  display: flex;
  align-items: center;
  gap: 18px;

  > svg,
  > button {
    cursor: pointer;
  }

  button {
    padding: 10px;
  }
`;
