import styled, { css } from 'styled-components';

import { Icon } from '../../assets/Icon';
import { colors } from '../../styles/color';
import { fontType } from '../../styles/font';

export const Button = ({
  disabled,
  size,
  color,
  iconType,
  iconWidth,
  isIcon,
  buttonText,
  isLeftPosition,
  onClick,
  active,
  buttonType
}) => {
  const btnSize = btnSizes[size];
  const btnColor =
    active === undefined
      ? btnColors[color]
      : active
        ? btnColors.ghostBlack
        : btnColors.ghostGray;
  const iconSize = iconWidth || '9';

  return (
    <MyButton
      disabled={disabled}
      btnSize={btnSize}
      btnColor={btnColor}
      onClick={onClick}
      type={buttonType || 'button'}
    >
      {isIcon
        ? (
          isLeftPosition
            ? (
              <>
                <Icon iconType={iconType} width={iconSize} />
                {buttonText}
              </>
            )
            : (
              <>
                {buttonText}
                <Icon iconType={iconType} width={iconSize} />
              </>
            )
        )
        : (
          <>{buttonText}</>
        )}
    </MyButton>
  );
};

const btnSizes = {
  l: css`
    width: 320px;
    height: 56px;
  `,
  m: css`
    width: 240px;
    height: 56px;
  `,
  s: css`
    width: 120px;
    height: 40px;
  `,
  xs: css`
    width: 65px;
    height: 32px;
  `,
  xxs: css`
    width: 59px;
    height: 28px;
  `
};

const btnColors = {
  containerBlue: css`
    background-color: ${colors.blue};
    color: ${colors.gray50};
    border: none;
  `,
  containerBlack: css`
    background-color: ${colors.gray900};
    color: ${colors.gray50};
    border: none;
  `,
  outlineBlue: css`
    background-color: transparent;
    color: ${colors.blue};
    border: 1px solid ${colors.blue};
    > svg {
      fill: ${colors.blue};
    }
  `,
  ghostBlack: css`
    background-color: transparent;
    color: ${colors.gray900};
    border: none;
    > svg {
      fill: ${colors.gray900};
    }
  `,
  ghostGray: css`
    background-color: transparent;
    color: ${colors.gray600};
    border: none;
    > svg {
      fill: ${colors.gray600};
    }
  `,
  ghostRed: css`
    background-color: transparent;
    color: ${colors.red};
    border: none;
    > svg {
      fill: transparent;
      > path {
        stroke: red;
      }
    }
  `
};

const MyButton = styled.button`
  ${(p) => p.btnSize};
  ${(p) => p.btnColor};
  flex-direction: row;
  justify-content: space-evenly;
  align-items: center;
  display: flex;
  border-radius: 11px;
  padding: 0;
  ${fontType.BOLD};
  > svg {
    margin-right: 5px;
  }
  &: hover {
    opacity: 0.8;
  }

  &: active {
    opacity: 0.64;
  }

  &: disabled {
    opacity: 0.32;
  }
`;
