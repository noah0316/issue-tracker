import { rest } from 'msw';

import closeFilter from './closeFilterData.json';
import { issueList, commentData } from './data';
import labelData from './labelData.json';
import milestoneData from './milestoneData.json';
import issueData from './mockIssueData.json';
import openFilter from './openFilterData.json';
import userData from './userData.json';

export const handlers = [
  rest.get('/issues', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(issueData));
  }),

  // detail params 를 위한 데이터
  rest.get('/issueDetail/:issueId', (req, res, ctx) => {
    const { issueId } = req.params;
    const list = issueList.find(
      (list) => parseInt(issueId) === parseInt(list.issueId)
    );
    return res(ctx.status(200), ctx.json(list));
  }),

  rest.get('/issues/:issueId/comments', (req, res, ctx) => {
    const { issueId } = req.params;
    const list = commentData.find(
      (list) => parseInt(issueId) === parseInt(list.issueId)
    );
    return res(ctx.status(200), ctx.json(list));
  }),

  rest.get('/labels', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(labelData));
  }),

  rest.get('/milestone', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(milestoneData));
  }),

  rest.get('/user', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(userData));
  }),

  rest.get('/issues/isOpen=true', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(openFilter));
  }),
  rest.get('/issues/isOpen=false', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(closeFilter));
  })
];
