import { useContext, useState, useEffect } from 'react';

import styled from 'styled-components';

import { IssueDetailContext } from '../../pages/IssueDetail';
import { colors } from '../../styles/color';
import { getTimeElapsed } from '../../utils/timeElapsed';
import { Button } from '../button/Button';
import { LabelTag } from '../LabelTag';
import { PageHeader } from '../PageHeader';

export const IssueDetailHeader = () => {
  const { issue, comments } = useContext(IssueDetailContext);
  const [isEdit, setIsEdit] = useState(false);
  const [isClose, setIsClose] = useState(false);
  const [titleData, setTitleData] = useState(null);
  const [completeTitleData, setCompleteTitleData] = useState(null);

  const handleNotEdit = () => {
    setIsEdit(false);
    setTitleData(completeTitleData);
  };
  const handleCloseIssue = () => {
    setIsClose(true);
  };
  const handleSubmit = () => {
    setIsEdit(false);
    setCompleteTitleData(titleData);
  };
  const handleEdit = () => {
    setIsEdit(true);
    setTitleData(issue?.title);
  };

  useEffect(() => {
    setTitleData(issue?.title);
    setCompleteTitleData(issue?.title);
  }, [issue?.title]);

  const editBtn = [
    {
      id: 1,
      size: 's',
      color: 'outlineBlue',
      iconType: isEdit ? 'xSquare' : 'edit',
      iconWidth: '12',
      buttonText: isEdit ? '편집 취소' : '제목 편집',
      onClick: isEdit ? handleNotEdit : handleEdit
    },
    {
      id: 2,
      size: 's',
      color: isEdit ? 'containerBlue' : 'outlineBlue',
      iconType: isEdit ? 'edit' : 'archive',
      iconWidth: '12',
      buttonText: isEdit ? '편집 완료' : '이슈 닫기',
      onClick: isEdit ? handleSubmit : handleCloseIssue
    }
  ];
  const labelTagBtn = {
    tagType: isClose ? 'close' : 'open',
    hasIcon: true,
    icon: 'archive',
    text: isClose ? '닫힌 이슈' : '열린 이슈'
  };

  const pageHeaderInfo = {
    leftChild: titleData,
    middleChild: `#${issue?.id}`,
    value: isEdit
  };

  return (
    <MyIssueDetailHeader>
      <PageHeader
        leftChild={pageHeaderInfo.leftChild}
        middleChild={pageHeaderInfo.middleChild}
        rigthChild={
          <>
            {editBtn.map((edit) => (
              <Button key={edit.id} {...edit} />
            ))}
          </>
        }
        value={isEdit}
        inputValue={titleData}
        inputSetValue={setTitleData}
      />
      <MySubHeader>
        <LabelTag {...labelTagBtn} />
        <p>
          이 이슈가 {getTimeElapsed(issue.createTime)}에 {issue.author?.name}
          님에 의해 열렸습니다. * 코멘트 {comments.length}개
        </p>
      </MySubHeader>
    </MyIssueDetailHeader>
  );
};

const MyIssueDetailHeader = styled.div`
  width: 1280px;
  margin: 0 auto;
  border-bottom: 1px solid ${colors.gray400};
  padding-bottom: 25px;
`;
const MySubHeader = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
  padding-left: 25px;
  & p {
    color: ${colors.gray600};
  }
`;
