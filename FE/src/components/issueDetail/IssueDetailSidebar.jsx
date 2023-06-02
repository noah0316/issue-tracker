import { useContext, useEffect, useState } from 'react';

import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

import { IssueDetailContext } from '../../pages/IssueDetail';
import { Button } from '../button/Button';
import { SideBar } from '../newIssue/Sidebar';

export const IssueDetailSidebar = () => {
  const { issue } = useContext(IssueDetailContext);
  const [assignees, setAssignees] = useState(null);
  const [labels, setlabels] = useState(null);
  const [milestone, setMilestone] = useState(null);
  const [selectedSideBarMenu, setselectedSideBarMenu] = useState({});
  const navigate = useNavigate();
  // TODO: 이슈 삭제하기
  const btnInfo = {
    size: 'xs',
    color: 'ghostRed',
    iconType: 'trash',
    isIcon: true,
    buttonText: '이슈 삭제',
    isLeftPosition: true,
    onClick: () => navigate('/issues')
  };

  useEffect(() => {
    setselectedSideBarMenu({
      assignees: issue.assignees?.[0],
      labels: issue.labels?.[0],
      milestones: issue.milestone
    });
  }, [issue]);

  useEffect(() => {
    setAssignees(assignees);
    setlabels(labels);
    setMilestone(milestone);
  }, []);

  return (
    <SidebarSection>
      <SideBar
        assigneeSetValue={setAssignees}
        labelSetValue={setlabels}
        milestoneSetValue={setMilestone}
        selectedSideBarMenu={selectedSideBarMenu}
      />
      <Button {...btnInfo} />
    </SidebarSection>
  );
};

const SidebarSection = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: 20px;
  > button {
    margin-right: 25px;
  }
`;
