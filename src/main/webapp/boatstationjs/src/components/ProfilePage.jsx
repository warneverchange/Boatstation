import { Fragment, useState } from "react";
import { useFetching } from "../hooks/useFetching"
import { User } from "../queries/User";
import { Button, Grid, TextField, Typography } from "@mui/material";
import { Stack } from "@mui/material";
import { useAuthenticationContext } from "./auth/AuthenticationProvider";

const FieldLabels = {
    firstName: "First name",
    lastName: "Last name",
    patronymic: "Patronymic",
    passportData: "Passport data",
    phoneNumber: "Phone number"
}

export const ProfilePage = () => {
    const [currentUserData, setCurrentUserData] = useState({})
    const authenticationContext = useAuthenticationContext();

    const [loadClientData, isClientDataLoading, clientDataLoadingErr] = useFetching(async () => {
        const response = await User.getCurrentUserData();
        setCurrentUserData(response.data);
    });

    useState(() => {
        loadClientData();
    }, [])

    return (
        <Fragment>

            <Grid
                container
                direction="row"
                justifyContent="center"
                alignItems="center"
                spacing={5}
                marginTop={15}
            >


                {Object.keys(currentUserData).length !== 0 &&

                    Object.keys(currentUserData).map((key) => {
                        if (key !== "id") {
                            return (
                                <Grid
                                    container
                                    direction="row"
                                    justifyContent="center"
                                    alignItems="center"
                                >
                                    <Grid item xs={6}>
                                        <Typography>{FieldLabels[key]}</Typography>
                                    </Grid>
                                    <Grid item xs={6}>
                                        <TextField
                                            variant="outlined"
                                            defaultValue={currentUserData[key]}
                                            onChange={(event) => {
                                                let newValue = event.target.value;
                                                currentUserData[key] = newValue;
                                                setCurrentUserData(
                                                    currentUserData
                                                )
                                            }}
                                        >
                                        </TextField>
                                    </Grid>
                                </Grid>
                            )
                        }

                    })}

                <Grid
                    container
                    direction="row"
                    justifyContent="center"
                    alignItems="center"
                >
                    <Grid item xs={6}>
                        <Button variant="outlined" onClick={() => {
                            User.updateCurrentUserClientData(currentUserData);
                        }}>Save info</Button>
                    </Grid>
                    <Grid item xs={6}>
                        <Button variant="outlined" onClick={() => {
                            User.deleteCurrentAccout();
                            authenticationContext.logout();
                        }}>Delete account</Button>
                    </Grid>
                </Grid>
            </Grid>
        </Fragment>
    )
}