import {Link} from "react-router-dom"
import { Button, Stack } from "@mui/material"
import { Grid } from "@mui/material"

export const AuthRegPage = () => {

    return (
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
                    <Button size="large" variant="contained" component={Link} to="/auth/authentication" sx={{mt: 3}}>
                        Login
                    </Button>
            </Stack>
        </Grid>

    )
}