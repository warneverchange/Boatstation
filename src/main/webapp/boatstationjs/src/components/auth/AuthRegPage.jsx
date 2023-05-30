import { Link } from "react-router-dom"
import { Button, Container, Stack } from "@mui/material"
import { Grid } from "@mui/material"
import bgVideo from '../../videos/auth.mp4'
import { Fragment } from "react"

export const AuthRegPage = () => {

    return (
        <Fragment>
            <div className="main">
                <video style={{width: "105%", height: "105%"}} src={bgVideo} autoPlay loop muted/>
                <div className="content">
                    <Container>
                        <Grid
                            container
                            alignItems="center"
                            direction="column"
                            justifyContent="center"
                            marginTop={50}>
                            <Stack
                                useFlexGap
                                flexWrap="wrap">
                                <Button size="large" variant="contained" component={Link} to="/auth/registration">
                                    Registration
                                </Button>
                                <Button size="large" variant="contained" component={Link} to="/auth/authentication" sx={{ mt: 3 }}>
                                    Login
                                </Button>
                            </Stack>
                        </Grid>
                    </Container>
                </div>

            </div>

        </Fragment>

    )
}