import {useEffect, useState} from "react";
import axios from "axios";
import {Box, Grid, useMediaQuery} from "@mui/material";
import Header from "./Header.tsx";
import ListContainer from "./ListContainer.tsx";
import FormularContainer from "./FormularContainer.tsx";
import {Animal} from "./Utils.tsx";
import CategoryChips from "./CategoryChips.tsx";
import Footer from "./Footer.tsx";
import LoginPage from "./LoginPage.tsx";


function App() {
    const [animals, setAnimals] = useState<Animal[]>([]);
    const [editMode, setEditMode] = useState(false);
    const [animalId, setAnimalId] = useState("");


    useEffect(() => {
        axios.get('/api/animals')
            .then((response) => setAnimals(response.data))
            .catch((error) => console.error(error));
    }, []);

    const matches = useMediaQuery('(min-width:820px)');

    return (
        <Box sx={{flexGrow: 1, margin: 0}} onClick={() => setEditMode(false)}>
            {matches ? (
                    <Grid container spacing={{xs: 2, md: 3}}>
                        <Header/>
                        <LoginPage/>
                        <CategoryChips/>
                        <ListContainer setAnimals={setAnimals} animals={animals} setAnimalId={setAnimalId}/>
                        <FormularContainer
                            setAnimals={setAnimals} animals={animals}
                            animalId={animalId} setEditMode={setEditMode}
                            editMode={editMode}
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
                        />
                        <ListContainer setAnimals={setAnimals} animals={animals} setAnimalId={setAnimalId}/>

                        <Footer/>
                    </Grid>)}

        </Box>


    )
}

export default App;
