import {Box, Grid, Paper, styled, TextField, Typography, Button} from "@mui/material";

const Item = styled(Paper)(({theme}) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
    margin: 4
}));

function FormularContainer() {
    return (
        <Grid xs={6} sx={{mr: 2}}>
            <Item sx={{mh: 100, backgroundColor: "#35baf6"}}>
                <Typography>Neues Tier hinzufügen</Typography>
                <Box component="form" autoComplete="off" sx={{display: "flex", flexDirection: "column"}}>
                    <TextField label="Animal Name" variant="outlined"/>
                    <Button variant="contained" sx={{margin: 1, textTransform: "none"}}>
                        Add Animal
                    </Button>
                </Box>
            </Item>
        </Grid>
    );
}

export default FormularContainer;