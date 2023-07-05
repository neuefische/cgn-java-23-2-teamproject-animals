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
                <img src="/images/Bild.png" alt="Animal picture"
                     loading="lazy"
                     style={{
                         borderRadius: 8,
                         objectFit: "cover",
                         width: "100%",
                         height: 100,
                         objectPosition: "80% 40%",
                     }}/>
            </Item>
        </Grid>
    );
}

export default Header;