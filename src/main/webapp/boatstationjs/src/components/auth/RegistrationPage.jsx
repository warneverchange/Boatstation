import { Button, Container, TextField } from "@mui/material";
import { useAuthenticationContext } from "./AuthenticationProvider";
import { useNavigate } from "react-router-dom";
import bgVideo from '../../videos/reg.mp4'

const { Stack, Grid, Typography } = require("@mui/material");
const { Fragment, useState } = require("react");
const RegistrationPage = () => {
    const authenticationContext = useAuthenticationContext()
    const navigate = useNavigate();


    const [regData, setRegData] = useState({
        "firstName": "",
        "lastName": "",
        "patronymic": "",
        "passportData": "",
        "phoneNumber": "",
        "username": "",
        "password": "",
        "email": ""
    })

    const inputFieldsData = [
        ["First name", (value) => {
            setRegData({ ...regData, "firstName": value })
        }],
        ["Last name", (value) => {
            setRegData({ ...regData, "lastName": value })
        }],
        ["Patronymic", (value) => {
            setRegData({ ...regData, "patronymic": value })
        }],
        ["Passport data", (value) => {
            setRegData({ ...regData, "passportData": value })
        }],
        ["Phone number", (value) => {
            setRegData({ ...regData, "phoneNumber": value })
        }],
        ["Username", (value) => {
            setRegData({ ...regData, "username": value })
        }],
        ["Password", (value) => {
            setRegData({ ...regData, "password": value })
        }],
        ["Email", (value) => {
            setRegData({ ...regData, "email": value })
        }],

    ]
    const inputFields = inputFieldsData.map(([columnName, setState]) => {
        return (
            <Fragment>
                <Grid item>
                    <Typography variant="h6" >
                        {columnName}
                    </Typography>
                </Grid>
                <Grid item>
                    {columnName === 'Password' ?
                        <TextField onChange={event => setState(event.target.value)} type="password" sx={{ width: 400,  backgroundColor: "rgba(255, 255, 255, 0.2)" }} />
                        :
                        <TextField onChange={event => setState(event.target.value)} sx={{ width: 400, backgroundColor: "rgba(255, 255, 255, 0.2)" }} />
                    }
                </Grid>
            </Fragment>);
    })

    return (
        <Fragment>
            <div className="main">
                <video style={{ width: "105%", height: "105%" }} src={bgVideo} autoPlay loop muted />
                <div className="content">
                    <Container>
                        <Grid
                            container
                            direction="column"
                            justifyContent="center"
                            alignItems="center"
                            spacing={2}
                            marginTop={1}
                        >
                            {inputFields}
                            <Grid item>
                                <Grid
                                    container
                                    direction="row"
                                    justifyContent="space-evenly"
                                    alignItems="center"
                                    sx={{ mt: 5 }}
                                >
                                    <Grid item>
                                        <Button
                                            size="large"
                                            variant="contained"
                                            sx={{ width: 190, mr: 5 }}
                                            onClick={() => authenticationContext.register(regData)}
                                        >
                                            Register
                                        </Button>
                                    </Grid>
                                    <Grid item>
                                        <Button
                                            size="large"
                                            variant="contained"
                                            sx={{ width: 190 }}
                                            onClick={() => { navigate("/auth") }}
                                        >
                                            Back
                                        </Button>
                                    </Grid>
                                </Grid>
                            </Grid>
                        </Grid>

                    </Container>
                </div>
            </div>
        </Fragment>
    );
}

export default RegistrationPage;