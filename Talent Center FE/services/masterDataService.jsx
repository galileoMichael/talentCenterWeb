import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/master-management';

export const getFilterOptions = async () => {
  try {
    const [positions, skillset, experiences, levels] = await Promise.all([
      axios.get(`${API_BASE_URL}/talent-position-option-lists`),
      axios.get(`${API_BASE_URL}/skill-set-option-lists?type=all`),
      axios.get(`${API_BASE_URL}/year-experience-option-lists?page=0&size=8`),
      axios.get(`${API_BASE_URL}/talent-level-option-lists`),
    ]);

    return {
      positions: positions.data.data, 
      skillset: skillset.data.data, 
      experiences: experiences.data, 
      levels: levels.data.data, 
    };

  } catch (error) {
    console.error('Error fetching filter options', error);
    throw error;
  }
};