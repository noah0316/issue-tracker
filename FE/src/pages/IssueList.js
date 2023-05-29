import React, { useEffect, useReducer, useState } from 'react';

import styled from 'styled-components';

import { FilterSection } from '../components/filterSection/FiterSection';
import { IssueListContainer } from '../components/issueList/IssueListContainer';
import {
  filterStateReducer,
  initialFilterState
} from '../stores/filterStateReducer';
import { fetchData } from '../utils/fetch';
export const FilterStateContext = React.createContext();
export const IssueListContext = React.createContext();

export const IssueList = () => {
  const [issues, setIssues] = useState([]);
  const initData = async () => {
    try {
      const response = await fetchData('http://13.209.232.172:8080/Issues');
      setIssues(response);
    } catch (err) {
      console.err(err);
    }
  };

  useEffect(() => {
    initData();
  }, []);

  const [filterState, filterStateDispatch] = useReducer(
    filterStateReducer,
    initialFilterState
  );

  return (
    <FilterStateContext.Provider value={{ filterState, filterStateDispatch }}>
      <IssueListContext.Provider value={issues}>
        <MyIssueListPage>
          <FilterSection />
          <IssueListContainer />
        </MyIssueListPage>
      </IssueListContext.Provider>
    </FilterStateContext.Provider>
  );
};

const MyIssueListPage = styled.div`
  width: 1280px;
  margin: 0 auto;
`;
