export const fetchData = async (path) => {
  const response = await fetch(path, {
    method: 'GET',
    header: {
      Authorization: `Bearer ${localStorage.jwtToken}`
    }
  });
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
    // console.error('Error:', error);
  }
};
export const fetchPut = async ({ path, data }) => {
  try {
    const response = await fetch(path, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    });
  } catch (error) {
    // console.error('Error:', error);
  }
};
export const fetchDelete = async ({ path, data }) => {
  try {
    const response = await fetch(path, {
      method: 'DELETE',
      body: JSON.stringify(data)
    });
  } catch (error) {
    // console.error('Error:', error);
  }
};

export const fetchPatch = async ({ path, data }) => {
  try {
    const response = await fetch(path, {
      method: 'PATCH',
      body: JSON.stringify(data)
    });
  } catch (error) {
    // console.error('Error:', error);
  }
};
