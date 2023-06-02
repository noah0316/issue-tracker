import { useEffect, useState } from 'react';

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
  createdTime
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
  const editComment = {
    cancle: {
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
    complete: {
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
  };

  return (
    <>
      <MyCommentElements isFocus={isEditComment}>
        <MyCommentHeader>
          <MyProfileInfo>
            <Profile userInfo={userInfo} />
            <span>{userInfo.name}</span>
            <span>{getTimeElapsed(createdTime)}</span>
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
      {isEditComment && (
        <MyEditCommentBtn>
          <Button {...editComment.cancle} />
          <Button {...editComment.complete} />
        </MyEditCommentBtn>
      )}
    </>
  );
};

const MyCommentElements = styled.div`
  box-shadow: ${({ isFocus }) =>
    isFocus ? `0 0 0 1px ${colors.gray700}` : null};
  width: 100%;
  align-items: center;
  border-radius: 16px;
  border: ${({ isFocus }) => (isFocus ? null : `1px solid ${colors.gray300}`)};
  > div {
    &:first-child {
      border-radius: 16px 16px 0px 0px;
      padding-right: 10px;
      align-items: center;
      border-bottom: 1px solid ${colors.gray300};
    }
  }
`;

const MyComments = styled.div`
  height: max-heigth;
  border-radius: 0px 0px 16px 16px;
  padding: 15px 20px;
  display: flex;
  align-items: center;
  background: ${colors.gray50};
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
