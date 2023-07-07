import {Button, Grid, TextField, Typography} from "@mui/material";
import {FormEvent, useState} from "react";
import {Animal, Item} from "./Utils.tsx";
import axios from "axios";


type Props = {
    setAnimals: React.Dispatch<React.SetStateAction<Animal[]>>,
    animals: Animal[]
}


function FormularContainer({setAnimals, animals}: Props) {

    const [idCounter, setIdCounter] = useState(1)
    const [animal, setAnimal] = useState<Animal>({
        id: idCounter.toString(),
        name: ""
    })

    function addAnimal(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        setAnimals([...animals, animal])
        setAnimal({
            id: null,
            name: ""
        })
        axios.post("/api/animals", animal)
    }

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setAnimal({
            id: idCounter.toString(),
            name: event.target.value
        })
        setIdCounter(idCounter + 1)
    }

    return (
        <Grid item xs={6} sx={{mr: 2}}>
            <Item sx={{mh: 100, backgroundColor: "#35baf6"}}>
                <Typography>Neues Tier hinzufügen</Typography>
                <form onSubmit={addAnimal} style={{display: "flex", flexDirection: "column"}}>
                    <TextField label="Animal Name" variant="outlined" value={animal.name}
                               onChange={handleChange}/>
                    <Button variant="contained" type="submit" style={{margin: 1, textTransform: "none"}}>
                        Add Animal
                    </Button>
                </form>
            </Item>
        </Grid>
    );
}

export default FormularContainer;