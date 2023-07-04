import {useEffect, useState} from "react";
import axios from "axios";
import {Box, Grid} from "@mui/material";
import Header from "./Header.tsx";
import ListContainer from "./ListContainer.tsx";
import FormularContainer from "./FormularContainer.tsx";
import {Animal} from "./Utils.tsx";


function App() {
    const [animals, setAnimals] = useState<Animal[]>([]);

    useEffect(() => {
        axios.get('/api/animals')
            .then((response) => setAnimals(response.data))
            .catch((error) => console.error(error));
    }, []);

    console.log(animals)


    return (
        <Box sx={{flexGrow: 1}}>
            <Grid container spacing={3} sx={{mt: 0, ml: 0}}>
                <Header/>
                <ListContainer setAnimals={setAnimals} animals={animals}/>

                <FormularContainer setAnimals={setAnimals}/>
            </Grid>
        </Box>
    )
}

export default App;
