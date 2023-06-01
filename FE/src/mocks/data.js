export const users = [
  {
    id: 0,
    name: '나니',
    profileUrl:
      'https://github.com/bread1022/TIL/assets/107349637/db753467-76aa-493d-9875-6aff6fc6c656'
  },
  {
    id: 1,
    name: '워터',
    profileUrl:
      'https://github.com/bread1022/TIL/assets/107349637/e675d61f-2884-4084-b7db-604ad238fc87'
  },
  {
    id: 2,
    name: '버디',
    profileUrl:
      'https://github.com/bread1022/TIL/assets/107349637/bacc531d-a512-4a54-9c5c-5e22d7425900'
  },
  {
    id: 3,
    name: '감자',
    profileUrl:
      'https://github.com/bread1022/TIL/assets/107349637/e3d247fa-47b0-4d13-8dc5-a3cae495d732'
  },
  {
    id: 4,
    name: '노아',
    profileUrl:
      'https://github.com/bread1022/TIL/assets/107349637/6094efe1-d378-43bc-9d5e-e6cf5e792e3d'
  },
  {
    id: 5,
    name: '데미안',
    profileUrl:
      'https://github.com/bread1022/TIL/assets/107349637/a981d3b0-77ec-4402-a658-ca6a41ed41de'
  }
];

export const labels = [
  {
    id: 0,
    name: 'Bug',
    backgroundColor: '#389561',
    fontColor: '#fff'
  },
  {
    id: 1,
    name: 'Feature',
    backgroundColor: '#FFBBC6',
    fontColor: '#000'
  },
  {
    id: 2,
    name: 'Enhancement',
    backgroundColor: '#6E6ED7',
    fontColor: '#fff'
  },
  {
    id: 3,
    name: 'Refactor',
    backgroundColor: '#975a97',
    fontColor: '#fff'
  }
];

export const milestones = [
  {
    id: 1,
    name: 'First Milestone',
    endDate: '2023-06-20',
    contents: 'contents 1'
  },
  {
    id: 2,
    name: 'Second Milestone',
    endDate: '2023-07-22',
    contents: 'contents 2'
  },
  {
    id: 3,
    name: 'Third Milestone',
    endDate: '2023-09-25',
    contents: 'contents 3'
  }
];

export const issueList = [
  {
    issueId: 0,
    title: 'First Issue',
    createTime: '2023-05-22T05:34:16.754Z',
    updateTime: '2023-05-22T05:34:16.754Z',
    isOpen: false,
    milestone: {
      id: 0,
      name: 'First Milestone'
    },
    commentAuthors: [0, 3],
    author: {
      id: 3,
      name: 'GamJa',
      profileUrl:
        'https://github.com/bread1022/TIL/assets/107349637/e3d247fa-47b0-4d13-8dc5-a3cae495d732'
    },
    assignees: [
      {
        id: 0,
        name: 'Nani',
        profileUrl:
          'https://github.com/bread1022/TIL/assets/107349637/db753467-76aa-493d-9875-6aff6fc6c656'
      }
    ],
    labels: [
      {
        id: 0,
        name: 'Bug',
        backgroundColor: '#389561',
        fontColor: '#fff'
      },
      {
        id: 3,
        name: 'Refactor',
        backgroundColor: '#975a97',
        fontColor: '#fff'
      }
    ]
  },
  {
    issueId: 1,
    title: 'Second Issue',
    createTime: '2023-04-12T03:34:16.754Z',
    updateTime: '2023-05-22T06:34:16.754Z',
    isOpen: true,
    milestone: {
      id: 1,
      name: 'Second Milestone'
    },
    commentAuthors: [0, 3],
    author: {
      id: 5,
      name: 'Demian',
      profileUrl:
        'https://github.com/bread1022/TIL/assets/107349637/a981d3b0-77ec-4402-a658-ca6a41ed41de'
    },
    assignees: [
      {
        id: 3,
        name: 'GamJa',
        profileUrl:
          'https://github.com/bread1022/TIL/assets/107349637/e3d247fa-47b0-4d13-8dc5-a3cae495d732'
      }
    ],
    labels: [
      {
        id: 1,
        name: 'Feature',
        backgroundColor: '#FFBBC6',
        fontColor: '#000'
      }
    ]
  },
  {
    issueId: 2,
    title: 'Third Issue',
    createTime: '2023-05-21T05:34:16.754Z',
    updateTime: '2023-05-21T06:34:16.754Z',
    isOpen: true,
    milestone: {
      id: 2,
      name: 'Third Milestone'
    },
    commentAuthors: [2, 4],
    author: {
      id: 3,
      name: 'GamJa',
      profileUrl:
        'https://github.com/bread1022/TIL/assets/107349637/e3d247fa-47b0-4d13-8dc5-a3cae495d732'
    },
    assignees: [
      {
        id: 4,
        name: 'Noah',
        profileUrl:
          'https://github.com/bread1022/TIL/assets/107349637/6094efe1-d378-43bc-9d5e-e6cf5e792e3d'
      }
    ],
    labels: [
      {
        id: 2,
        name: 'Enhancement',
        backgroundColor: '#6E6ED7',
        fontColor: '#fff'
      }
    ]
  },
  {
    issueId: 3,
    title: 'Fourth Issue',
    createTime: '2023-05-22T05:34:16.754Z',
    updateTime: '2023-05-22T07:34:16.754Z',
    isOpen: true,
    milestone: {
      id: 3,
      name: 'Fourth Milestone'
    },
    commentAuthors: [1, 2, 3],
    author: {
      id: 1,
      name: 'Water',
      profileUrl:
        'https://github.com/bread1022/TIL/assets/107349637/e675d61f-2884-4084-b7db-604ad238fc87'
    },
    assignees: [
      {
        id: 4,
        name: 'Noah',
        profileUrl:
          'https://github.com/bread1022/TIL/assets/107349637/6094efe1-d378-43bc-9d5e-e6cf5e792e3d'
      }
    ],
    labels: [
      {
        id: 5,
        name: 'Build',
        backgroundColor: '#82B3ED',
        fontColor: '#14142B'
      }
    ]
  }
];

