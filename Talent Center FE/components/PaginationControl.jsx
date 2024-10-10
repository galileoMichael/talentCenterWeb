import React from 'react';
import { Pagination, Select, MenuItem, Box, FormControl, InputLabel } from '@mui/material';


const PaginationControl = ({ page, size, total, onPageChange, onSizeChange }) => {
  const handlePageChange = (event, value) => {
    onPageChange(value - 1);
  };

  const handleSizeChange = (event) => {
    onSizeChange(event.target.value);
  };

  return (
    <Box display="flex" alignItems="center" justifyContent="space-between" p={2}>
      <FormControl variant="outlined" size="small">
        <InputLabel>Entries</InputLabel>
        <Select
          value={size}
          onChange={handleSizeChange}
          label="Entries"
        >
          <MenuItem value={10}>10</MenuItem>
          <MenuItem value={20}>20</MenuItem>
          <MenuItem value={50}>50</MenuItem>
        </Select>
      </FormControl>
      <Pagination
        count={Math.ceil(total / size)}
        page={page + 1}
        onChange={handlePageChange}
        color="primary"
        showFirstButton
        showLastButton
      />
    </Box>
  );
};

export default PaginationControl;