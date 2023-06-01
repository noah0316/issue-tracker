import React, { useEffect, useState } from 'react';

import { useParams, useOutletContext } from 'react-router-dom';
import styled from 'styled-components';

import { IssueDetailContent } from '../components/issueDetail/IssueDetailContent';
import { IssueDetailHeader } from '../components/issueDetail/IssueDetailHeader';
import { IssueDetailSidebar } from '../components/issueDetail/IssueDetailSidebar';
import { fetchAll } from '../utils/fetch';

export const IssueDetailContext = React.createContext();

export const IssueDetail = () => {
  const [issue, setIssue] = useState([]);
  const [comments, setComments] = useState([]);
  const { id } = useParams();
  const { user } = useOutletContext();
  const initData = async () => {
    try {
      const [issueInfo, commentInfo] = await fetchAll(
        `${process.env.REACT_APP_BASE_URI}/issues/${id}`,
        `${process.env.REACT_APP_BASE_URI}/issues/${id}/comments?issueId=${id}`
      );
      setIssue(issueInfo);
      setComments(commentInfo);
    } catch (err) {
      // console.error(err);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const url = `${process.env.REACT_APP_BASE_URI}/issues/${user.userProfile.id}/${id}/comments`;
    const data = {
      issueId: id,
      comment: comments,
      fileAttachmentUrl: null,
      description: null,
      userId: user.userProfile.id,
      userName: user.userProfile.name,
      userProfileUrl: user?.userProfile.id
    };
    await fetchPost({ url, data });
  };

  // TODO : 코맨트 편집기능
  const handleEdit = async (e, element) => {
    e.preventDefault();
    const url = `${process.env.REACT_APP_BASE_URI}/issues/${id}/${element}`;
    const data = {
      element
    };
    await fetchPatch({ url, data });
  };

  useEffect(() => {
    initData();
  }, [id]);

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