export const commentData = [
  {
    issueId: 0,
    comments: [
      {
        userId: 0,
        userName: '나니',
        userUrl:
          'https://github.com/bread1022/TIL/assets/107349637/db753467-76aa-493d-9875-6aff6fc6c656',
        createTime: '2023-05-24T03:34:16.754Z',
        replyContents: '닫았음',
        emojiShape: 'thumbs-up',
        emojiCount: 5,
        fileAttachmentUrl: 'https://example.com/files/1'
      },
      {
        userId: 3,
        userName: '감자',
        userUrl:
          'https://github.com/bread1022/TIL/assets/107349637/e3d247fa-47b0-4d13-8dc5-a3cae495d732',
        createTime: '2023-05-24T03:34:16.754Z',
        replyContents: '잠시만요',
        emojiShape: 'thumbs-up',
        emojiCount: 5,
        fileAttachmentUrl: 'https://example.com/files/1'
      }
    ]
  },
  {
    issueId: 1,
    comments: [
      {
        userId: 0,
        userName: '나니',
        userUrl:
          'https://github.com/bread1022/TIL/assets/107349637/db753467-76aa-493d-9875-6aff6fc6c656',
        createTime: '2023-05-24T03:34:16.754Z',
        replyContents: '저도 좋은데 어떻게 생각해요?',
        emojiShape: 'thumbs-up',
        emojiCount: 5,
        fileAttachmentUrl: 'https://example.com/files/1'
      },
      {
        userId: 3,
        userName: '감자',
        userUrl:
          'https://github.com/bread1022/TIL/assets/107349637/e3d247fa-47b0-4d13-8dc5-a3cae495d732',
        createTime: '2023-05-24T03:34:16.754Z',
        replyContents: '그래요',
        emojiShape: 'thumbs-up',
        emojiCount: 5,
        fileAttachmentUrl: 'https://example.com/files/1'
      }
    ]
  },
  {
    issueId: 2,
    comments: [
      {
        userId: 1,
        userName: '워터',
        userUrl:
          'https://github.com/bread1022/TIL/assets/107349637/e675d61f-2884-4084-b7db-604ad238fc87',
        createTime: '2023-05-24T03:34:16.754Z',
        replyContents: '조아요오오',
        emojiShape: 'thumbs-up',
        emojiCount: 5,
        fileAttachmentUrl: 'https://example.com/files/1'
      },
      {
        userId: 3,
        userName: '감자',
        userUrl:
          'https://github.com/bread1022/TIL/assets/107349637/e3d247fa-47b0-4d13-8dc5-a3cae495d732',
        createTime: '2023-05-24T03:34:16.754Z',
        replyContents: '나이스',
        emojiShape: 'thumbs-up',
        emojiCount: 5,
        fileAttachmentUrl: 'https://example.com/files/1'
      }
    ]
  },
  {
    issueId: 3,
    comments: [
      {
        userId: 4,
        userName: '노아',
        userUrl:
          'https://github.com/bread1022/TIL/assets/107349637/6094efe1-d378-43bc-9d5e-e6cf5e792e3d',
        createTime: '2023-05-24T03:34:16.754Z',
        replyContents: '넹',
        emojiShape: 'thumbs-up',
        emojiCount: 5,
        fileAttachmentUrl: 'https://example.com/files/1'
      },
      {
        userId: 2,
        userName: '버디',
        userUrl:
          'https://github.com/bread1022/TIL/assets/107349637/bacc531d-a512-4a54-9c5c-5e22d7425900',
        createTime: '2023-05-24T03:34:16.754Z',
        replyContents: '넵!',
        emojiShape: 'thumbs-up',
        emojiCount: 5,
        fileAttachmentUrl: 'https://example.com/files/1'
      },
      {
        userId: 5,
        userName: '데미안',
        userUrl:
          'https://github.com/bread1022/TIL/assets/107349637/a981d3b0-77ec-4402-a658-ca6a41ed41de',
        createTime: '2023-05-24T03:34:16.754Z',
        replyContents: '옙',
        emojiShape: 'thumbs-up',
        emojiCount: 5,
        fileAttachmentUrl: 'https://example.com/files/1'
      }
    ]
  }
];
