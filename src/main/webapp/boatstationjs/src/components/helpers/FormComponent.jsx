import { Button, Dialog, FormControl, Grid, InputLabel, MenuItem, Select, TextField, Toolbar, Typography } from "@mui/material"
import { Fragment, useEffect, useState } from "react"



export const FormComponent = ({ elements, title }) => {

    return (
        <Fragment>
            <Grid container
                direction="column"
                justifyContent="center"
                alignItems="center">
                <Typography variant="h4" >{title}</Typography>

                {
                    elements?.map(({
                        type,
                        name,
                        value,
                        getOptions,
                        showOption,
                        getValue,
                        onChange,
                        required }) => {
                        const options = getOptions();
                        if (type === "selector") {
                            console.log(name)
                            return (
                                <Grid item xs={12} key={name + "griditem"}>
                                    <FormControl key={name + "formcontrol"} sx={{ m: 1, minWidth: 160 }}>
                                        <InputLabel id={name + "lable"}>{name}</InputLabel>
                                        <Select
                                            onChange={onChange}
                                            label={name}
                                            labelId={name + "lable"}
                                            id={name}
                                            key={name}
                                            required={required ?? false}
                                            value={value}
                                            autoWidth

                                        >
                                            {options?.map((option) => {
                                                return (<MenuItem key={getValue(option)} value={getValue(option)}>{showOption(option)}</MenuItem>);
                                            })}
                                        </Select>
                                    </FormControl>

                                </Grid>

                            )
                        }
                        else if (type === "textField") {

                            return (
                                <Grid item xs={12}>
                                    <FormControl>
                                        <TextField
                                            required={required ?? false}
                                            label={name}
                                            key={name}
                                            value={value}
                                            onChange={onChange}
                                        />
                                    </FormControl>

                                </Grid>

                            )
                        }

                    })
                }
            </Grid>

        </Fragment>

    )
}