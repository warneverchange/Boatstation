import { Fragment, useEffect, useRef, useState } from "react"
import { useFetching } from "../../hooks/useFetching";
import { Employees } from "../../queries/Employees";
import { TableQuery } from "../helpers/TableQuery";
import { Client } from "../../queries/Client";
import { Button, Container, Grid, IconButton } from "@mui/material";
import DialogForm from "../helpers/DialogForm";
import PersonAddAlt1Icon from '@mui/icons-material/PersonAddAlt1';
import BlockIcon from '@mui/icons-material/Block';


export const ClientsComponent = () => {
    const [employees, setEmployees] = useState([]);
    const [jobTitles, setJobTitles] = useState([])
    const [selectdClientId, setSelectedClientId] = useState('')
    const [selectedJobTitleId, setSelectedJobTitleId] = useState('')
    const [updateTable, setUpdateTable] = useState(true)

    const dialogRef = useRef();
    const tableRef = useRef();


    const [fetchEmployees, isEmployeesLoading, errEmployeesLoading] = useFetching(async () => {
        const response = await Employees.getEmployees();
        setEmployees(response.data)
    })

    const [fetchJobTitles, isJobTitlesLoading, errJobTitlesLoading] = useFetching(async () => {
        const response = await Employees.getEmployeesJobTitles();
        setJobTitles(response.data);
    })



    useEffect(() => {
        fetchEmployees();
        fetchJobTitles();
    }, [updateTable])


    const onAddEmployeeHandler = async (event) => {
        event.preventDefault();
        const response = await Employees.addNewEmployee(selectdClientId, selectedJobTitleId);
        tableRef.current.updateTable();
        setUpdateTable(!updateTable);
        dialogRef.current.setOpen(false)
    }

    const onCloseDialog = () => {
        setSelectedClientId('');
        setSelectedJobTitleId('')
    }



    return (
        <Fragment>
            <Container>
                <Grid
                    container
                    direction="row"
                    justifyContent="center"
                    alignItems="center"
                    sx={{mt: 15}}
                >
                    <Grid item>
                        <TableQuery
                            ref={tableRef}
                            getRowsIdCallback={(obj) => { return obj.id }}
                            callback={Client.getAllClients}
                            customColumns={{
                                isEmployee: {
                                    fieldName: "isEmployee",
                                    renderCell: (props) => {
                                        if (employees.map((obj) => obj.clientDataId).includes(props.row.id)) {
                                            return (
                                                <IconButton onClick={async (event) => {
                                                    event.preventDefault();
                                                    const response = await Employees.fireEmployee(props.row.id)
                                                    tableRef.current.updateTable();
                                                    setUpdateTable(!updateTable);
                                                }}>
                                                    <BlockIcon />
                                                </IconButton>
                                            )
                                        } else {
                                            return (
                                                <IconButton onClick={async () => {
                                                    setSelectedClientId(props.row.id)
                                                    dialogRef.current.setOpen(true);
                                                }}>
                                                    <PersonAddAlt1Icon />
                                                </IconButton>
                                            )
                                        }
                                    },
                                }

                            }}

                            proccessRowUpdate={(newRow, oldRow) => {
                                const updatedRow = { ...newRow, isNew: true }
                                return updatedRow
                            }}
                        >

                        </TableQuery>
                    </Grid>

                    <DialogForm
                        ref={dialogRef}
                        onClose={onCloseDialog}
                        onSubmit={onAddEmployeeHandler}
                        title="Select job title"
                        dialogElements={{
                            elements: [
                                {
                                    type: "selector",
                                    name: "Job title",
                                    value: selectedJobTitleId,
                                    getOptions: () => { return jobTitles },
                                    showOption: (option) => { return option.name },
                                    getValue: (option) => { return option.id },
                                    onChange: (event) => { setSelectedJobTitleId(event.target.value) },
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