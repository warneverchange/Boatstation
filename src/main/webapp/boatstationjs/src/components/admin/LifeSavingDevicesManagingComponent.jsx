import { useEffect, useRef, useState } from "react"
import { TableQuery } from "../helpers/TableQuery"
import { Fragment } from "react";
import { useFetching } from "../../hooks/useFetching";
import LifeSavingDevices from "../../queries/LifeSavingDevices";
import { Button, Container, Grid } from "@mui/material";
import DialogForm from "../helpers/DialogForm";

export const LifeSavingDevicesManagingComponent = () => {
    const tableRef = useRef();
    const dialogRef = useRef();

    const [lsdType, setLsdType] = useState([]);
    const [fetchLsdType, lsdTypeLoading, errLoadingLsdType] = useFetching(async () => {
        const response = await LifeSavingDevices.getLsdTypes();
        setLsdType(response.data)
    })

    useEffect(() => {
        fetchLsdType();
    }, [])

    const [selectedLsdTypeId, setSelectedLsdTypeId] = useState('');

    const onSubmit = async (event) => {
        event.preventDefault();

        const response = await LifeSavingDevices.createNewLifeSavingDevice(selectedLsdTypeId);

        tableRef.current.updateTable();
        dialogRef.current.setOpen(false);
    }

    const onClose = () => {
        setSelectedLsdTypeId('');
    }

    return (
        <Fragment>
            <Container>
                <Grid
                    container
                    direction="column"
                    justifyContent="center"
                    alignItems="center"
                >
                    <Grid item sx={{width: 700, mt: 15}}>
                        <TableQuery
                            ref={tableRef}
                            getRowsIdCallback={(obj) => obj._id}
                            callback={LifeSavingDevices.getAllLifeSavingDevices}
                            widthComponents={{
                                name: 400
                            }}
                            proccessRowUpdate={(newRow, oldRow) => {
                                const updatedRow = { ...newRow, isNew: true }
                                return updatedRow
                            }}
                        >
                        </TableQuery>
                    </Grid>

                    <Button variant="contained" sx={{ mt: 5 }} onClick={() => {
                        dialogRef.current.setOpen(true);
                    }}>
                        Add life saving device
                    </Button>

                    <DialogForm
                        ref={dialogRef}
                        onClose={onClose}
                        onSubmit={onSubmit}
                        title="Select life saving device type"
                        dialogElements={{
                            elements: [
                                {
                                    type: "selector",
                                    name: "Life saving device type",
                                    value: selectedLsdTypeId,
                                    getOptions: () => { return lsdType },
                                    showOption: (option) => { return option.name },
                                    getValue: (option) => { return option.id },
                                    onChange: (event) => setSelectedLsdTypeId(event.target.value),
                                    required: true
                                }
                            ]
                        }}
                    >

                    </DialogForm>
                </Grid>
            </Container>

        </Fragment>
    )
}