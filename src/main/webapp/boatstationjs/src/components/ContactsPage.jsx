import { Container, Typography } from "@mui/material"
import { Grid } from "@mui/material"
import { Image } from "mui-image"
import bgVideo from "../videos/undersea.mp4"
import mailImg from "../img/phone.png"
import phoneImg from "../img/smartphone.png"
import socialImg from "../img/social-media.png"

export const ContactsPage = () => {
    return (

        <div className="main">
            <video src={bgVideo} autoPlay loop muted />
            <div className="content">
                <Container>
                    <Grid
                        container
                        direction="column"
                        justifyContent="space-between"
                        alignItems="center"
                        marginTop={25}
                        spacing={5}
                    >
                        <Grid item>
                            <Typography variant="h2">
                                Наши контакты
                            </Typography>
                        </Grid>
                        <Grid item>
                            <Grid
                                container
                                direction="row"
                                justifyContent="center"
                                alignItems="flex-start"
                            >
                                <Grid item xs={4}>
                                    <Grid
                                        container
                                        direction="column"
                                        justifyContent="space-between"
                                        alignItems="center"
                                    >
                                        <Grid item>
                                            <Image src={phoneImg} fit="contain" width="10vh" />
                                        </Grid>
                                        <Grid item>
                                            <Typography variant="h6" sx={{ mt: 5 }}>
                                                Позвоните по номеру телефона для получения обратной связи: 89963250540
                                            </Typography>
                                        </Grid>

                                    </Grid>
                                </Grid>
                                <Grid item xs={4}>
                                    <Grid
                                        container
                                        direction="column"
                                        justifyContent="space-between"
                                        alignItems="center"
                                    >
                                        <Grid item>
                                            <Image src={mailImg} fit="contain" width="10vh" />
                                        </Grid>
                                        <Grid item>
                                            <Typography variant="h6" sx={{ mt: 5 }}>
                                                Напишите нам на почту: ceckinkonstantin@gmail.com
                                            </Typography>
                                        </Grid>


                                    </Grid>
                                </Grid>
                                <Grid item xs={4}>
                                    <Grid
                                        container
                                        direction="column"
                                        justifyContent="space-between"
                                        alignItems="center"
                                    >
                                        <Grid item>
                                            <Image src={socialImg} fit="contain" width="10vh" />
                                        </Grid>
                                        <Grid item>
                                            <Typography variant="h6" sx={{ mt: 5 }}>
                                               Или отправьте сообщение в телеграм: @kceckin
                                            </Typography>
                                        </Grid>


                                    </Grid>
                                </Grid>
                            </Grid>
                        </Grid>
                    </Grid>
                </Container>
            </div>
        </div>


    )
}