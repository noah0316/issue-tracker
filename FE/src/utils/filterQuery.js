export const getFilterQueryString = ({
  isOpen,
  author,
  labels, // 중복가능
  milestones,
  assignees, // 중복가능
  isWrittenByMe,
  isAssignedToMe,
  commentedByMe
}) => {
  return [
    `?isOpen=${isOpen}`,
    author && `&author=${author}`,
    labels && `&labels=${labels}`,
    milestones && `&milestone=${milestones}`,
    assignees && `&assignees=${assignees}`,
    isWrittenByMe && `&isWrittenByMe=${isWrittenByMe}`,
    isAssignedToMe && `&isAssignedToMe=${isAssignedToMe}`,
    commentedByMe && `&commentedByMe=${commentedByMe}`
  ].join('');
};

// export const getFilterQueryString = ({
//   isOpen,
//   author,
//   labels, // 중복가능
//   milestone,
//   assignees, // 중복가능
//   comments, //
//   isWrittenByMe,
//   isAssignedToMe,
//   commentedByMe
// }) => {
//   return [
//     `isOpen=${isOpen}`,
//     author && `&author=${author}`,
//     labels?.length ? `&labels=${labels.map((id) => id).join(',')}` : null,
//     milestone && `&milestone=${milestone}`,
//     assignees?.length
//       ? `&assignees=${assignees.map((id) => id).join(',')}`
//       : null,
//     comments && `&comments=${comments}`,
//     isWrittenByMe && `&isWrittenByMe=${isWrittenByMe}`,
//     isAssignedToMe && `&isAssignedToMe=${isAssignedToMe}`,
//     commentedByMe && `&commentedByMe=${commentedByMe}`
//   ].join('');
// };

// export const convertFilterQueryToInputValue = ({
//   isOpen,
//   author,
//   labels,
//   milestone,
//   assignees,
//   comments,
//   isWrittenByMe,
//   isAssignedToMe,
//   commentedByMe
// }) => {
//   return [
//     `is:${isOpen ? 'open' : 'close'} is:issue`,
//     author && `author:${author}`,
//     labels?.length ? `&labels=${labels.map((id) => id).join(',')}` : null,

//     milestone && `milestone:${milestone}`,
//     assignees?.length
//       ? `&assignees=${assignees.map((id) => id).join(',')}`
//       : null,
//     comments && `comments:${comments}`,
//     isWrittenByMe && `isWrittenByMe=@me`,
//     isAssignedToMe && `isAssignedToMe=@me`,
//     commentedByMe && `commentedByMe=@me`
//   ]
//     .filter((query) => query)
//     .join(' ');
// };

export const convertFilterQueryToInputValue = ({
  isOpen,
  author,
  labels,
  milestones,
  assignees,
  comments,
  isWrittenByMe,
  isAssignedToMe,
  commentedByMe
}) => {
  return [
    `is:${isOpen ? 'open' : 'close'} is:issue`,
    author && `author:${author}`,
    labels && `labels:${labels}`,
    milestones && `milestone:${milestones}`,
    assignees && `assignees:${assignees}`,
    comments && `comments:${comments}`,
    isWrittenByMe && `isWrittenByMe=@me`,
    isAssignedToMe && `isAssignedToMe=@me`,
    commentedByMe && `commentedByMe=@me`
  ]
    .filter((query) => query)
    .join(' ');
};
