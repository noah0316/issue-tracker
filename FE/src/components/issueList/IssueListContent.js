import React, { useContext } from 'react';

import styled from 'styled-components';

import { IssueItem } from './IssueItem';
import { IssueListContext } from '../../pages/IssueList';
import { colors } from '../../styles/color';
import { fontSize } from '../../styles/font';

export const IssueListContent = () => {
  const { issuesInfo } = useContext(IssueListContext);

  return (
    <MyIssueListContent>
      {issuesInfo
        ? (
          issuesInfo.map((issues) => <IssueItem key={issues.id} {...issues} />)
        )
        : (
          <MyEmptyContent>등록된 이슈가 없습니다</MyEmptyContent>
        )}
    </MyIssueListContent>
  );
};

const MyIssueListContent = styled.div`
  > div:not(:last-child) {
    border-bottom: 1px solid ${colors.gray300};
  }

  > div:last-child {
    border-radius: 0px 0px 16px 16px;
  }
`;

const MyEmptyContent = styled.div`
  ${fontSize.M}
  height: 150px;
  line-height: 150px;
  text-align: center;
  background-color: ${colors.gray50};
`;
