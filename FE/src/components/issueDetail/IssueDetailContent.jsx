import { useContext, useEffect, useState } from 'react';

import { useOutletContext } from 'react-router-dom';
import styled from 'styled-components';

import { CommentElements } from './CommentElements';
import { IssueDetailContext } from '../../pages/IssueDetail';
import { fontSize } from '../../styles/font';
import { Button } from '../button/Button';
import { TextArea } from '../textForm/TextArea';

export const IssueDetailContent = () => {
  const { user } = useOutletContext();
  const { issue, comments } = useContext(IssueDetailContext);
  const [comment, setComment] = useState('');
  const [saveComment, setSaveComment] = useState([]);
  const userData = user?.userProfile;
  console.log(userData);

  useEffect(() => {
    setSaveComment(comments);
  }, []);

  const handleSaveComment = () => {
    setSaveComment([
      ...saveComment,
      {
        userId: userData?.id,
        userName: userData?.name,
        userUrl: userData?.avatar_url,
        createTime: Date.now(),
        replyContents: comment
      }
    ]);
    setComment('');
  };
  const commentInput = {
    label: '코멘트를 입력하세요.',
    size: 's',
    value: comment,
    setValue: setComment
  };
  const addComment = {
    size: 's',
    color: 'containerBlue',
    isIcon: true,
    iconWidth: 11,
    buttonText: '코멘트 작성',
    iconType: 'plus',
    disabled: comment.length < 1,
    isLeftPosition: true,
    onClick: handleSaveComment
  };
  return (
    <MyIssueDetailContent>
      {saveComment &&
        saveComment.map(
          ({ userId, userName, userUrl, createTime, replyContents }, index) => (
            <CommentElements
              key={index}
              authorInfo={{
                id: userData?.id,
                name: userData?.name
              }}
              userInfo={{
                id: userId,
                name: userName,
                profileUrl: userUrl
              }}
              createTime={createTime}
              reply={replyContents}
            />
          )
        )}
      {comments &&
        comments.map((comment, index) => (
          <CommentElements
            key={index}
            authorInfo={
              {
                // id: userData?.id,
                // name: userData?.name
              }
            }
            userInfo={{
              id: comment.userId,
              name: comment.userName,
              profileUrl: comment.userUrl
            }}
            createTime={comment.createTime}
            reply={comment.replyContents}
          />
        ))}
      <TextArea {...commentInput} />
      <Button {...addComment} />
    </MyIssueDetailContent>
  );
};

const MyIssueDetailContent = styled.div`
  gap: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  width: 938px;
  > button {
    justify-content: center;
    ${fontSize.S};
  }
  & div {
    :last-child {
      align-items: flex-start;
    }
  }
`;
