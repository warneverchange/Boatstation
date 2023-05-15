import { Box, Button, Dialog, DialogActions, DialogContent, DialogTitle, FormControl, Grid, InputLabel, MenuItem, Select, Stack, TextField, Typography } from '@mui/material'
import React, { useImperativeHandle, useState } from 'react'
import { Snackbar } from '@mui/material'
import MuiAlert from '@mui/material/Alert';


const Alert = React.forwardRef(function Alert(props, ref) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

/*
    dialogElements{
        elements:[
            element:{
                type:"textField",
                name: "ObjectField",
                required:false
            },
            element{
                type:"selector",
                name: "Age"
                required:false,
                getOptions: ()=>{return [...]}
            }
        ]
    }
*/

const DialogForm = React.forwardRef(({ title, onClose, onSubmit, dialogElements, gridPadding = 2, gridSpacing = 1 }, ref) => {
    const [open, setOpen] = useState(false)
    const [error, setError] = useState({})
    useImperativeHandle(ref, () => ({
        setOpen: (value) => {
            setOpen(value)
        },
        clear: () => {
            onClose();
        },
        setError: (err) => {
            console.log(err)
            setError(err)
        }
    }));

    const handleErrorMessageClose = (event, reason) => {
        if (reason === 'clickaway') {
            return
        }
        setError({})
    }


    
    return (
        <div>
            <Dialog open={open}>
                <DialogTitle>{title}</DialogTitle>
                <DialogContent>
                    <Box component="form" id="form" sx={{ display: 'flex', flexWrap: 'wrap' }}>
                        <Grid
                            spacing={gridSpacing}
                            container
                            padding={gridPadding}
                            direction="column"
                            justifyContent="center"
                            alignItems="center"
                        >
                            {dialogElements.elements.map((element) => {
                                if (element.type === "selector") {
                                    const options = element.getOptions()
                                    return (
                                        <Grid item key={element.name + "GridItem"}>
                                            <FormControl key={element.name + "FromControl"} required={element.required ?? false} sx={{ m: 1, minWidth: 120 }} disabled={element.isDisabled?.() ?? false}>
                                                <InputLabel id={element.name + "id"}>{element.name}</InputLabel>
                                                <Select
                                                    labelId={element.name + "id"}
                                                    label={element.name}
                                                    autoWidth
                                                    required={element.required ?? false}
                                                    key={element.name}
                                                    onChange={element.onChange}
                                                    value={element.value}>
                                                    {options.map((option) => {
                                                        return (
                                                            <MenuItem key={element.getValue(option)} value={element.getValue(option)}>{element.showOption(option)}</MenuItem>
                                                        )
                                                    })}
                                                </Select>
                                            </FormControl>
                                        </Grid>)

                                }
                                else if (element.type === "textField") {
                                    return (
                                        <Grid item key={element.name + "GridItem"}>
                                            <FormControl key={element.name + "FromControl"} required={element.required ?? false}>
                                                <TextField
                                                    required={element.required ?? false}
                                                    label={element.name}
                                                    key={element.name}
                                                    value={element.value}
                                                    onChange={element.onChange}
                                                ></TextField>
                                            </FormControl>
                                        </Grid>
                                    )
                                }
                            })}
                        </Grid>
                    </Box>
                    <DialogActions>
                        <Grid
                            container
                            direction="row"
                            justifyContent="center"
                            alignItems="center"
                            spacing={5}>
                            <Grid item>
                                <Button variant='contained' type="submit" form="form" onClick={onSubmit}>Submit</Button>
                            </Grid>
                            <Grid item>
                                <Button variant='contained' onClick={() => { setOpen(false); onClose?.() }}>Close</Button>
                            </Grid>
                        </Grid>


                    </DialogActions>

                    {Object.keys(error).length !== 0 &&
                        <Snackbar open={true} autoHideDuration={3000} onClose={handleErrorMessageClose}>
                            <Alert onClose={handleErrorMessageClose} severity="error" sx={{ width: '100%' }}>
                                {error?.data.reason}
                            </Alert>
                        </Snackbar>
                    }
                </DialogContent>
            </Dialog>
        </div>
    )
})

export default DialogForm