import { useContext, useState, useEffect } from 'react';

import styled from 'styled-components';

import { IssueDetailContext } from '../../pages/IssueDetail';
import { colors } from '../../styles/color';
import { getTimeElapsed } from '../../utils/timeElapsed';
import { Button } from '../button/Button';
import { LabelTag } from '../LabelTag';
import { PageHeader } from '../PageHeader';

export const IssueDetailHeader = () => {
  const { issue } = useContext(IssueDetailContext);
  const [isEdit, setIsEdit] = useState(false);
  const [isClose, setIsClose] = useState(false);
  const [titleData, setTitleData] = useState(null);
  const [completeTitleData, setcompleteTitleData] = useState(null);
  const handleEdit = () => {
    setIsEdit(true);
    setTitleData(`${issue?.title}`);
  };

  useEffect(() => {
    setTitleData(issue?.title);
  }, [issue?.title]);

  const handleNotEdit = () => {
    setIsEdit(false);
  };
  const handleCloseIssue = () => {
    setIsClose(true);
  };

  const handleSubmit = () => {
    setIsEdit(false);
  };

  const pageHeaderInfo = {
    leftChild: issue?.title,
    middleChild: `#${issue?.issueId}`,
    value: isEdit
  };

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

  return (
    <MyIssueDetailHeader>
      <PageHeader
        leftChild={pageHeaderInfo.leftChild}
        middleChild={pageHeaderInfo.middleChild}
        rigthChild={
          <>
            {editBtn.map((edit) => (
              <Button
                key={edit.id}
                size={edit.size}
                color={edit.color}
                iconType={edit.iconType}
                iconWidth={edit.iconWidth}
                isIcon
                buttonText={edit.buttonText}
                isLeftPosition
                onClick={edit.onClick}
              />
            ))}
          </>
        }
        value={isEdit}
        inputValue={titleData}
        inputSetValue={setTitleData}
      />
      <SubHeader>
        <LabelTag {...labelTagBtn} />
        <p>
          이 이슈가 {getTimeElapsed(issue?.createTime)}에 {issue.author?.name}
          님에 의해 열렸습니다. * 코멘트 {issue.commentAuthors?.length}개
        </p>
      </SubHeader>
    </MyIssueDetailHeader>
  );
};

const MyIssueDetailHeader = styled.div`
  width: 1280px;
  margin: 0 auto;
  border-bottom: 1px solid ${colors.gray400};
  padding-bottom: 25px;
`;
const SubHeader = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
  padding-left: 25px;
  & p {
    color: ${colors.gray600};
  }
`;
