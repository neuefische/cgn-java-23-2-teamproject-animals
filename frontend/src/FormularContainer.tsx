import {Button, Card, CardActions, CardContent, CardMedia, Grid, TextField, Typography} from "@mui/material";
import React, {FormEvent, useEffect, useRef, useState} from "react";
import {Animal, Item} from "./Utils.tsx";
import axios from "axios";


type Props = {
    setAnimals: React.Dispatch<React.SetStateAction<Animal[]>>,
    animals: Animal[]
    animalId: string
    setEditMode: React.Dispatch<React.SetStateAction<boolean>>
    editMode: boolean
}


function FormularContainer({setAnimals, animals, animalId, setEditMode, editMode}: Props) {
    const animalRef = useRef("" as any)
    const [name, setName] = useState<string>("")

    function addAnimal(event: FormEvent<HTMLFormElement>) {
        event.preventDefault()
        const newAnimal = {
            name
        }
        const animalWithId: Animal = {
            id: animals.length.toString(),
            name
        }
        axios.post("/api/animals", newAnimal)
        setAnimals([...animals, animalWithId])
        setName("")
    }

    function updateAnimal(id: string) {
        axios.put(`/api/animals/${animalId}`, {name})
        const toUpdateAnimal = animals.find((animal: Animal) => (animal.id === id))
        if (toUpdateAnimal) toUpdateAnimal.name = name

    }


    useEffect(() => {
        if (animalId !== "") {
            setEditMode(prev => !prev)
            animalRef.current.focus()
        }
    }, [animalId, setEditMode])

    return (
        <Grid item xs={6} sx={{}}>
            <Item variant="outlined" sx={{mh: 100}}>
                <Typography>Neues Tier hinzufügen</Typography>
                <form onSubmit={addAnimal} style={{display: "flex", flexDirection: "column"}}>
                    <TextField label="Animal Name" variant="outlined" value={name} inputRef={animalRef as any}
                               onChange={(e) => setName(e.target.value)}/>
                    <Button
                        variant="contained" type="submit"
                        style={{margin: 4, textTransform: "none"}}
                    >
                        Add Animal
                    </Button>
                </form>
                {editMode && (<Button variant="contained" onClick={() => {
                    updateAnimal(animalId)
                    setName("")
                }}>update</Button>)}
            </Item>
            <Grid>
                <Card sx={{margin: 2}}>
                    <CardMedia
                        sx={{height: 140}}
                        title="green iguana"
                        image="/static/images/cards/contemplative-reptile.jpg"
                    />
                    <CardContent>
                        <Typography gutterBottom variant="h5" component="div">
                            Lizard
                        </Typography>
                        <Typography variant="body2" color="text.secondary">
                            Lizards are a widespread group of squamate reptiles, with over 6,000
                            species, ranging across all continents except Antarctica
                        </Typography>
                    </CardContent>
                    <CardActions>
                        <Button variant="contained" size="small">close</Button>
                    </CardActions>
                </Card>
            </Grid>
        </Grid>
    );
}

export default FormularContainer;