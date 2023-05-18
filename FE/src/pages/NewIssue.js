import styled from 'styled-components';

import { Button } from '../components/button/Button';
import { Header } from '../components/Header';
import { Sidebar } from '../components/newIssue/Sidebar';
import { TextInput } from '../components/newIssue/TextInput';
import { colors } from '../styles/color';

const NewIssuePage = styled.div`
  width: 1280px;
  margin: 0px auto;
`;

export const NewIssue = () => {
  const cancelBtnConstant = {
    type: 'ghostButton',
    btnColor: colors.gray700,
    backgroundColor: 'transparent',
    hoverColor: colors.gray700,
    btnText: '작성 취소',
    isIcon: true,
    iconType: 'xSquare',
    isLeftPosition: true
  };

  const saveBtnConstant = {
    type: 'containerButton',
    btnColor: colors.gray50,
    backgroundColor: colors.blue,
    hoverColor: colors.gray50,
    btnText: '완료',
    btnWidth: '240px',
    btnHeight: '56px',
    isIcon: false,
    iconType: 'plus',
    isLeftPosition: true
  };

  const titleInputConstant = {
    type: 'defaultTextInput',
    isIcon: false,
    initialText: '제목',
    inputWidth: '912px',
    inputHeight: '56px'
  };

  const comentInputConstant = {
    type: 'defaultTextInput',
    isIcon: false,
    initialText: '코멘트를 입력하세요',
    inputWidth: '912px',
    inputHeight: '436px'
  };
  // TODO : Fetch -> user 정보 필요
  return (
    <NewIssuePage>
      {/* TODO : Header 고정으로 빼기  */}
      <Header />
      <Header text={'새로운 이슈 작성'} />
      <Button
        type={cancelBtnConstant.type}
        buttonColor={cancelBtnConstant.btnColor}
        hoverColor={cancelBtnConstant.hoverColor}
        backgroundColor={cancelBtnConstant.backgroundColor}
        buttonText={cancelBtnConstant.btnText}
        isIcon={cancelBtnConstant.isIcon}
        iconType={cancelBtnConstant.iconType}
        isLeftPosition={cancelBtnConstant.isLeftPosition}
      />
      <Button
        type={saveBtnConstant.type}
        buttonColor={saveBtnConstant.btnColor}
        hoverColor={saveBtnConstant.hoverColor}
        backgroundColor={saveBtnConstant.backgroundColor}
        buttonText={saveBtnConstant.btnText}
        buttonWidth={saveBtnConstant.btnWidth}
        buttonHeight={saveBtnConstant.btnHeight}
        isIcon={saveBtnConstant.isIcon}
        iconType={saveBtnConstant.iconType}
        isLeftPosition={saveBtnConstant.isLeftPosition}
      />
      <Sidebar />
      <TextInput
        type={titleInputConstant.type}
        isIcon={titleInputConstant.isIcon}
        initialText={titleInputConstant.initialText}
        inputWidth={titleInputConstant.inputWidth}
        inputHeight={titleInputConstant.inputHeight}
      />
    </NewIssuePage>
  );
};
