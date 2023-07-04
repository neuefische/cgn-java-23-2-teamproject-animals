import {Grid, Typography} from "@mui/material";
import {Item} from "./Utils.tsx";


function Header() {
    return (
        <Grid item xs={12}>
            <Item sx={{height: 100, backgroundColor: "#35baf6"}}>
                <Typography>Header: Tier Management Application -standard willkommensbild: ein tier -
                    tiergruppenfoto?</Typography>
            </Item>
        </Grid>
    );
}

export default Header;