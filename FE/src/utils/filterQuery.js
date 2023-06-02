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
