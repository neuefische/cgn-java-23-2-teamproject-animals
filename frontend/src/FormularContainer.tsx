import {Button, Grid, Paper, styled, TextField, Typography} from "@mui/material";
import {useState} from "react";
import {Animal} from "./Animal.tsx";
import axios from "axios";


type Props = {
    setAnimals: React.Dispatch<React.SetStateAction<Animal[]>>;

}


const Item = styled(Paper)(({theme}) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
    margin: 4
}));

function FormularContainer({setAnimals}: Props) {

    const [name, setName] = useState("")

    function addAnimal() {
        axios.post("/api/animals", {
            name
        }).then((response) => {
            setAnimals(response.data)
            setName("")
        }).catch(error => console.log(error))
    }

    return (
        <Grid item xs={6} sx={{mr: 2}}>
            <Item sx={{mh: 100, backgroundColor: "#35baf6"}}>
                <Typography>Neues Tier hinzufügen</Typography>
                <form onSubmit={addAnimal} style={{display: "flex", flexDirection: "column"}}>
                    <TextField label="Animal Name" variant="outlined" value={name}
                               onChange={(event) => setName(event.target.value)}/>
                    <Button variant="contained" style={{margin: 1, textTransform: "none"}}>
                        Add Animal
                    </Button>
                </form>
            </Item>
        </Grid>
    );
}

export default FormularContainer;