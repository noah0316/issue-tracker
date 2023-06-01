import React, { useState } from 'react';

import { BrowserRouter, Route, Routes } from 'react-router-dom';
// import { ThemeProvider } from 'styled-components';

import { Header } from './components/Header';
import { HeaderLayout } from './components/HeaderLayout';
import { Auth } from './pages/Auth';
import { IssueDetail } from './pages/IssueDetail';
import { IssueList } from './pages/IssueList';
import { LabelList } from './pages/LabelList';
import { Login } from './pages/Login';
import { MilestoneList } from './pages/MilestoneList';
import { NewIssue } from './pages/NewIssue';
import { GlobalStyle } from './styles/GlobalStyle';
// import { lightTheme } from './styles/theme';

const App = () => {
  const [login, setLogin] = useState({ isLogin: false, accessToken: '' });
  return (
    <>
      {/* <ThemeProvider theme={lightTheme}> */}
      <GlobalStyle />
      <BrowserRouter>
        <div className="App">
          <Routes>
            <Route path="/" element={<Login />} />
            <Route element={<HeaderLayout />}>
              <Route path="/auth" element={<Auth />} />
              <Route path="/issues" element={<IssueList />} />
              <Route path="/newIssue" element={<NewIssue />} />
              <Route path="/issueDetail/:issueId" element={<IssueDetail />} />
              <Route path="/labels" element={<LabelList />} />
              <Route path="/milestone" element={<MilestoneList />} />
            </Route>
          </Routes>
        </div>
      </BrowserRouter>
      {/* </ThemeProvider> */}
    </>
  );
};

export default App;
