import React, { useRef, useState } from 'react';

import { useOutletContext } from 'react-router-dom';
import styled from 'styled-components';

import { NewIssueFooter } from '../components/newIssue/NewIssueFooter';
import { SideBar } from '../components/newIssue/Sidebar';
import { PageHeader } from '../components/PageHeader';
import { Profile } from '../components/Profile';
import { TextArea } from '../components/textForm/TextArea';
import { TextInput } from '../components/textForm/TextInput';

export const NewIssue = () => {
  const titleInputRef = useRef();
  const commentInputRef = useRef();
  const { user } = useOutletContext();
  const [title, setTitle] = useState('');
  const [comment, setComment] = useState('');
  const [assignee, setAssignee] = useState(null);
  const [label, setlabel] = useState(null);
  const [milestone, setMilestone] = useState(null);

  const [newIssue, setNewIssue] = useState({});

  const handleSubmit = (e) => {
    e.preventDefault();
    setNewIssue(
      JSON.stringify({
        title,
        comment,
        assignees: assignee,
        labels: label,
        milestone
      })
    );
  };

  return (
    <MyNewIssuePage>
      <PageHeader leftChild={'새로운 이슈 작성'} />
      <MyNewIssueForm onSubmit={handleSubmit}>
        <MyNewIssueContainer>
          <Profile
            isLarge
            userInfo={{
              name: user?.userProfile.name,
              profileUrl: user?.userProfile.avatar_url
            }}
          />
          <MyNewIssueContent>
            <TextInput
              label={'제목'}
              height={'70px'}
              value={title}
              setValue={setTitle}
              inputRef={titleInputRef}
            />
            <TextArea
              label={'코멘트를 입력하세요'}
              size={'l'}
              value={comment}
              setValue={setComment}
              inputRef={commentInputRef}
            />
          </MyNewIssueContent>
          <SideBar
            assigneeSetValue={setAssignee}
            labelSetValue={setlabel}
            milestoneSetValue={setMilestone}
          />
        </MyNewIssueContainer>
        <NewIssueFooter titleValue={title} commentValue={comment} />
      </MyNewIssueForm>
    </MyNewIssuePage>
  );
};

const MyNewIssuePage = styled.div`
  width: 1280px;
  margin: 0px auto;
`;

const MyNewIssueContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 0px 0px 20px 0px;
`;

const MyNewIssueContent = styled.div`
  gap: 10px;
  display: flex;
  flex-direction: column;
`;

const MyNewIssueForm = styled.form``;
