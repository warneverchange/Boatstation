import { Fragment } from "react"
import { Outlet } from "react-router-dom"
import Header from "./navbar/Header"
import { Grid } from "@mui/material"
import Image from '../img/background_page.png'

export const MainLayout = () => {

    return (
        <Fragment>
            <Grid item xs={12}>
                <Header />
            </Grid>
            <Grid item xs={12} marginTop={5}>
                <Outlet />
            </Grid>
        </Fragment>

    )
}
