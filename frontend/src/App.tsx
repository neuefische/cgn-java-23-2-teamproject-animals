import {useEffect, useState} from "react";
import axios from "axios";
import {Box, Grid, useMediaQuery} from "@mui/material";
import Header from "./Header.tsx";
import ListContainer from "./ListContainer.tsx";
import FormularContainer from "./FormularContainer.tsx";
import {Animal, Type} from "./Utils.tsx";
import CategoryChips from "./CategoryChips.tsx";
import Footer from "./Footer.tsx";

function App() {
    const [animals, setAnimals] = useState<Animal[]>([]);
    const [editMode, setEditMode] = useState(false);
    const [animalId, setAnimalId] = useState("");
    const [currentAnimal, setCurrentAnimal] = useState<Animal>(
        {
            id: "",
            name: "",
            favoriteFood: "",
            type: Type.OTHER,
            dateOfBirth: "",
            imageUrl: "",
            AnimalUserid: ""
        }
    )
    const [showAnimalMode, setShowAnimalMode] = useState(false);


    useEffect(() => {
        axios.get('/api/animals')
            .then((response) => setAnimals(response.data))
            .catch((error) => console.error(error));
    }, []);

    const matches = useMediaQuery('(min-width:820px)');

    return (
        <Box sx={{flexGrow: 1, margin: 0}}>
            {matches ? (
                    <Grid container spacing={{xs: 2, md: 3}}>
                        <Header/>
                        <CategoryChips/>
                        <ListContainer
                            setAnimals={setAnimals}
                            animals={animals}
                            setAnimalId={setAnimalId}
                            setCurrentAnimal={setCurrentAnimal}
                            setShowAnimalMode={setShowAnimalMode}
                        />
                        <FormularContainer
                            setAnimals={setAnimals} animals={animals}
                            animalId={animalId} setEditMode={setEditMode}
                            editMode={editMode}
                            currentAnimal={currentAnimal}
                            setShowAnimalMode={setShowAnimalMode}
                            showAnimalMode={showAnimalMode}
                        />
                        <Footer/>
                    </Grid>)
                :
                (
                    <Grid container spacing={{xs: 2, md: 3}} columns={{xs: 4, sm: 8, md: 12}}
                          sx={{display: "flex", flexDirection: "column", pr: 0}}>
                        <Header/>
                        <CategoryChips/>
                        <FormularContainer
                            setAnimals={setAnimals} animals={animals}
                            animalId={animalId}
                            setEditMode={setEditMode}
                            editMode={editMode}
                            currentAnimal={currentAnimal}
                            setShowAnimalMode={setShowAnimalMode}
                            showAnimalMode={showAnimalMode}
                        />
                        <ListContainer
                            setAnimals={setAnimals}
                            animals={animals}
                            setAnimalId={setAnimalId}
                            setCurrentAnimal={setCurrentAnimal}
                            setShowAnimalMode={setShowAnimalMode}
                        />
                        <Footer/>
                    </Grid>)}
        </Box>
    )
}

export default App;
