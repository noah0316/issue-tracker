import React, { useEffect, useState } from 'react';

import { useParams } from 'react-router-dom';
import styled from 'styled-components';

import { IssueDetailContent } from '../components/issueDetail/IssueDetailContent';
import { IssueDetailHeader } from '../components/issueDetail/IssueDetailHeader';
import { IssueDetailSidebar } from '../components/issueDetail/IssueDetailSidebar';
import { fetchAll } from '../utils/fetch';

export const IssueDetailContext = React.createContext();

export const IssueDetail = () => {
  const [issue, setIssue] = useState([]);
  const [comments, setComments] = useState([]);
  const { issueId } = useParams();

  const initData = async () => {
    try {
      const [issue, comment] = await fetchAll(
        `${process.env.REACT_APP_BASE_URI}/issues/${issueId}`,
        `${process.env.REACT_APP_BASE_URI}/issues/issues/${issueId}/comments`
      );
      setIssue(issue);
      setComments(comment.comments);
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    initData();
  }, [issueId]);

  return (
    <IssueDetailContext.Provider value={{ issue, comments }}>
      <IssueDetailHeader />
      <MysIssueDetailContainer>
        <IssueDetailContent />
        <IssueDetailSidebar />
      </MysIssueDetailContainer>
    </IssueDetailContext.Provider>
  );
};

const MysIssueDetailContainer = styled.div`
  display: flex;
  width: 1280px;
  margin: 0px auto;
  padding: 30px 0;
  justify-content: space-evenly;
  gap: 35px;
`;
