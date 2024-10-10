import React, { useEffect, useState } from 'react';
import filterIcon from './assets/Filter Section Title.png';
import { getFilterOptions } from '../services/masterDataService';

const Sidebar = () => {
  const [filterOptions, setFilterOptions] = useState({
    positions: [],
    skillset: [],
    experiences: [],
    levels: [],
  });

  useEffect(() => {
    const fetchFilterOptions = async () => {
      try {
        const options = await getFilterOptions();
        setFilterOptions(options);
      } catch (error) {
        console.error('Failed to fetch filter options:', error);
      }
    };

    fetchFilterOptions();
  }, []);

  return (
    <div>
      <img src={filterIcon} alt='Filter Icon' />
      {Object.keys(filterOptions).map(category => (
        <div key={category}>
          <h4>{category.charAt(0).toUpperCase() + category.slice(1)}</h4>
          <ul>
            {filterOptions[category].map(option => (
              <li key={option.positionId || option.skillSetId || option.talentLevelId || option.experience}>
                {option.positionName || option.skillName || option.talentLevelName || option.experience}
              </li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
};

export default Sidebar;