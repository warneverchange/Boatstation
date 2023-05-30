import { Button, Container, Typography } from "@mui/material"
import bgVideo from "../videos/ocean.mov"
import { Fragment } from "react"
import { Grid } from "@mui/material"
import Image from "mui-image"
import lifebouyImg from "../img/lifebuoy.png"
import { useNavigate } from "react-router-dom"


export const HomePage = () => {
    const navigate = useNavigate();

    return (
        <Fragment>
            <div className="main">
                <video src={bgVideo} autoPlay loop muted />
                <div className="content">
                    <Container>
                        <Grid container marginTop={5}>

                            <Grid
                                container
                                direction="row"
                                justifyContent="space-around"
                                alignItems="center"
                            >
                                <Grid item xs={12} md={6}>
                                    <Typography variant="h3">
                                        Компания Boatstation
                                    </Typography>
                                    <Typography variant="h6" sx={{ mt: 5 }}>
                                        Наша компания предоставляет услуги аренды плавательных средств.
                                        С дня нашего основания мы бережно заботимся о клиентах и том,
                                        чтобы их отдых прошел удачно
                                    </Typography>
                                </Grid>
                                <Grid item xs={12} md={6} justifyContent="center" alignItems="center">
                                    <Image src={lifebouyImg} fit="contain" width="50vh" />
                                </Grid>
                            </Grid>


                            <Grid
                                container
                                direction="row"
                                justifyContent="space-around"
                                alignItems="center"
                            >
                                <Grid item xs={12} md={6}>
                                    <Button size="large" variant="contained" sx={{width: 400, height: 100}} onClick={() => navigate('/rental')}>
                                        Перейти к аренде
                                    </Button>
                                </Grid>

                                <Grid item xs={12} md={6}>
                                    <Typography variant="h3" sx={{ mt: 5 }}>
                                        Не время ждать
                                    </Typography>
                                    <Typography variant="h6" sx={{ mt: 5 }}>
                                        На нашем сайте вы можете найти любой асортимент плавательных средств, 
                                        от яхт и катеров, до гидроциклов и байдарок. Чтобы начать перейдите по ссылке слева
                                    </Typography>
                                </Grid>
                            </Grid>
                        </Grid>

                    </Container>
                </div>
            </div>

        </Fragment>

    )
}