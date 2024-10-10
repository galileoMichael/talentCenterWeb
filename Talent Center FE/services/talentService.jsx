import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/talent-management';
  
const transformFilters = (filters) => {
  const params = {};
  Object.keys(filters).forEach((key) => {
    if (filters[key].length > 0) {
      filters[key].forEach((value) => {
        if (!params[key]) {
          params[key] = [];
        }
        params[key].push(value);
      });
    }
  });
  return params;
};

export const getTalents = async ({ page, size, sort, direction, filters }) => {
  const transformedFilters = transformFilters(filters);
  const response = await axios.get(`${API_BASE_URL}/talents`, {
    params: { page, size, sort, direction, ...transformedFilters },
    paramsSerializer: (params) => {
      return Object.keys(params)
        .map((key) => {
          if (Array.isArray(params[key])) {
            return params[key]
              .map((val) => `${encodeURIComponent(key)}=${encodeURIComponent(val)}`)
              .join('&');
          }
          return `${encodeURIComponent(key)}=${encodeURIComponent(params[key])}`;
        })
        .join('&');
    },
  });
  return response.data;
};

export const addTalentToList = async (data) => {
  const response = await axios.post(`${API_BASE_URL}/talents/add-to-list`, data);
  return response.data;
};

export const downloadCV = async (data) => {
  const response = await axios.post(`${API_BASE_URL}/talents/download-cv`, data, {
  });
  return response.data;
};
