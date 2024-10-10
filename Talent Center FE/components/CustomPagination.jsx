import React from 'react';
import Pagination from '@mui/lab/Pagination';
import { Select, MenuItem, FormControl, InputLabel } from '@mui/material';
import './Pagination.css'; 

const CustomPagination = ({ totalPages, currentPage, onPageChange, entriesPerPage, onEntriesChange }) => {
    return (
        <div className="pagination-container">
            <div className="entries-selector">
                <FormControl variant="outlined" size="small">
                    <InputLabel>Entries</InputLabel>
                    <Select
                        value={entriesPerPage}
                        onChange={(e) => onEntriesChange(e.target.value)}
                        label="Entries"
                    >
                        <MenuItem value={10}>10</MenuItem>
                        <MenuItem value={20}>20</MenuItem>
                        <MenuItem value={50}>50</MenuItem>
                    </Select>
                </FormControl>
            </div>
            <Pagination
                count={totalPages}
                page={currentPage}
                onChange={(e, page) => onPageChange(page)}
                color="primary"
                shape="rounded"
                className="pagination"
            />
        </div>
    );
};

export default CustomPagination;
