export const initialFilterState = {
  isOpen: true,
  author: null, // 작성자
  labels: null, // 레이블 중복가능
  milestones: null, // 마일스톤
  assignees: null, // 담당자 중복가능
  comments: null // 댓글
};

// export const initialFilterState = {
//   isOpen: true,
//   author: null, // 작성자
//   labels: [], // 레이블 중복가능
//   milestone: null, // 마일스톤
//   assignees: [], // 담당자 중복가능
//   comments: null // 댓글
// };

export const filterStateReducer = (state, action) => {
  const { type, payload } = action;

  switch (type) {
    case 'RESET-FILTER': {
      return initialFilterState;
    }
    // 토글하면 사라지게 구현 (중복은 안됨)
    case 'FILTER-ISSUES': {
      const { filterTab, id } = payload;
      if (filterTab in state) {
        if (state[filterTab] === id) {
          return {
            ...state,
            [filterTab]: null
          };
        } else {
          return {
            ...state,
            [filterTab]: id
          };
        }
      } else {
        return filterByMe(state, filterTab, id);
      }
    }
    case 'FILTER-OPEN': {
      return {
        ...state,
        isOpen: payload.id === 'isOpen'
      };
    }
    default:
      return state;
  }
};

// TODO: 중복 가능하는 로직으로 수정 필요.. (?)
// const handleDuplicateLabelsOrAssignees = (state, filterTab, id) => {
//   if (filterTab !== 'labels' && filterTab !== 'assignees') return;
//   return state[filterTab].reduce(
//     (acc, issue) => (issue === id ? acc : [...acc, id]),
//     []
//   );
// };

const filterByMe = (state, filterTab, id) => {
  if (filterTab !== 'filter') return;
  switch (id) {
    case 'author': {
      return {
        ...state,
        author: id // TODO: 유저 id 로 변경필요
      };
    }
    default:
      return state;
  }
};
