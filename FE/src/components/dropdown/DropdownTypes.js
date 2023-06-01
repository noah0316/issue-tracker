export const tabTypes = {
  author: {
    tabId: 'author',
    tabName: '작성자',
    filterOptions (author) {
      return {
        id: author.id,
        option: author.name,
        profileUrl: author.profileUrl
      };
    }
  },
  labels: {
    tabId: 'labels',
    tabName: '레이블',
    filterOptions (label) {
      return {
        id: label.id,
        option: label.title ?? label.labelName,
        backgroundColor: label.backgroundColor,
        fontColor: label.fontColor
      };
    }
  },
  milestones: {
    tabId: 'milestones',
    tabName: '마일스톤',
    filterOptions (milestone) {
      return {
        id: milestone.id,
        option: milestone.title
      };
    }
  },
  assignees: {
    tabId: 'assignees',
    tabName: '담당자',
    filterOptions (assignee) {
      return {
        id: assignee.id,
        option: assignee.name,
        profileUrl: assignee.profileUrl
      };
    }
  }
};
