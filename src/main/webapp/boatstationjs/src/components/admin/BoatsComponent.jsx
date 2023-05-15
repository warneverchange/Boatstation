import { Fragment, useRef, useState } from "react"
import { useFetching } from "../../hooks/useFetching";
import { Watercraft } from "../../queries/Watercraft";
import { Water } from "../../queries/Water";
import { WatercraftLog } from "../../queries/WatercraftLog";
import { TableQuery } from "../helpers/TableQuery";
import { Button, IconButton } from "@mui/material";
import DialogForm from "../helpers/DialogForm";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { getColumnByColumnName } from "../../utils/DataGridUtils";
import { getFieldIdFromData } from "../../utils/DataGridUtils";
import { ListDialog } from "../helpers/ListDialog";
import LifeSavingDevices from "../../queries/LifeSavingDevices";
import { KeyboardBackspaceOutlined } from "@mui/icons-material";
import SupportIcon from '@mui/icons-material/Support';




export const BoatsComponent = () => {
    const dialogRef = useRef();
    const talbeRef = useRef();
    const listDialogRef = useRef();
    const [waterTypeData, setWaterTypeData] = useState([]);
    const [waterData, setWaterData] = useState([]);
    const [brandData, setBrandData] = useState([]);
    const [modelData, setModelData] = useState([]);
    const [watercraftData, setWatercraftData] = useState([]);
    const [technicalConditionData, setTechnicalConditionData] = useState([]);
    const [lifeSavingDevices, setLifeSavingDevices] = useState([]);
    const [freeLifeSavingDevices, setFreeLifeSavingDevices] = useState([]);

    const [fetchFreeLifeSavingDevices, isFreeLifeSavingDevicesLoading, freeLifeSavingDevicesLoadingErr] = useFetching(async () => {
        const response = await LifeSavingDevices.getFreeLifeSavingDevices();
        setFreeLifeSavingDevices(response.data);
    })

    const [fetchLifeSavingDevices, isLifeSavingDevicesLoading, lifeSavingDevicesLoadingErr] = useFetching(async () => {
        const response = await LifeSavingDevices.getLifeSavingDevices();
        setLifeSavingDevices(response.data);
    })

    const [fetchWaterTypeData, isWaterTypeDataLoading, waterTypeErr] = useFetching(async () => {
        const response = await Water.getWaterTypes();
        setWaterTypeData(response.data);
    });

    const [fetchWaterData, isWaterDataLoading, waterErr] = useFetching(async () => {
        const response = await Water.getWaters();
        setWaterData(response.data);
    })

    const [fetchBrandData, isBrandDataLoding, brandDataErr] = useFetching(async () => {
        const response = await Watercraft.getBrands();
        setBrandData(response.data);
    })

    const [fetchModelData, isModelDataLoading, modelDataErr] = useFetching(async () => {
        const response = await Watercraft.getModels();
        setModelData(response.data);
    })

    const [fetchWatercraftData, isWatercraftDataLoading, watercraftDataErr] = useFetching(async () => {
        const response = await Watercraft.getWatercraft();
        setWatercraftData(response.data);
    })

    const [fetchTechnicalConditionData, isTechnicalConditionLoading, technicalConditionErr] = useFetching(async () => {
        const response = await Watercraft.getTechnicalConditions();
        setTechnicalConditionData(response.data);
    })

    const [watercraftLogId, setWatercraftLogId] = useState('');
    const [waterTypeId, setWaterTypeId] = useState('');
    const [waterId, setWaterId] = useState('');
    const [brandId, setBrandId] = useState('');
    const [modelId, setModelId] = useState('');
    const [watercraftId, setWatercraftId] = useState('')
    const [technicalConditionId, setTechnicalConditionId] = useState('');
    const [watercraftNumber, setWatercraftNumber] = useState('');

    const onSubmit = async (event, reason) => {
        if (waterId === '' || technicalConditionId === '' || watercraftId === '' || watercraftNumber === '') return;
        event.preventDefault();
        try {
            const response = await WatercraftLog.createOrUpdateWatercraftLog({
                id: watercraftLogId,
                watercraftNumber: watercraftNumber,
                waterId: waterId,
                technicalConditionId: technicalConditionId,
                watercraftId: watercraftId
            })
            talbeRef.current.updateTable();
            dialogRef.current.setOpen(false);
            dialogRef.current.clear();
        } catch (err) {
            dialogRef.current.setError(err.response)
        }
    }

    useState(() => {
        fetchBrandData();
        fetchModelData();
        fetchTechnicalConditionData();
        fetchWaterTypeData();
        fetchWaterData();
        fetchWatercraftData()
        fetchLifeSavingDevices();
        fetchFreeLifeSavingDevices();
    }, [])




    return (
        <Fragment>
            <TableQuery
                ref={talbeRef}
                callback={Watercraft.getWatercraftView}
                customColumns={{
                    technicalCondition: {
                        editable: true,
                        type: 'singleSelect',
                        valueOptions: getColumnByColumnName(technicalConditionData, "name") //getTechnicalConditionNames(technicalConditionData)
                    },
                    editButton: {
                        fieldName: "editBtn",
                        renderCell: (params) => {
                            return (
                                <IconButton onClick={(event) => {
                                    setWatercraftLogId(params.row.id);
                                    setWaterTypeId(getFieldIdFromData(waterTypeData, "name", params.row.waterType))
                                    setWaterId(getFieldIdFromData(waterData, "name", params.row.waterName))
                                    setBrandId(getFieldIdFromData(brandData, "name", params.row.brandName))
                                    setModelId(getFieldIdFromData(modelData, "name", params.row.modelName))
                                    setWatercraftId(getFieldIdFromData(watercraftData, "issueYear", params.row.issueYear))
                                    setTechnicalConditionId(getFieldIdFromData(technicalConditionData, "name", params.row.technicalCondition))
                                    setWatercraftNumber(params.row.watercraftNumber)
                                    dialogRef.current.setOpen(true)
                                }}>
                                    <EditIcon />
                                </IconButton>
                            )
                        }
                    },

                    deleteButton: {
                        fieldName: "delBtn",
                        renderCell: (params) => {
                            return (
                                <IconButton onClick={async () => {
                                    await WatercraftLog.deleteWatercraftLog(params.row.id)
                                    talbeRef.current.updateTable();
                                }}>
                                    <DeleteIcon />
                                </IconButton>
                            )
                        }
                    },
                    lsDeviceButton: {
                        fieldName: "lsDeviceBtn",
                        renderCell: (params) => {
                            return (
                                <IconButton onClick={()=>{
                                    setWatercraftLogId(params.row.id);
                                    listDialogRef.current.setOpen(true);
                                }}>
                                    <SupportIcon/>
                                </IconButton>
                            )
                        }
                    }
                }}
                processRowUpdate={(newRow, oldRow) => {
                    const updatedRow = { ...newRow, isNew: false }
                    if (newRow.technicalCondition !== oldRow.technicalCondition) {
                        WatercraftLog.updateTechnicalCondition(
                            newRow.id,
                            getFieldIdFromData(technicalConditionData, "name", newRow.technicalCondition)
                        )
                    }

                    return updatedRow;
                }}


                onProcessRowUpdateError={(err) => {

                }}
            >

            </TableQuery>

            {/* const [waterTypeId, setWaterTypeId] = useState('');
    const [waterId, setWaterId] = useState('');
    const [brandId, setBrandId] = useState('');
    const [modelId, setModelId] = useState('');
    const [watercraftId, setWatercraftId] = useState('')
    const [technicalConditionId, setTechnicalConditionId] = useState('');
    const [watercraftNumber, setWatercraftNumber] = useState(''); */}

            <Button onClick={() => { dialogRef.current.setOpen(true) }} variant="contained">Create new watercraft log</Button>
            <DialogForm
                ref={dialogRef}
                onClose={() => {
                    setWatercraftLogId('')
                    setWaterTypeId('')
                    setWaterId('');
                    setBrandId('')
                    setModelId('')
                    setWatercraftId('')
                    setTechnicalConditionId('')
                    setWatercraftNumber('')
                }}
                onSubmit={onSubmit}
                title="Enter watercraft log data"
                dialogElements={
                    {
                        elements: [
                            {
                                type: "selector",
                                name: "Water type",
                                value: waterTypeId,
                                getOptions: () => { return waterTypeData },
                                showOption: (obj) => { return obj.name },
                                getValue: (obj) => { return obj.id },
                                onChange: (event) => { setWaterTypeId(Number(event.target.value) || '') },
                                required: true
                            },
                            {
                                type: "selector",
                                name: "Water",
                                value: waterId,
                                getOptions: () => { return waterData.filter((obj) => { return obj.waterTypeId === waterTypeId }) },
                                showOption: (obj) => { return obj.name },
                                getValue: (obj) => { return obj.id },
                                onChange: (event) => { setWaterId(Number(event.target.value) || '') },
                                isDisabled: () => { return waterTypeId === '' },
                                required: true
                            },
                            {
                                type: "selector",
                                name: "Brand",
                                value: brandId,
                                getOptions: () => { return brandData },
                                showOption: (obj) => { return obj.name },
                                getValue: (obj) => { return obj.id },
                                onChange: (event) => { setBrandId(Number(event.target.value) || '') },
                                required: true
                            },
                            {
                                type: "selector",
                                name: "Model",
                                value: modelId,
                                getOptions: () => { return modelData.filter((obj) => { return obj.brandId === brandId }) },
                                showOption: (obj) => { return obj.name },
                                getValue: (obj) => { return obj.id },
                                onChange: (event) => { setModelId(Number(event.target.value) || '') },
                                isDisabled: () => { return brandId === '' },
                                required: true
                            },
                            {
                                type: "selector",
                                name: "Year",
                                value: watercraftId,
                                getOptions: () => { return watercraftData.filter((obj) => { return obj.modelId === modelId }) },
                                showOption: (obj) => { return obj.issueYear },
                                getValue: (obj) => { return obj.id },
                                onChange: (event) => { setWatercraftId(Number(event.target.value) || '') },
                                isDisabled: () => { return modelId === '' },
                                required: true
                            },
                            {
                                type: "selector",
                                name: "Technical condition",
                                value: technicalConditionId,
                                getOptions: () => { return technicalConditionData },
                                showOption: (obj) => { return obj.name },
                                getValue: (obj) => { return obj.id },
                                onChange: (event) => { setTechnicalConditionId(Number(event.target.value) || '') },
                                required: true
                            },
                            {
                                type: "textField",
                                name: "Watercraft number: ",
                                value: watercraftNumber,
                                onChange: (event) => { setWatercraftNumber(event.target.value) },
                                required: true
                            }
                        ]
                    }
                }
            >

            </DialogForm>

            <ListDialog
                ref={listDialogRef}
                title="Life saving devices"
                getItems={() => {
                    return lifeSavingDevices.filter((item) => item.watercraftLogId === watercraftLogId);
                }}
                getAllItems={() => {
                    return freeLifeSavingDevices;
                }}
                deleteItem={async (event) => {
                    const lifeSavingDeviceLogId = event.target.id;
                    console.log(event)
                    await LifeSavingDevices.deleteLifeSavingDeviceLog(lifeSavingDeviceLogId);
                    fetchLifeSavingDevices();
                    fetchFreeLifeSavingDevices();
                }}
                showListItem={(item) => {
                    return item.id + " " + item.lifeSavingDeviceType;
                }}
                getListItemId={(item) => {
                    return item._id;
                }}
                showSelectorItem={(item) => {
                    return item.id + " " + item.name;
                }}
                getSelectorItemId={(item) => {
                    return item._id
                }}
                addItem={async (event) => {
                    const lifeSavingDeviceId = event.target.id;
                    await LifeSavingDevices.addLifeSavingDeviceLog(lifeSavingDeviceId, watercraftLogId);
                    fetchLifeSavingDevices();
                    fetchFreeLifeSavingDevices();
                }}
                onClose={() => {
                    listDialogRef.current.setOpen(false);
                    setWatercraftLogId('')
                }}
            >
            </ListDialog>
        </Fragment>
    )
}