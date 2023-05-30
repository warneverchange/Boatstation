import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import Button from '@mui/material/Button';
import List from '@mui/material/List';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import MailIcon from '@mui/icons-material/Mail';
import { useTheme } from 'styled-components';
import { Grid, Toolbar, makeStyles } from '@mui/material';
import { CssBaseline } from '@mui/material';
import TodayIcon from '@mui/icons-material/Today';
import SummarizeIcon from '@mui/icons-material/Summarize';
import DirectionsBoatIcon from '@mui/icons-material/DirectionsBoat';
import WaterIcon from '@mui/icons-material/Water';
import { Fragment } from 'react';
import { Outlet } from 'react-router-dom';
import { Link } from 'react-router-dom';
import SupportIcon from '@mui/icons-material/Support';
import HailIcon from '@mui/icons-material/Hail';
import { Container } from 'react-bootstrap';


const drawerWidth = 240;


export const AdminPanel = () => {
    const drawerComponentsData = [
        { name: 'Rental', url: '/adminpanel/rental', listItemIcon: <TodayIcon /> },
        { name: 'Instruction report', url: '/adminpanel/report', listItemIcon: <SummarizeIcon /> },
        { name: 'Boat accounting', url: '/adminpanel/boats', listItemIcon: <DirectionsBoatIcon /> },
        { name: "Life saving devices", url: "/adminpanel/lsdevices", listItemIcon: <SupportIcon /> },
        { name: "Clients", url: "/adminpanel/clients", listItemIcon: <HailIcon /> }
    ]

    const list = () => (
        <Box
            sx={{ overflow: 'auto' }}
            role="presentation"
        >
            <List>
                {drawerComponentsData.map(({ name, url, listItemIcon }) => (
                    <ListItem key={name} disablePadding>
                        <ListItemButton to={url} component={Link}>
                            <ListItemIcon>
                                {listItemIcon}
                            </ListItemIcon>
                            <ListItemText primary={name} />
                        </ListItemButton>
                    </ListItem>
                ))}
            </List>
        </Box>
    );

    return (
        <Container>
            <Fragment>
                <Drawer
                    variant="permanent"
                    sx={{
                        width: drawerWidth,
                    }}
                >
                    <Toolbar />
                    {list()}
                </Drawer>
                <Outlet />
            </Fragment>
        </Container>

    );
}