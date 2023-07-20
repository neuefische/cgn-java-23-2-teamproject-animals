import {
    Box,
    Button,
    Card,
    CardActions,
    CardContent,
    CardMedia,
    Grid,
    InputLabel,
    MenuItem,
    Select,
    SelectChangeEvent,
    TextField,
    Typography
} from "@mui/material";
import FormControl from '@mui/material/FormControl';
import React, {FormEvent, useEffect, useRef, useState} from "react";
import {Animal, Item, Type} from "./Utils.tsx";
import axios from "axios";


type Props = {
    setAnimals: React.Dispatch<React.SetStateAction<Animal[]>>,
    animals: Animal[]
    animalId: string
    setEditMode: React.Dispatch<React.SetStateAction<boolean>>
    editMode: boolean
    currentAnimal: Animal
    setShowAnimalMode: React.Dispatch<React.SetStateAction<boolean>>
    showAnimalMode: boolean
}


function FormularContainer({
                               setAnimals, animals,
                               animalId, setEditMode,
                               editMode, currentAnimal,
                               setShowAnimalMode, showAnimalMode
                           }: Props) {

    const [name, setName] = useState<string>("")
    const [favoriteFood, setFavoriteFood] = useState("")
    const [dateOfBirth, setDateOfBirth] = useState("")
    const [type, setType] = useState(Type.OTHER);
    const [file, setFile] = useState<File>()
    const ref = useRef("" as any);
    const animalRef = useRef({
        name: name, favoriteFood: favoriteFood,
        dateOfBirth: dateOfBirth,
        type: type,
        AnimalUserId: "",
        imageUrl: ""
    } as any)

    const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files) {
            setFile(event.target.files[0]);
        }
    };

    const reset = () => {
        ref.current.value = "";
    };


    async function handleAddNewAnimal(e: FormEvent<HTMLFormElement>) {
        e.preventDefault()
        const newAnimal: Animal = {
            id: animals.length.toString(),
            name,
            favoriteFood,
            dateOfBirth,
            type,
            imageUrl: "",
            AnimalUserid: ""
        }
        setAnimals([...animals, newAnimal])
        await axios.post("/api/animals", {name, favoriteFood, dateOfBirth, type, file},
            {headers: {"content-type": "multipart/form-data"}})

        setName("")
        setType(Type.OTHER)
        setFavoriteFood("")
        setDateOfBirth("")
        reset()
    }

    function updateAnimal(id: string) {
        axios.put(`/api/animals/${animalId}`, {name, favoriteFood, dateOfBirth, type})
        const toUpdateAnimal = animals.find((animal: Animal) => (animal.id === id))
        if (toUpdateAnimal) {
            toUpdateAnimal.name = name
            toUpdateAnimal.favoriteFood = favoriteFood
            toUpdateAnimal.dateOfBirth = dateOfBirth
            toUpdateAnimal.type = type
        }
        setEditMode(false)
    }

    const handleTypeChange = (event: SelectChangeEvent<typeof type>) => {
        const {value} = event.target
        setType(value as Type)
    };


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
                <form onSubmit={handleAddNewAnimal} style={{display: "flex", flexDirection: "column"}}>
                    <TextField label="Animal Name" variant="outlined" value={name}
                               onChange={(e) => setName(e.target.value)}
                               sx={{mb: 2}} inputRef={animalRef}/>
                    <TextField label="favorite food" variant="outlined" value={favoriteFood}
                               onChange={(e) => setFavoriteFood(e.target.value)}
                               sx={{mb: 2}}
                    />
                    <TextField label=" date of of - dd/mm/yyyy" variant="outlined" value={dateOfBirth}
                               onChange={(e) => setDateOfBirth(e.target.value)}
                               sx={{mb: 2}}
                    />

                    <Box sx={{mr: 100, mb: 5}}>
                        <FormControl sx={{mt: 2, minWidth: 120}}>
                            <InputLabel htmlFor="Type">Type</InputLabel>
                            <Select
                                autoFocus
                                label="Type"
                                value={type}
                                onChange={handleTypeChange}
                                inputProps={{
                                    name: 'Type',
                                    id: 'Type'
                                }}
                            >
                                <MenuItem value={"DOG"}>dog</MenuItem>
                                <MenuItem value={"CAT"}>cat</MenuItem>
                                <MenuItem value={"RABBIT"}>rabbit</MenuItem>
                                <MenuItem value={"GUINEA_PIG"}>guinea pig</MenuItem>
                                <MenuItem value={"HAMSTER"}>hamster</MenuItem>
                                <MenuItem value={"MICE"}>mice</MenuItem>
                                <MenuItem value={"RAT"}>rat</MenuItem>
                                <MenuItem value={"FERRET"}>ferret</MenuItem>
                                <MenuItem value={"BIRD"}>bird</MenuItem>
                                <MenuItem value={"REPTILE"}>reptile</MenuItem>
                                <MenuItem value={"FISH"}>fish</MenuItem>
                                <MenuItem value={"OTHER"}>other</MenuItem>
                            </Select>
                        </FormControl>

                    </Box>

                    <input style={{margin: 6}} type="file" onChange={handleFileChange} ref={ref as any}/>
                    <p style={{marginRight: 180}}>Image size should not be more than 1MB</p>
                    <Button
                        variant="contained" type="submit"
                        style={{margin: 4, width: 200}}
                    >
                        Add Animal
                    </Button>
                </form>
                {editMode && (<Button variant="contained" sx={{mr: 83, width: 200}} onClick={() => {
                    updateAnimal(animalId)
                    setName("")
                    setType(Type.OTHER)
                    setFavoriteFood("")
                    setDateOfBirth("")
                }}>update</Button>)}
            </Item>
            <Grid>
                {showAnimalMode ? <Card sx={{margin: 2}}>
                    <CardMedia
                        sx={{height: 200}}
                        title={currentAnimal.name}
                        image={currentAnimal.imageUrl}
                        component='img'
                    />
                    <CardContent>
                        <Typography gutterBottom variant="h5" component="div">
                            Name: {currentAnimal.name}
                        </Typography>
                        <Typography variant="body2" color="text.secondary">
                            Favorite Food: {currentAnimal.favoriteFood}
                        </Typography>
                        <Typography variant="body2" color="text.secondary">
                            Type: {currentAnimal.type}
                        </Typography>
                        <Typography variant="body2" color="text.secondary">
                            Date of Birth: {currentAnimal.dateOfBirth}
                        </Typography>
                    </CardContent>
                    <CardActions>
                        <Button onClick={() => setShowAnimalMode(prev => !prev)} variant="contained"
                                size="small">close</Button>
                    </CardActions>
                </Card> : null}
            </Grid>
        </Grid>
    );
}

export default FormularContainer;