import React from 'react';
import { Box } from '@mui/material';
import { DataGrid, useGridApiRef } from '@mui/x-data-grid';
import getObjectHeaders from '../../utils/DataGridUtils';

export const Table = (
  { data,
    width = 120,
    editable = null,
    widthComponents = null,
    getRowsIdCallback = null,
    processRowUpdate = null,
    onProcessRowUpdateError = null,
    renderCells = null,
    customColumns = null
  }) => {
  const columns = getObjectHeaders(data[0], width, editable, widthComponents, renderCells, customColumns);
  console.log(columns)
  if (columns == null) {
    return;
  }
  return (
    <div>
      <Box sx={{ height: 400, width: '100%' }}>
        <DataGrid editMode='row' rows={data}
          processRowUpdate={processRowUpdate}
          onProcessRowUpdateError={onProcessRowUpdateError}
          experimentalFeatures={{ newEditingApi: true }}
          columns={columns}
          getRowId={getRowsIdCallback ?? undefined} sx={{
            boxShadow: 2,
            border: 2,
            borderColor: 'primary.light',
            '& .MuiDataGrid-cell:hover': {
              color: 'primary.main',
            },
          }} >
        </DataGrid>
      </Box>
    </div>
  );
}