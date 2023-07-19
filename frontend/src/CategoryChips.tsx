import {Chip, Grid, Stack} from "@mui/material";
import {Item} from "./Utils.tsx";


function CategoryChips() {
    return (
        <Grid item xs={12} sx={{mt: 2}}>
            <Item variant="outlined">
                <Stack direction="row" spacing={4}
                       sx={{display: "flex", flexWrap: "wrap", justifyContent: "center", alignItems: "center"}}>
                    <Chip label="Dogs" variant="outlined" sx={{bgcolor: "turquoise"}}/>
                    <Chip label="Cats" variant="outlined" sx={{bgcolor: "blueviolet"}}/>
                    <Chip label="Bird" variant="outlined" sx={{bgcolor: "orange"}}/>
                    <Chip label="Reptile" variant="outlined" sx={{bgcolor: "yellow"}}/>
                    <Chip label="Fish" variant="outlined" sx={{bgcolor: "lightBlue"}}/>
                </Stack>
            </Item>
        </Grid>
    );
}

export default CategoryChips;