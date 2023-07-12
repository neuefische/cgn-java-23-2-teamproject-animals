import {Box, Button, Grid, List, Typography} from "@mui/material";
import {Item} from "./Utils.tsx";
import TwitterIcon from '@mui/icons-material/Twitter';
import InstagramIcon from '@mui/icons-material/Instagram';

function Footer() {

    return (
        <Grid item xs={12}>
            <Item sx={{padding: 5, backgroundColor: (theme) => theme.palette.primary.main}}>
                <Box>
                    <Typography sx={{color: "white"}}>Developed and maintained by
                        team-project-1 &copy; {new Date().getFullYear()}</Typography>
                    <List>
                        <Button sx={{textTransform: "none", color: "white"}}
                                endIcon={<TwitterIcon sx={{color: "#4dabf5"}}/>}>
                            twitter
                        </Button>
                        <Button sx={{textTransform: "none", color: "white"}}
                                endIcon={<InstagramIcon sx={{color: "red"}}/>}>
                            Instagram
                        </Button>
                    </List>
                </Box>
            </Item>
        </Grid>
    );
}

export default Footer;