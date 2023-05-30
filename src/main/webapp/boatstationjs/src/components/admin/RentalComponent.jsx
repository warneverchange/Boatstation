import { Fragment, useEffect, useMemo, useState } from "react";
import { useFetching } from "../../hooks/useFetching";
import Rental from "../../queries/Rental";
import DataGridUtils from "../../utils/DataGridUtils";
import { useRef } from "react";
import { TableQuery } from "../helpers/TableQuery";
import { Container, IconButton, MenuItem, Select } from "@mui/material";
import dateFormat from "dateformat";
import SummarizeIcon from '@mui/icons-material/Summarize';
import DialogForm from "../helpers/DialogForm";
import { Employees } from "../../queries/Employees";
import { Briefing } from "../../queries/Briefing";
import { Client } from "../../queries/Client";
import CloudUploadIcon from '@mui/icons-material/CloudUpload';
import DownloadIcon from '@mui/icons-material/Download';
import { downloadFile } from "../../utils/FileUtils";
import { Grid } from "@mui/material";
const PizZip = require("pizzip");
const Docxtemplater = require("docxtemplater");




export const RentalComponent = () => {
    const [rentalStatuses, setRentalStatuses] = useState([])
    const [employees, setEmployees] = useState([])
    const [briefingTypes, setBriefingTypes] = useState([])
    const [selectedRentalLogId, setSelectedRentalLogId] = useState('')
    const [selectedBriefingTypeId, setSelectedBriefingTypeId] = useState('');
    const [selectedEmployeeId, setSelectedEmployeeId] = useState('');


    const tableRef = useRef();
    const dialogRef = useRef();

    const [reportedBriefings, setReportedBriefings] = useState([])
    const [fetchReportedBriefings, reportedBriefingsLoading, errReportedBriefingsLoading] = useFetching(async () => {
        const response = await Briefing.getReportedBriefings();
        setReportedBriefings(response.data);
    })

    const [fetchBriefingTypes, isBriefingTypesLoading, briefingTypesLoadingErr] = useFetching(async () => {
        const response = await Briefing.getBriefingTypes();
        setBriefingTypes(response.data);
    })

    const [fetchRentalStatuses, isRentalStatusesLoading, errLoadingRentalStatuses] = useFetching(async () => {
        const response = await Rental.getAllRentalStatuses();
        setRentalStatuses(response.data)
    })

    const [fetchEmployees, isEmployeesLoading, errEmployeesLoading] = useFetching(async () => {
        const response = await Employees.getEmployees();
        setEmployees(response.data);
    })

    useEffect(() => {
        fetchRentalStatuses()
        fetchEmployees();
        fetchBriefingTypes();
        fetchReportedBriefings();
    }, [])


    const getRentalStatusIdByName = (rentalStatusName) => {
        let rentalStatusId;
        rentalStatuses?.forEach(({ id, name }) => {
            if (name === rentalStatusName) {
                rentalStatusId = id;
            }
        })
        return rentalStatusId
    }

    const getRentalStatusNames = (rentalStatuses) => {
        const result = [];
        rentalStatuses?.forEach(({ id, name }) => {
            result.push(name)
        })
        return result
    }





    const onReportDialogSubmit = async (event) => {
        event.preventDefault();
        const _template = await Briefing.downloadReportTemplate();
        const _employee = await Client.getClientDataById(selectedEmployeeId);
        const _client = await Rental.getClientDataByRentalLogId(selectedRentalLogId);

        console.log(await _template.data.arrayBuffer())


        await Briefing.createBriefingLog({
            briefingTypeId: selectedBriefingTypeId,
            rentalLogId: selectedRentalLogId,
            clientDataId: selectedEmployeeId
        })
        tableRef.current.updateTable();


        const zip = new PizZip(await _template.data.arrayBuffer());

        const doc = new Docxtemplater(zip, {
            paragraphLoop: true,
            linebreaks: true,
        });

        doc.render({
            iLastName: _employee.data["lastName"],
            iFirstName: _employee.data["firstName"],
            iPatronymic: _employee.data["patronymic"],
            iPassportData: _employee.data["passportData"],
            iPhoneNumber: _employee.data["phoneNumber"],
            lastName: _client.data["lastName"],
            firstName: _client.data["firstName"],
            patronymic: _client.data["patronymic"],
            passportData: _client.data["passportData"],
            phoneNumber: _client.data["phoneNumber"],
            date: ""
        })
        const buf = doc.getZip().generate({
            type: "blob",
            mimeType:
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
        });
        downloadFile(buf, "report.docx");

        dialogRef.current.setOpen(false);
    }

    return (

        <Fragment>
            <Container>
                <Grid
                    container
                    direction="row"
                    justifyContent="center"
                    alignItems="center"
                    sx={{ mt: 15 }}
                >
                    <Grid item xs={12}>
                        <TableQuery
                            ref={tableRef}
                            getRowsIdCallback={(obj) => { return obj.id }}
                            callback={Rental.getAllRentalData}
                            customColumns={{
                                // rentalStatus: {
                                //     editable: true,
                                //     type: 'singleSelect',
                                //     valueOptions: getRentalStatusNames(rentalStatuses)
                                // },

                                createReportBtn: {
                                    fieldName: "createReportButton",
                                    editable: true,
                                    renderCell: (props) => {
                                        if (props.row.rentalStatus === 'Бронь') {
                                            return (
                                                <IconButton onClick={() => {
                                                    setSelectedRentalLogId(props.row.id);
                                                    dialogRef.current.setOpen(true);
                                                }}>

                                                    <SummarizeIcon />
                                                </IconButton>
                                            )
                                        } else if (props.row.rentalStatus === 'Инструктаж') {
                                            return (
                                                <IconButton component="label">
                                                    <input hidden accept="application/pdf" type="file" onChange={async (event) => {
                                                        console.log("upload btn pressed")
                                                        const file = event.target.files[0];
                                                        const briefingLogIdResponse = await Briefing.getBriefingLogIdByRentalLogId(props.row.id);
                                                        await Briefing.uploadReport(briefingLogIdResponse.data, file);
                                                        tableRef.current.updateTable();
                                                    }} />
                                                    <CloudUploadIcon />
                                                </IconButton>
                                            )
                                        } else {
                                            return (
                                                <IconButton onClick={async () => {
                                                    const briefingLogIdResponse = await Briefing.getBriefingLogIdByRentalLogId(props.row.id);
                                                    const reportDownloadReponse = await Briefing.downloadReport(briefingLogIdResponse.data);
                                                    downloadFile(reportDownloadReponse.data, "data.pdf")
                                                }}>
                                                    <DownloadIcon />
                                                </IconButton>
                                            )

                                        }

                                    }
                                }
                            }}

                            widthComponents={{
                                id: 50,
                                dateFrom: 170
                            }}

                            renderCells={{
                                dateFrom: (params) => {
                                    return dateFormat(new Date(params.row.dateFrom), "dd-mm-yyyy HH:MM")
                                },
                                dateTo: (params) => {
                                    return dateFormat(new Date(params.row.dateTo), "dd-mm-yyyy HH:MM")
                                }
                            }}
                            processRowUpdate={(newRow, oldRow) => {
                                const updatedRow = { ...newRow, isNew: false };
                                Rental.updateRentalStatus(newRow.id, getRentalStatusIdByName(newRow.rentalStatus))
                                return updatedRow
                            }}
                            onProcessRowUpdateError={(err) => {
                                console.log(err)
                            }}>
                        </TableQuery>
                    </Grid>



                    <DialogForm
                        ref={dialogRef}
                        onClose={() => {
                            setSelectedEmployeeId('');
                            setSelectedBriefingTypeId('');
                            setSelectedRentalLogId('');
                        }}
                        onSubmit={onReportDialogSubmit}
                        title="Report details"
                        dialogElements={{
                            elements: [

                                {
                                    type: "selector",
                                    name: "Briefing type",
                                    value: selectedBriefingTypeId,
                                    getOptions: () => { return briefingTypes },
                                    showOption: (option) => { return option.name },
                                    getValue: (option) => { return option.id },
                                    onChange: (event) => { setSelectedBriefingTypeId(event.target.value) },
                                    required: true
                                },
                                {
                                    type: "selector",
                                    name: "Employee",
                                    value: selectedEmployeeId,
                                    getOptions: () => { return employees.filter(empl => empl.jobTitle === 'Инструктор') },
                                    showOption: (option) => { return option.firstName + " " + option.lastName + " " + option.patronymic + " " + option.phoneNumber },
                                    getValue: (option) => { return option.clientDataId },
                                    onChange: (event) => { setSelectedEmployeeId(event.target.value) },
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