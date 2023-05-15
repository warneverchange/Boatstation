import React, { useEffect, useImperativeHandle, useRef, useState } from 'react'
import { useFetching } from '../../hooks/useFetching';
import { Table } from './Table';

export const TableQuery = React.forwardRef((props, ref) => {
  const dataFetchedRef = useRef(false)
  const [data, setData] = useState([])
  const [fetchData, isDataLoading, dataFetchError] = useFetching(async () => {
    const response = await props.callback()
    setData(response)
  })
  useImperativeHandle(ref, () => ({
    updateTable: async () => {
      await props.callback().then(newData => {
        setData(newData)
      })
    },
    getData: () => {
      return data;
    }
  }));
  useEffect(() => {
    if (dataFetchedRef.current) {
      return
    }
    dataFetchedRef.current = true
    fetchData()
  })
  return (
    <Table
      editable={props.editable ?? false}
      getRowsIdCallback={props.getRowsIdCallback}
      onProcessRowUpdateError={props.onProcessRowUpdateError}
      processRowUpdate={props.processRowUpdate}
      renderCells={props.renderCells}
      customColumns={props.customColumns}
      data={data}
      widthComponents={props.widthComponents}>
    </Table>
  )
})