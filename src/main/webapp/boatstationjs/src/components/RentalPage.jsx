import { Button, Container, Dialog, FormControl, Grid, InputLabel, MenuItem, Paper, Select, TextField, Toolbar, Typography } from "@mui/material"
import { Fragment, useEffect, useState } from "react"
import { useFetching } from "../hooks/useFetching";
import { Watercraft } from "../queries/Watercraft";
import { Water } from "../queries/Water";
import { getColumnByColumnName } from "../utils/DataGridUtils";
import dayjs from "dayjs";
import { MobileDateTimePicker } from "@mui/x-date-pickers";
import { LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import Rental from "../queries/Rental";
import { FormComponent } from "./helpers/FormComponent";
import { filterByParams } from "../utils/FilterUtils";
import { isInAnyInterval } from "../utils/DateTimesUtils";
import { isRangeIn } from "../utils/DateTimesUtils";
import bgVideo from "../videos/rental.mp4"

export const RentalPage = () => {
    const [waterType, setWaterType] = useState([])
    const [water, setWater] = useState([])
    const [brand, setBrand] = useState([])
    const [model, setModel] = useState([])
    const [watercraft, setWatercraft] = useState([])
    const [freeWatercrafts, setFreeWatercrafs] = useState([]);
    const [watercraftNumbers, setWatercraftNumbers] = useState([]);
    const [bookedDateTimes, setBookedDateTimes] = useState([]);
    const [dateFrom, setDateFrom] = useState('')
    const [dateTo, setDateTo] = useState('')


    const [fetchBookedDateTimes, isBookedDateTimesLoading, bookeDateTimesLoadingErr] = useFetching(async () => {
        const response = await Rental.getBookedDatetimes();
        setBookedDateTimes(response.data);
    })

    const [fetchWaterType, isWaterTypeLoading, waterTypeLoadingErr] = useFetching(async () => {
        const response = await Water.getWaterTypes();
        setWaterType(response.data);
    })

    const [fetchWater, isWaterLoading, waterLoadingErr] = useFetching(async () => {
        const response = await Water.getWaters();
        setWater(response.data);
    })

    const [fetchBrand, isBrandLoading, brandLoadingErr] = useFetching(async () => {
        const response = await Watercraft.getBrands();
        setBrand(response.data)
    })

    const [fetchModel, isModelLoading, modelLoadingErr] = useFetching(async () => {
        const response = await Watercraft.getModels();
        setModel(response.data);
    })

    const [fetchWatercraft, isWatercraftLoading, watercraftLoadingErr] = useFetching(async () => {
        const response = await Watercraft.getWatercraft();
        setWatercraft(response.data)
    })

    const [fetchFreeWatercrafts, isFreeWatercraftsLoading, freeWatercraftsLoadingErr] = useFetching(async () => {
        const response = await Watercraft.getFreeWatercrafts();
        setFreeWatercrafs(response.data);
    })

    const [fetchWatercraftNumbers, isWatercraftNumbersLoading, watercraftNumbersLoadingErr] = useFetching(async () => {
        const response = await Watercraft.getWatercraftNumbers();
        setWatercraftNumbers(response.data);
    })

    const [waterTypeId, setWaterTypeId] = useState('')
    const [waterId, setWaterId] = useState('')
    const [brandId, setBrandId] = useState('')
    const [modelId, setModelId] = useState('')
    const [watercraftId, setWatercraftId] = useState('')
    const [watercraftLogId, setWatercraftLogId] = useState('')






    useState(() => {
        fetchWaterType();
        fetchWater();
        fetchBrand();
        fetchModel();
        fetchWatercraft();
        fetchWatercraftNumbers();
        fetchFreeWatercrafts();
        fetchBookedDateTimes();
    }, [])



    const onSubmit = () => {
        console.log(dateFrom);
        console.log(dateTo);
        if (dateFrom < dateTo && !isRangeIn(
            filterByParams(
                bookedDateTimes,
                {
                    name: "watercraftLogId",
                    value: watercraftLogId
                }
            ),
            dateFrom,
            dateTo)) {


            console.log("Enter in condition")
            const response = Rental.createNewRentalLog({
                dateFrom: dateFrom,
                dateTo: dateTo,
                watercraftLogId: watercraftLogId
            })
        }

    }

    return (
        <div className="main">
            <video src={bgVideo} autoPlay loop muted />
            <div className="content">

                <Container>
                    <Fragment>

                        <Toolbar />
                        <FormComponent
                            title="Enter rental data"

                            // type,
                            // name,
                            // value,
                            // getOptions,
                            // showOption,
                            // getValue,
                            // onChange,
                            // required 
                            elements={[
                                {
                                    type: "selector",
                                    value: waterTypeId,
                                    name: "Water type",
                                    getOptions: () => {
                                        return waterType.filter(
                                            (option) => {
                                                console.log(option.id)
                                                console.log("Free watercrafts")
                                                console.log(getColumnByColumnName(freeWatercrafts, "waterTypeId"))
                                                console.log(freeWatercrafts)
                                                return getColumnByColumnName(freeWatercrafts, "waterTypeId").includes(option.id)
                                            })
                                    },
                                    showOption: (option) => { return option.name },
                                    getValue: (option) => { return option.id },
                                    required: true,
                                    onChange: (event) => { setWaterTypeId(event.target.value) }
                                },
                                {
                                    type: "selector",
                                    value: waterId,
                                    name: "Water",
                                    getOptions: () => {
                                        return water.filter((option) =>
                                            option.waterTypeId === waterTypeId &&
                                            getColumnByColumnName(freeWatercrafts, "waterId").includes(option.id));
                                    },
                                    showOption: (option) => { return option.name },
                                    getValue: (option) => { return option.id },
                                    required: true,
                                    onChange: (event) => { setWaterId(event.target.value) }
                                },
                                {
                                    type: "selector",
                                    value: brandId,
                                    name: "Brand",
                                    getOptions: () => {
                                        return brand.filter((option) =>
                                            getColumnByColumnName(
                                                filterByParams(
                                                    freeWatercrafts,
                                                    { name: "waterTypeId", value: waterTypeId },
                                                    { name: "waterId", value: waterId }),
                                                "brandId")
                                                .includes(option.id))
                                    },
                                    showOption: (option) => option.name,
                                    getValue: (option) => option.id,
                                    required: true,
                                    onChange: (event) => setBrandId(event.target.value)
                                },
                                {
                                    type: "selector",
                                    value: modelId,
                                    name: "Model",
                                    getOptions: () => model.filter((option) => option.brandId === brandId &&
                                        getColumnByColumnName(
                                            filterByParams(
                                                freeWatercrafts,
                                                { name: "waterTypeId", value: waterTypeId },
                                                { name: "waterId", value: waterId }),
                                            "modelId")
                                            .includes(option.id)),
                                    showOption: (option) => option.name,
                                    getValue: (option) => option.id,
                                    required: true,
                                    onChange: (event) => setModelId(event.target.value)
                                },
                                {
                                    type: "selector",
                                    value: watercraftId,
                                    name: "Year",
                                    getOptions: () => watercraft.filter((option) =>
                                        option.modelId === modelId &&
                                        getColumnByColumnName(freeWatercrafts, "watercraftId")
                                            .includes(option.id)),
                                    showOption: (option) => option.issueYear,
                                    getValue: (option) => option.id,
                                    required: true,
                                    onChange: (event) => setWatercraftId(event.target.value)
                                },
                                {
                                    type: "selector",
                                    value: watercraftLogId,
                                    name: "Watercraft number",
                                    getOptions: () => watercraftNumbers.filter((option) =>
                                        getColumnByColumnName(
                                            filterByParams(
                                                freeWatercrafts,
                                                { name: "watercraftId", value: watercraftId },
                                                { name: "waterId", value: waterId }),
                                            "id")
                                            .includes(option.id)),
                                    showOption: (option) => option.watercraftNumber,
                                    getValue: (option) => option.id,
                                    require: true,
                                    onChange: (event) => setWatercraftLogId(event.target.value)
                                }
                            ]}
                        >

                        </FormComponent>
                        <Grid container
                            direction="column"
                            justifyContent="center"
                            alignItems="center"
                            marginTop={1}
                            spacing={2}>

                            <LocalizationProvider dateAdapter={AdapterDayjs}>
                                <Grid item>
                                    <MobileDateTimePicker
                                        sx={{ '& input': {color: 'white'},   '& .MuiOutlinedInput-root': {
                                            '& fieldset': {
                                              borderColor: 'white',
                                            },
                                            '&:hover fieldset': {
                                              borderColor: 'white',
                                            },
                                            '&.Mui-focused fieldset': {
                                              borderColor: 'white',
                                            },
                                          },}}
                                        onChange={(value, context) => {
                                            const selectedDateTime = dayjs(value.$d).valueOf();
                                            if (!isInAnyInterval(
                                                filterByParams(
                                                    bookedDateTimes,
                                                    {
                                                        name: "watercraftLogId",
                                                        value: watercraftLogId
                                                    }
                                                ),
                                                selectedDateTime)) {
                                                setDateFrom(selectedDateTime)
                                            } else {
                                            }
                                        }}
                                        ampm={false}
                                        views={['day', 'hours', 'minutes']}
                                        disablePast defaultValue={dayjs()}
                                        shouldDisableTime={(value, view) => {
                                            const selectedDateTime = dayjs(value.$d).valueOf();
                                            return isInAnyInterval(
                                                filterByParams(
                                                    bookedDateTimes,
                                                    {
                                                        name: "watercraftLogId",
                                                        value: watercraftLogId
                                                    }
                                                ),
                                                selectedDateTime
                                            )
                                        }} />
                                </Grid>
                                <Grid item>
                                    <MobileDateTimePicker
                                        onChange={(value, context) => {
                                            const selectedDateTime = dayjs(value.$d).valueOf();
                                            if (!isInAnyInterval(
                                                filterByParams(
                                                    bookedDateTimes,
                                                    {
                                                        name: "watercraftLogId",
                                                        value: watercraftLogId
                                                    }
                                                ),
                                                selectedDateTime)) {
                                                setDateTo(selectedDateTime)
                                            } else {
                                            }
                                        }}
                                        sx={{ '& input': {color: 'white'},  '& .MuiOutlinedInput-root': {
                                            '& fieldset': {
                                              borderColor: 'white',
                                            },
                                            '&:hover fieldset': {
                                              borderColor: 'white',
                                            },
                                            '&.Mui-focused fieldset': {
                                              borderColor: 'white',
                                            },
                                          },}}
                                        ampm={false}
                                        views={['day', 'hours', 'minutes']}
                                        disablePast defaultValue={dayjs()}
                                        shouldDisableTime={(value, view) => {
                                            const selectedDateTime = dayjs(value.$d).valueOf();
                                            return isInAnyInterval(
                                                filterByParams(
                                                    bookedDateTimes,
                                                    {
                                                        name: "watercraftLogId",
                                                        value: watercraftLogId
                                                    }
                                                ),
                                                selectedDateTime
                                            )
                                        }} />
                                </Grid>

                            </LocalizationProvider>
                            <Grid item>
                                <Button variant="contained" onClick={onSubmit}>Rent boat</Button>
                            </Grid>


                        </Grid>

                    </Fragment >
                </Container>
            </div>
        </div>
    )
}