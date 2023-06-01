export const initialCheckState = {
  isAllChecked: false, // false(initial) 또는 true(disable)
  checkedIssues: []
};

export const checkBoxReducer = (state, action) => {
  const { checkedIssues } = state;
  const { type, payload } = action;

  switch (type) {
    case 'CHECK': {
      return {
        isAllChecked: true,
        checkedIssues: [...checkedIssues, payload]
      };
    }
    case 'UNCHECK': {
      const updateCheckedIssues = checkedIssues.filter((id) => id !== payload);
      const isAllUpdatedChecked = updateCheckedIssues.length > 0;
      return {
        isAllChecked: isAllUpdatedChecked,
        checkedIssues: updateCheckedIssues
      };
    }
    case 'ALL-CHECK': {
      const updateCheckedIssues = payload.map(({ id }) => id);
      return {
        isAllChecked: true,
        checkedIssues: updateCheckedIssues
      };
    }
    case 'ALL-UNCHECK': {
      return { isAllChecked: false, checkedIssues: [] };
    }
    case 'SWITCH-OPEN': {
      const updateCheckedIssues = payload.reduce((acc, issue) => {
        if (checkedIssues.includes(issue.id) && issue.isOpen !== true) {
          issue.isOpen = true;
        }
        acc.push(issue);
        return acc;
      }, []);
      return {
        isAllChecked: false,
        checkedIssues: []
      };
    }
    case 'SWITCH-CLOSE': {
      // TODO: 업데이트한 데이터 POST하고, 그 값을 다시 받아서 리렌더
      const updateCheckedIssues = payload.reduce((acc, issue) => {
        if (checkedIssues.includes(issue.id) && issue.isOpen !== false) {
          issue.isOpen = false;
        }
        acc.push(issue);
        return acc;
      }, []);
      return {
        isAllChecked: false,
        checkedIssues: []
      };
    }
    default:
      return state;
  }
};
