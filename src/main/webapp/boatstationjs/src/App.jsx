import { Route, Routes } from "react-router-dom";
import { Grid } from "@mui/material";
import { AuthenticationProvider } from "./components/auth/AuthenticationProvider";
import { MainLayout } from "./components/MainLayout";
import { HomePage } from "./components/HomePage";
import { RentalPage } from "./components/RentalPage";
import { ContactsPage } from "./components/ContactsPage";
import { AuthRegPage } from "./components/auth/AuthRegPage";
import RegistrationPage from "./components/auth/RegistrationPage";
import { AuthenticationPage } from "./components/auth/AuthenticationPage";
import { AdminPanel } from "./components/admin/AdminPanel";
import { LoadingPage } from "./components/LoadingPage";
import { RentalComponent } from "./components/admin/RentalComponent";
import { ReportComponent } from "./components/admin/ReportComponent";
import { BoatsComponent } from "./components/admin/BoatsComponent";
import { Navigate } from "react-router-dom";
import { LifeSavingDevicesManagingComponent } from "./components/admin/LifeSavingDevicesManagingComponent";
import { ProfilePage } from "./components/ProfilePage";
import { ClientsComponent } from "./components/admin/ClientsComponent";

export default function App(props) {
    return (
            <Grid container spacing={2} >
                <AuthenticationProvider>
                    <Routes>
                        <Route path="/" element={<MainLayout />}>
                            <Route index element={<HomePage />} />
                            <Route path="rental" element={<RentalPage />} />
                            <Route path="contacts" element={<ContactsPage />} />
                            <Route path="adminpanel" element={<AdminPanel />}>
                                <Route index element={<Navigate to="/adminpanel/rental" />} />
                                <Route path="rental" element={<RentalComponent />} />
                                <Route path="report" element={<ReportComponent/>}/>
                                <Route path="boats" element={<BoatsComponent/>}/>
                                <Route path="lsdevices" element={<LifeSavingDevicesManagingComponent/>}/>
                                <Route path="clients" element={<ClientsComponent/>}/>
                            </Route>
                            <Route path="profile" element={<ProfilePage/>}/>
                        </Route>
                        <Route path="/loading" element={<LoadingPage />} />
                        <Route path="/auth" element={<AuthRegPage />} />
                        <Route path="/auth/registration" element={<RegistrationPage />} />
                        <Route path="/auth/authentication" element={<AuthenticationPage />} />
                    </Routes>
                </AuthenticationProvider>
            </Grid>

    );
}