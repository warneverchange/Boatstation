import { Fragment, useEffect, useRef, useState } from "react"
import { TableQuery } from "../helpers/TableQuery";
import { Briefing } from "../../queries/Briefing";
import { useFetching } from "../../hooks/useFetching";
import dateFormat from "dateformat";
import { IconButton } from "@mui/material";
import DownloadIcon from '@mui/icons-material/Download';
import { downloadFile } from "../../utils/FileUtils";


export const ReportComponent = () => {
    const tableRef = useRef();

    const [reportedBriefings, setReportedBriefings] = useState([])
    const [fetchReportedBriefings, reportedBriefingsLoading, errReportedBriefingsLoading] = useFetching(async () => {
        const response = await Briefing.getReportedBriefings();
        setReportedBriefings(response.data);
    })

    useEffect(() => {
        fetchReportedBriefings();
    }, [])

    return (
        <Fragment>
            <TableQuery
                ref={tableRef}
                getRowsIdCallback={(obj) => obj.id}
                callback={Briefing.getBriefings}
                customColumns={{
                    downloadBtn: {
                        fieldName: "downloadBtn",
                        renderCell: (props) => {
                            
                            console.log("-----debug reported briefings ----")
                            console.log(reportedBriefings)
                            if (reportedBriefings.includes(props.row.id)) {
                                
                                return (
                                    <IconButton onClick={async () => {
                                        
                                        const reportDownloadReponse = await Briefing.downloadReport(props.row.id);
                                        downloadFile(reportDownloadReponse.data, "report.pdf")
                                    }}>
                                        <DownloadIcon/>
                                    </IconButton>
                                )
                            }
                        }
                    }
                }}
                renderCells={{
                    date: (params) => {
                        return dateFormat(new Date(params.row.date), "dd-mm-yyyy HH:MM")
                    },
                }}
            />
        </Fragment>
    )
}