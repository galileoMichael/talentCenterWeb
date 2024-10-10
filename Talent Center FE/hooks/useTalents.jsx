import { useState, useEffect } from 'react';
import { getTalents, addTalentToList, downloadCV } from '../services/talentService';

export const useTalents = (page, size, sort, direction, filters) => {
  const [talents, setTalents] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [total, setTotal] = useState(0);

  useEffect(() => {
    const fetchTalents = async () => {
      setLoading(true);
      try {
        const response = await getTalents({ page, size, sort, direction, filters });
        setTalents(response.data);
        setTotal(response.total);
      } catch (error) {
        setError(error);
      } finally {
        setLoading(false);
      }
    };

    fetchTalents();
  }, [page, size, sort, direction, filters]);

  return { talents, loading, error, total };
};


export const useAddTalentToList = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const addTalent = async (data) => {
    setLoading(true);
    try {
      await addTalentToList(data);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  return { addTalent, loading, error };
};

export const useDownloadCV = () => {
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const download = async (data) => {
    setLoading(true);
    try {
      const file = await downloadCV(data);
    } catch (err) {
      setError(err);
    } finally {
      setLoading(false);
    }
  };

  return { download, loading, error };
};
