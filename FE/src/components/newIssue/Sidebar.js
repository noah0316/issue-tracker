import React, { useCallback } from 'react';

import styled from 'styled-components';

import { colors } from '../../styles/color';
import { fontSize } from '../../styles/font';
import { Dropdown } from '../dropdown/Dropdown';
import { tabTypes } from '../dropdown/DropdownTypes';
import { LabelTag } from '../LabelTag';
import { Profile } from '../Profile';

export const SideBar = React.memo(
  ({
    assigneeSetValue,
    labelSetValue,
    milestoneSetValue,
    selectedSideBarMenu
  }) => {
    const sideBarTabs = ['assignees', 'labels', 'milestones'];
    const sideBarInfo = sideBarTabs.map((tab) => tabTypes[tab]);
    const setValue = {
      assignees: assigneeSetValue,
      labels: labelSetValue,
      milestones: milestoneSetValue
    };
    const handleSelectedSideBarMenu = useCallback(
      (selectedTab, selectedSideBarMenu) => {
        if (!selectedSideBarMenu) return;
        const {
          name,
          labelName,
          title,
          option,
          profileUrl,
          backgroundColor,
          fontColor
        } = selectedSideBarMenu;
        const sideBarMenu = {
          assignees: () => (
            <>
              {profileUrl && <Profile userInfo={{ option, profileUrl }} />}
              <div>{name ?? option}</div>
            </>
          ),
          labels: () => (
            <LabelTag
              tagType={'labels'}
              text={labelName ?? option}
              backgroundColor={backgroundColor}
              fontColor={fontColor}
            />
          ),
          milestones: () => <>{title ?? option}</>
        };
        const SelectedMenuItem = sideBarMenu[selectedTab];
        return <SelectedMenuItem />;
      },
      []
    );

    return (
      <MySidebar>
        {sideBarInfo.map(({ tabId, tabName, filterOptions }) => (
          <Dropdown
            key={tabId}
            type={'sidebar'}
            tabId={tabId}
            tabName={tabName}
            filterOptions={filterOptions}
            buttonOption={{
              disabled: false,
              size: 'm'
            }}
            setValue={setValue[tabId]}
            selectedSideBarMenu={selectedSideBarMenu}
            handleSelectedSideBarMenu={handleSelectedSideBarMenu}
          />
        ))}
      </MySidebar>
    );
  }
);

const MySidebar = styled.div`
  border: 1px solid ${colors.gray300};
  border-radius: 16px;
  background: ${colors.gray50};
  height: max-content;
  > div:not(:last-child) {
    border-bottom: 1px solid ${colors.gray300};
  }
  & button {
    height: 96px;
    justify-content: space-between;
    padding: 0 20px;
    ${fontSize.M}
  }
`;
