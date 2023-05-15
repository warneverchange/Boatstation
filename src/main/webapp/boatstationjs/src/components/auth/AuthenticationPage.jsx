import { useAuthenticationContext } from "./AuthenticationProvider";
import { Fragment, useState } from "react";
import { Box, Button, Grid, Stack, TextField, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { VisibilityOff } from "@mui/icons-material";
import { InputAdornment } from "@mui/material";

export const AuthenticationPage = () => {
    const navigate = useNavigate();
    const authenticationContext = useAuthenticationContext()

    const [authData, setAuthData] = useState({
        "username": "",
        "password": ""
    })

    const inputFieldsData = [
        ["Username", (value) => {
            setAuthData({ ...authData, "username": value })
        }],
        ["Password", (value) => {
            setAuthData({ ...authData, "password": value })
        }],
    ]

    const inputFields = inputFieldsData.map(([columnName, setState]) => {
        return (
            <Fragment>
                <Grid item>
                    <Typography variant="h6">
                        {columnName}
                    </Typography>
                </Grid>
                <Grid item>
                    {columnName === 'Password' ?
                        <TextField onChange={event => setState(event.target.value)} type="password" sx={{ width: 400 }} />
                        : <TextField onChange={event => setState(event.target.value)} sx={{ width: 400 }} />
                    }

                </Grid>
            </Fragment>
        );
    })


    return (
        <Fragment>

            <Grid
                container
                direction="column"
                justifyContent="center"
                alignItems="center"
                spacing={2}
                marginTop={30}
            >

                {inputFields}
                <Grid item>
                    <Grid
                        container
                        direction="row"
                        justifyContent="space-evenly"
                        alignItems="center"
                        sx={{mt: 5}}
                    >

                        <Grid item>
                            <Button
                                size="large"
                                variant="contained"
                                onClick={() => authenticationContext.authenticate(authData)}
                                sx={{ width: 190 }}
                            >
                                Authenticate
                            </Button>
                        </Grid>
                        <Grid item>
                            <Button
                                size="large"
                                variant="contained"  onClick={() => navigate("/auth")}
                                sx={{ width: 190, ml: 5 }}
                            >
                                Back
                            </Button>
                        </Grid>
                    </Grid>
                </Grid>


            </Grid>
        </Fragment>

    )

}