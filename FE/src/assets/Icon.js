import * as icons from './svgs';
import { colors } from '../styles/color';

export const Icon = ({
  iconType,
  width = 16,
  height = width,
  fill = colors.gray50,
  onClick
}) => {
  const IconSvg = icons[iconType];
  return (
    <IconSvg width={width} height={height} fill={fill} onClick={onClick} />
  );
};
