import { Route, Routes } from "react-router-dom";
import ContactsPage from "./pages/ContactsPage";
import CareerPage from "./pages/CareerPage";
import RentalPage from "./pages/RentalPage";
import HomePage from "./pages/home/HomePage";
import AdminPanel from "./pages/admin/AdminPanel";
import Header from "./pages/navbar/Header";

import { Grid } from "@mui/material";
import { Container } from "@mui/material";

export default function App(props) {
    return (
        <Container >
            <Grid container spacing={2} >
                <Grid item xs={12} paddingBottom={10}>
                    <Header />
                </Grid>
                <Routes  path="/">
                    <Route exact path="/contacts" element={<ContactsPage />}></Route>
                    <Route exact path="/career" element={<CareerPage />}></Route>
                    <Route exact path="/rental" element={<RentalPage />}></Route>
                    <Route exact path="/home" element={<HomePage />}></Route>
                    <Route exact path="/adminpanel" element={<AdminPanel />}></Route>
                </Routes>

            </Grid>

        </Container >


    );
}