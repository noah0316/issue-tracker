import { Icon } from '../../assets/Icon';
import { colors } from '../../styles/color';
import { Profile } from '../Profile';

export const DropdownPanelItem = ({
  tabId,
  id,
  option,
  profileUrl,
  backgroundColor,
  isSelected,
  onMouseUp
}) => {
  const iconType = isSelected ? 'checkOnCircle' : 'checkOffCircle';
  return (
    <li id={id} value={tabId} onMouseUp={onMouseUp}>
      {profileUrl && <Profile userInfo={{ option, profileUrl }} />}
      {backgroundColor && (
        <Icon iconType={'roundImage'} fill={backgroundColor} />
      )}
      <div>{option}</div>
      <Icon iconType={iconType} fill={colors.gray700} />
    </li>
  );
};
