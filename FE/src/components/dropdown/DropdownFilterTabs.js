import styled from 'styled-components';

import { Dropdown } from './Dropdown';
import { tabTypes } from './DropdownTypes';

export const DropdownFilterTabs = ({ onFilterIssues }) => {
  const tabButtons = ['author', 'labels', 'milestones', 'assignees'];
  const dropdownTabInfo = tabButtons.map((tab) => tabTypes[tab]);
  const getParticleType = (tabId) =>
    ['author', 'assignees'].includes(tabId) ? '가' : '이';
  const isSelected = (option, selectedOption) =>
    String(option) === selectedOption;
  return (
    <MyDropdownFilterTabs>
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
          onFilterIssues={onFilterIssues}
          isSelected={isSelected}
        />
      ))}
    </MyDropdownFilterTabs>
  );
};

const MyDropdownFilterTabs = styled.div`
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
