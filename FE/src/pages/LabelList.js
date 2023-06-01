import { useState, useEffect } from 'react';

import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';

import { Button } from '../components/button/Button';
import { NewLabelSection } from '../components/labelList/NewlabelSection';
import { LabelTag } from '../components/LabelTag';
import { colors } from '../styles/color';
import { fontSize, fontType } from '../styles/font';
import { fetchAll } from '../utils/fetch';

export const LabelList = () => {
  const navigate = useNavigate();
  const [labels, setLabels] = useState([]);
  const [countInfo, setCountInfo] = useState([]);
  const [isNewLabel, setIsNewLabel] = useState(false);
  const initData = async () => {
    try {
      const [labelsInfo, countInfo] = await fetchAll('/labels', '/issues');
      setLabels(labelsInfo);
      setCountInfo(countInfo.countInfo);
    } catch (err) {
      // console.log(err);
    }
  };

  useEffect(() => {
    initData();
  }, []);

  const filterTabOptions = {
    labels: {
      size: 's',
      color: 'ghostGray',
      iconType: 'label',
      iconWidth: 16,
      isIcon: true,
      isLeftPosition: true
    },
    milestone: {
      size: 's',
      color: 'ghostGray',
      iconType: 'milestone',
      iconWidth: 16,
      isIcon: true,
      isLeftPosition: true
    },
    newIssue: {
      size: 's',
      color: isNewLabel ? 'outlineBlue' : 'containerBlue',
      iconType: isNewLabel ? 'xSquare' : 'plus',
      isIcon: true,
      buttonText: isNewLabel ? '닫기' : '레이블 추가',
      isLeftPosition: true
    }
  };
  return (
    <MyLabelListPage>
      <MyPageTabButtons>
        <MyPageMoveBUttons>
          <Button
            {...filterTabOptions.labels}
            buttonText={`레이블 (${countInfo?.labelCount || 0})`}
            onClick={() => navigate('/labels')}
          />
          <Button
            {...filterTabOptions.milestone}
            buttonText={`마일스톤 (${countInfo?.milestoneCount || 0})`}
            onClick={() => navigate('/milestone')}
          />
        </MyPageMoveBUttons>
        <Button
          {...filterTabOptions.newIssue}
          onClick={() =>
            isNewLabel ? setIsNewLabel(false) : setIsNewLabel(true)
          }
        />
      </MyPageTabButtons>
      {isNewLabel && <NewLabelSection />}
      <MyLabelList>
        <MyLabelListHeader>
          {countInfo.labelCount} 개의 레이블
        </MyLabelListHeader>
        {labels &&
          labels.map((label) => (
            <MyLabelItem key={label.id}>
              <div>
                <LabelTag
                  key={label.id}
                  tagType={'labels'}
                  hasIcon={false}
                  text={label.name}
                  backgroundColor={label.backgroundColor}
                  fontColor={label.fontColor}
                />
              </div>
              <p>{label.description}</p>
              <MyLabelEditButtons>
                <Button
                  size={'xs'}
                  color={'ghostGray'}
                  buttonText={'편집'}
                  isIcon
                  iconType={'edit'}
                  isLeftPosition
                />
                <Button
                  size={'xs'}
                  color={'ghostRed'}
                  buttonText={'삭제'}
                  isIcon
                  iconType={'trash'}
                  isLeftPosition
                />
              </MyLabelEditButtons>
            </MyLabelItem>
          ))}
      </MyLabelList>
    </MyLabelListPage>
  );
};

const MyLabelListPage = styled.div`
  width: 1280px;
  margin: 0 auto;
  > div {
    justify-content: space-between;
  }
`;

const MyPageTabButtons = styled.div`
  display: grid;
  grid-template-columns: 1fr auto;
  padding: 0 0 25px 0;
  button {
    font-size: 14px;
  }
`;

const MyPageMoveBUttons = styled.div`
  display: flex;
  height: 40px;
  width: 300px;
  border-radius: 11px;
  border: 1px solid ${colors.gray300};
  > button {
    width: 100%;
    &:first-child {
      border-radius: 11px 0px 0px 11px;
      border-right: 1px solid ${colors.gray300};
    }
    &:last-child {
      border-radius: 0px 11px 11px 0px;
    }
    &: hover {
      background: ${colors.gray200};
    }
  }
`;

const MyLabelList = styled.div`
  border: 1px solid #d9dbe9;
  border-radius: 16px;
  > div:not(:last-child) {
    border-bottom: 1px solid ${colors.gray300};
  }
  > div:last-child {
    border-radius: 0px 0px 16px 16px;
  }
`;

const MyLabelListHeader = styled.div`
  padding: 0 25px;
  height: 64px;
  line-height: 64px;
  background-color: ${colors.gray100};
  border-bottom: 1px solid ${colors.gray300};
  border-radius: 16px 16px 0px 0px;
  ${fontType.REGULAR}
  color: ${colors.gray600};
`;

const MyLabelItem = styled.div`
  display: grid;
  grid-template-columns: 1.5fr 8fr 1fr;
  height: 100px;
  align-items: center;
  padding: 0 25px;
  background-color: ${colors.gray50};
  color: ${colors.gray600};
  ${fontSize.M}
  ${fontType.LIGHT}
  &: hover {
    background-color: ${colors.gray100};
  }
  > div {
    width: fit-content;
  }
`;

const MyLabelEditButtons = styled.div`
  display: flex;
`;
