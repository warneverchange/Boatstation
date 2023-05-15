import React, { useState } from "react";
import {
    AppBar,
    Button,
    Icon,
    Tab,
    Tabs,
    Toolbar,
    Typography,
    useMediaQuery,
    useTheme,
} from "@mui/material";

import DrawerComp from "./Drawer";
import DirectionsBoatIcon from '@mui/icons-material/DirectionsBoat';
import { Link } from "react-router-dom";
import {useAuthenticationContext} from "../auth/AuthenticationProvider";

const Header = () => {
    const authenticationContext = useAuthenticationContext();
    const [value, setValue] = useState();
    const theme = useTheme();
    console.log(theme);
    const isMatch = useMediaQuery(theme.breakpoints.down("md"));
    console.log(isMatch);

    return (
        <React.Fragment>
            <AppBar position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
                <Toolbar>   
                    <DirectionsBoatIcon sx={{ transform: "scale(2)" }} />
                    <Icon s></Icon>
                    {isMatch ? (
                        <>
                            <Typography sx={{ fontSize: "2rem", paddingLeft: "10%" }}>
                                Shoppee
                            </Typography>
                            <DrawerComp />
                        </>
                    ) : (
                        <>
                            <Tabs
                                sx={{ marginLeft: "auto" }}
                                indicatorColor="secondary"
                                textColor="inherit"
                                value={value}
                                onChange={(e, value) => setValue(value)}
                            >
                                <Tab label="Home" to="/" component={Link} />
                                <Tab label="Rental" to="/rental" component={Link} />
                                <Tab label="Career" to="/career" component={Link} />
                                <Tab label="Contacts" to="/contacts" component={Link} />
                                { authenticationContext.isAdmin &&
                                    <Tab label="Admin panel" to="/adminpanel" component={Link}/>
                                }

                            </Tabs>


                            <Button sx={{ marginLeft: "auto" }} variant="contained" to="/profile" component={Link}>
                                Profile
                            </Button>
                            <Button sx={{ marginLeft: "10px" }} variant="contained" onClick={authenticationContext.logout}>
                                Logout
                            </Button>
                        </>
                    )}
                </Toolbar>
            </AppBar>
        </React.Fragment>
    );
};


export default Header;