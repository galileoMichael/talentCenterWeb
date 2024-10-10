import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import FilterSidebar from '../components/FilterSidebar';
import Header from '../components/Header';
import TalentCard from '../components/TalentCard';
import { useTalents } from '../hooks/useTalents';
import { addTalentToList } from '../services/talentService';
import Modal from '../components/Modal';
import PaginationControl from '../components/PaginationControl';
import './MainPage.css';

const MainPage = ({ openSignInModal, openRegisterModal }) => {
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(10);
  const [sort, setSort] = useState('experience');
  const [direction, setDirection] = useState('DESC');
  const [filters, setFilters] = useState({});
  const { talents, loading, error, total } = useTalents(page, size, sort, direction, filters);
  const [wishlist, setWishlist] = useState([]);
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleFilterChange = (selectedFilters) => {
    setFilters(selectedFilters);
    setPage(0); 
  };

  const handleSortChange = (event) => {
    const [newSort, newDirection] = event.target.value.split('_');
    setSort(newSort);
    setDirection(newDirection);
  };

  const handleAddToList = async (talentId) => {
    try {
      await addTalentToList({ talentId });
      setWishlist([...wishlist, talentId]);
      setIsModalOpen(true);
    } catch (error) {
      console.error('Error adding talent to list', error);
    }
  };

  const handleCloseModal = () => setIsModalOpen(false);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div className="main-page">
      <Navbar openSignInModal={openSignInModal} openRegisterModal={openRegisterModal} />
      <div className="content">
        <FilterSidebar onFilterChange={handleFilterChange} />
        <div className="main-content">
          <Header total={total} filters={filters} onSortChange={handleSortChange} />
          <div className="talent-list">
            {talents.map(talent => (
              <TalentCard 
                key={talent.talentId} 
                talent={talent} 
                onAddToList={() => handleAddToList(talent.talentId)}
              />
            ))}
          </div>
          <PaginationControl
            page={page}
            size={size}
            total={total}
            onPageChange={setPage}
            onSizeChange={setSize}
          />
        </div>
      </div>
      <Modal isOpen={isModalOpen} onClose={handleCloseModal}>
        <p>Talent added to Wishlist</p>
        <button onClick={() => window.location.href = '/main'}>View</button>
      </Modal>
    </div>
  );
};

export default MainPage;
