import { Fragment, useState } from "react";
import { useFetching } from "../hooks/useFetching"
import { User } from "../queries/User";
import { AppBar, Button, Container, Grid, TextField, Typography } from "@mui/material";
import { Stack } from "@mui/material";
import { useAuthenticationContext } from "./auth/AuthenticationProvider";
import bgVideo from "../videos/reg.mp4"

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
        <div className="main">
            <video src={bgVideo} autoPlay loop muted />
            <div className="content">
                <Container>
                    <Fragment>

                        <Grid
                            container
                            direction="column"
                            justifyContent="center"
                            alignItems="center"
                            spacing={2}
                            marginTop={5}
                        >


                            {Object.keys(currentUserData).length !== 0 &&

                                Object.keys(currentUserData).map((key) => {
                                    if (key !== "id") {
                                        return (
                                            <Fragment>
                                                <Grid item>
                                                    <Typography variant="h6">{FieldLabels[key]}</Typography>
                                                </Grid>
                                                <Grid item>
                                                    <TextField
                                                        sx={{ width: 300, backgroundColor: "rgba(255, 255, 255, 0.5)" }}
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
                                            </Fragment>
                                        )
                                    }

                                })}

                            <Grid item>
                                <Grid
                                    container
                                    direction="column"
                                    justifyContent="space-evenly"
                                    alignItems="center"
                                    spacing={2}
                                >
                                    <Grid item>
                                        <Button sx={{ width: 300 }} size="large" variant="contained" onClick={() => {
                                            User.updateCurrentUserClientData(currentUserData);
                                        }}>Save info</Button>
                                    </Grid>
                                    <Grid item>
                                        <Button sx={{ width: 300 }} size="large" variant="contained" onClick={() => {
                                            User.deleteCurrentAccout();
                                            authenticationContext.logout();
                                        }}>Delete account</Button>
                                    </Grid>
                                </Grid>
                            </Grid>

                        </Grid>
                    </Fragment>
                </Container>
            </div>
        </div>

    )
}