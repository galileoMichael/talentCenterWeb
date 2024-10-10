import React from 'react';
import './Header.css';

const Header = ({ total, filters, onSortChange }) => {
  const getFilterText = () => {
    const filterKeys = Object.keys(filters).filter(key => filters[key].length > 0);
    return filterKeys.length > 0 ? filterKeys.map(key => filters[key].join(', ')).join(' and ') : 'All';
  };

  return (
    <header className="header">
      <div className="header-title">
        Showing you {total > 0 ? `1 - ${Math.min(10, total)} talents out of ${total}` : 'No talents found'} for "{getFilterText()}"
      </div>
      <div className="header-controls">
        Sort By
        <select onChange={onSortChange} className="header-sort">
          <option value="name_asc">A-Z</option>
          <option value="name_desc">Z-A</option>
          <option value="level_asc">Level ↑</option>
          <option value="level_desc">Level ↓</option>
          <option value="experience_asc">Years of Experience ↑</option>
          <option value="experience_desc">Years of Experience ↓</option>
          <option value="availability">Availability</option>
        </select>
      </div>
    </header>
  );
};

export default Header;
