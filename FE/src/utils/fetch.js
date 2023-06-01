export const fetchData = async (path) => {
  const response = await fetch(path);
  const resData = await response.json();
  return resData;
};

export const fetchAll = async (...url) => {
  const response = await Promise.all(url.map((path) => fetchData(path)));
  return response;
};

export const fetchPost = async ({ path, data }) => {
  try {
    const response = await fetch(path, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    });
  } catch (error) {
    console.error('Error:', error);
  }
};

export const fetchDelete = async ({ path, data }) => {
  try {
    const response = await fetch(path, {
      method: 'DELETE',
      body: JSON.stringify(data)
    });
  } catch (error) {
    console.error('Error:', error);
  }
};

export const getLoginToken = async (queryCode) => {
  try {
    const data = await customFetch({
      path: '/oauth/result',
      method: 'GET',
      queries: { code: queryCode }
    });
    return data;
  } catch (error) {
    return error;
  }
};
