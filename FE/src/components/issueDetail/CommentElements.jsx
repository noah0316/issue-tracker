import { useEffect, useRef, useState } from 'react';

import styled from 'styled-components';

import { colors } from '../../styles/color';
import { getTimeElapsed } from '../../utils/timeElapsed';
import { Button } from '../button/Button';
import { LabelTag } from '../LabelTag';
import { Profile } from '../Profile';
import { TextArea } from '../textForm/TextArea';

export const CommentElements = ({
  authorInfo,
  userInfo,
  reply,
  createTime
}) => {
  const [isEditComment, setIsEditComment] = useState(false);
  const [comment, setComment] = useState('');
  const [completeComment, setIsCompleteComment] = useState(null);

  useEffect(() => {
    setComment(reply);
    setIsCompleteComment(reply);
  }, [reply]);

  const handleEditCommnet = () => {
    setIsEditComment(true);
  };

  const handleCompleteEditComment = () => {
    setIsEditComment(false);
    setComment(completeComment);
  };

  const handleCanelEditComment = () => {
    setIsEditComment(false);
    setIsCompleteComment(comment);
  };
  const emojiOptions = {
    size: 'xs',
    color: 'ghostGray',
    iconType: 'smile',
    iconWidth: '13',
    isIcon: true,
    buttonText: '반응',
    isLeftPosition: true
  };

  const editOption = {
    size: 'xs',
    color: 'ghostGray',
    iconType: 'edit',
    iconWidth: '13',
    isIcon: true,
    buttonText: '편집',
    isLeftPosition: true,
    onClick: handleEditCommnet
  };

  const labelTagInfo = {
    tagType: 'labels',
    hasIcon: false,
    text: '작성자',
    backgroundColor: colors.gray100,
    fontColor: colors.gray600,
    borderColor: colors.gray300
  };

  const commentInput = {
    size: 's',
    value: completeComment,
    setValue: setIsCompleteComment,
    isEdit: isEditComment
  };

  const editComment = [
    {
      id: 1,
      size: 's',
      color: 'outlineBlue',
      iconType: 'xSquare',
      isIcon: true,
      isLeftPosition: true,
      iconWidth: '12',
      buttonText: '편집 취소',
      onClick: handleCanelEditComment
    },
    {
      id: 2,
      size: 's',
      color: 'outlineBlue',
      iconType: 'edit',
      isIcon: true,
      isLeftPosition: true,
      iconWidth: '12',
      buttonText: '편집 완료',
      onClick: handleCompleteEditComment
    }
  ];

  return (
    <>
      <MyCommentElements isFocus={isEditComment}>
        <MyCommentHeader>
          <MyProfileInfo>
            <Profile userInfo={userInfo} />
            <span>{userInfo.name}</span>
            <span>{getTimeElapsed(createTime)}</span>
          </MyProfileInfo>
          <EditHeader>
            {authorInfo.id === userInfo.id && (
              <>
                <LabelTag {...labelTagInfo} />
                <Button {...editOption} />
              </>
            )}
            <Button {...emojiOptions} />
          </EditHeader>
        </MyCommentHeader>
        {isEditComment
          ? (
            <>
              <TextArea {...commentInput} />
            </>
          )
          : (
            <MyComments>{comment}</MyComments>
          )}
      </MyCommentElements>
      {isEditComment
        ? (
          <MyEditCommentBtn>
            {editComment.map((edit) => (
              <>
                <Button key={edit.id} {...edit} />
              </>
            ))}
          </MyEditCommentBtn>
        )
        : null}
    </>
  );
};

const MyCommentElements = styled.div`
  width: 100%;
  background: ${colors.gray100};
  align-items: center;
  border-radius: 16px;
  box-shadow: ${({ isFocus }) =>
    isFocus ? `0 0 0 1px ${colors.gray900}` : null};
  > div {
    :first-child {
      background: ${colors.gray100};
      border-radius: 16px 16px 0px 0px;
      border: 1px solid ${colors.gray300};
      align-items: center;
      padding-right: 10px;
    }
  }
`;
const MyComments = styled.div`
  background: ${colors.gray50};
  border-radius: 0px 0px 16px 16px;
  border: 1px solid ${colors.gray300};
  border-top: none;
  height: 60px;
  padding: 2px 20px;
  display: flex;
  align-items: center;
`;

const MyProfileInfo = styled.div`
  display: flex;
  align-items: center;
  gap: 5px;
  padding: 10px;
`;

const EditHeader = styled.div`
  display: flex;
  align-items: center;
  & button {
    display: flex;
    justify-content: center;
  }
`;
const MyCommentHeader = styled.div`
  display: flex;
  justify-content: space-between;
  height: 64px;
`;

const MyEditCommentBtn = styled.div`
  display: flex;
  gap: 10px;
  justify-content: flex-end;
`;
