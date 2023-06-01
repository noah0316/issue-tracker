import { useContext } from 'react';

import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

import { CheckboxStateContext } from './IssueListContainer';
import { Icon } from '../../assets/Icon';
import { colors } from '../../styles/color';
import { fontSize, fontType } from '../../styles/font';
import { getTimeElapsed } from '../../utils/timeElapsed';
import { CheckBox } from '../CheckBox';
import { LabelTag } from '../LabelTag';
import { Profile } from '../Profile';

export const IssueItem = ({
  issueId,
  title,
  author,
  labels,
  milestone,
  assignees,
  createTime,
  isOpen
}) => {
  const navigate = useNavigate();
  const { checkState, checkDispatch } = useContext(CheckboxStateContext);
  const { checkedIssues } = checkState;
  const iconType = isOpen ? 'alertCircle' : 'archive';
  const handleCheckBoxClick = ({ currentTarget }) => {
    if (checkedIssues.some((id) => id === Number(currentTarget.id))) {
      checkDispatch({ type: 'UNCHECK', payload: issueId });
    } else {
      checkDispatch({ type: 'CHECK', payload: issueId });
    }
  };

  return (
    <MyIssueItem>
      <MyIssueBox>
        <CheckBox
          id={issueId}
          onChange={handleCheckBoxClick}
          checked={checkedIssues.includes(issueId)}
        />
        <MyIssue>
          <MyIssueTitle>
            <Icon iconType={iconType} fill={colors.blue} />
            <span onClick={() => navigate(`/issueDetail/${issueId}`)}>
              {title}
            </span>
            {!!labels.length &&
              labels.map((label) => (
                <LabelTag
                  key={label.id}
                  tagType={'labels'}
                  hasIcon={false}
                  text={label.name}
                  backgroundColor={label.backgroundColor}
                  fontColor={label.fontColor}
                />
              ))}
          </MyIssueTitle>
          <MyIssueDiscription>
            <p>#{issueId}</p>
            <p>
              이 이슈가 {getTimeElapsed(createTime)}, {author?.name}님에 의해
              작성되었습니다
            </p>
            {milestone && (
              <>
                <Icon iconType={'milestone'} fill={colors.gray600} />
                <p>{milestone.name}</p>
              </>
            )}
          </MyIssueDiscription>
        </MyIssue>
      </MyIssueBox>
      {assignees && (
        <MyIssueAssignee>
          {assignees.map((assignee) => (
            <Profile key={assignee.id} userInfo={assignee} />
          ))}
        </MyIssueAssignee>
      )}
    </MyIssueItem>
  );
};

const MyIssueItem = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100px;
  padding: 0 25px;
  background-color: ${colors.gray50};

  &: hover {
    background-color: ${colors.gray100};
  }
`;

const MyIssueBox = styled.div`
  display: flex;
  align-items: center;
  gap: 18px;

  > svg {
    cursor: pointer;
  }
`;

const MyIssue = styled.div`
  display: flex;
  flex-direction: column;
  gap: 7px;
`;

const MyIssueTitle = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;

  > span {
    ${fontSize.L};
    cursor: pointer;
  }
`;

const MyIssueDiscription = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
  height: 30px;

  > p {
    height: 30px;
    vertical-align: center;
    color: ${colors.gray600};
    ${fontSize.M};
    ${fontType.LIGHT};
  }
`;

const MyIssueAssignee = styled.div`
  padding-right: 15px;

  img {
    cursor: pointer;
  }

  & > img:first-of-type:not(:last-child) {
    margin-right: -10px;
  }

  &:hover {
    > img:first-of-type:not(:last-child) {
      transform: translateX(-10px);
      transition: all 0.1s ease-in-out;
    }
  }
`;
