import {Grid} from "@mui/material";


function Header() {
    return (
        <Grid item xs={12}>
                <img src="/images/Bild.png" alt="Animal picture"
                     loading="lazy"
                     style={{
                         objectFit: "cover",
                         width: "100%",
                         height: 100,
                         objectPosition: "80% 40%",
                         margin: 0,
                         border: "1px solid #D3D3D3"
                     }}/>
        </Grid>
    );
}

export default Header;