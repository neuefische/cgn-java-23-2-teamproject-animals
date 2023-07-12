import {Chip, Grid, Stack} from "@mui/material";
import {Item} from "./Utils.tsx";

function CategoryChips() {
    return (
        <Grid item xs={12} sx={{mt: 2}}>
            <Item variant="outlined">
                <Stack direction="row" spacing={1}
                       sx={{display: "flex", justifyContent: "center", alignItems: "center"}}>
                    <Chip label="Dogs" variant="outlined"/>
                    <Chip label="Cats" variant="outlined"/>
                </Stack>
            </Item>
        </Grid>
    );
}

export default CategoryChips;