import {Backdrop, CircularProgress} from "@mui/material";

export const LoadingPage = () => {
    return (
        <Backdrop open={true}>
            <CircularProgress />
        </Backdrop>
    )
}