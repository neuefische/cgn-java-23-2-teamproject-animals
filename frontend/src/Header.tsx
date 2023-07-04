import {Grid, Paper, styled} from "@mui/material";

const Item = styled(Paper)(({theme}) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
    margin: 4
}));


function Header() {
    return (
        <Grid item xs={12}>
            <Item sx={{height: 100, backgroundColor: "#35baf6"}}>

                <img src="/frontend/src/assets/Bild.png" alt="Beschreibung des Bildes"/>

            </Item>
        </Grid>
    );
}

export default Header;