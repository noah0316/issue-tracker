import { useState } from 'react';

import styled from 'styled-components';

import { NewLabelSection } from './NewlabelSection';
import { colors } from '../../styles/color';
import { fontSize, fontType } from '../../styles/font';
import { fetchDelete } from '../../utils/fetch';
import { Button } from '../button/Button';
import { LabelTag } from '../LabelTag';
export const LabelListItem = ({
  id,
  labelText,
  labelBackgroundColor,
  labelFontColor,
  labelDescription,
  labelSetValue,
  labelValue,
  countSetValue
}) => {
  const [isEditLabel, setIsEditLabel] = useState(false);

  const handleDelete = async ({ id }) => {
    const url = `${process.env.REACT_APP_BASE_URI}/labels/${id}`;
    const idData = {
      labelId: id
    };
    await fetchDelete({ path: url, data: idData });
  };

  const handleEdit = () => {
    setIsEditLabel(true);
  };

  return (
    <>
      {isEditLabel
        ? (
          <>
            <NewLabelSection
              id={id}
              labelSetValue={labelSetValue}
              countSetValue={countSetValue}
              isEditLabel={isEditLabel}
              setIsEditLabel={setIsEditLabel}
              labelTitle={labelText}
              labelDescription={labelDescription}
              labelBackgroundColor={labelBackgroundColor}
              labelFontColor={labelFontColor}
            />
          </>
        )
        : (
          <>
            <MyLabelItem>
              <div>
                <LabelTag
                  tagType={'labels'}
                  hasIcon={false}
                  text={labelText}
                  backgroundColor={labelBackgroundColor}
                  fontColor={labelFontColor}
                />
              </div>
              <p>{labelDescription}</p>
              <MyLabelEditButtons>
                <Button
                  size={'xs'}
                  color={'ghostGray'}
                  buttonText={'편집'}
                  isIcon
                  iconType={'edit'}
                  isLeftPosition
                  onClick={handleEdit}
                />
                <Button
                  size={'xs'}
                  color={'ghostRed'}
                  buttonText={'삭제'}
                  isIcon
                  iconType={'trash'}
                  isLeftPosition
                  onClick={() => handleDelete({ id })}
                />
              </MyLabelEditButtons>
            </MyLabelItem>
          </>
        )}
    </>
  );
};

const MyLabelItem = styled.div`
  display: grid;
  grid-template-columns: 1.5fr 8fr 1.3fr;
  height: 100px;
  align-items: center;
  // padding: 0 25px;
  background-color: ${colors.gray50};
  color: ${colors.gray600};
  ${fontSize.M}
  ${fontType.LIGHT}
  &: hover {
    background-color: ${colors.gray100};
  }
  & div:first-child {
    display: flex;
    justify-content: center;
  }
`;

const MyLabelEditButtons = styled.div`
  display: flex;
`;
