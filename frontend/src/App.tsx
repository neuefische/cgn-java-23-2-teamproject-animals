import {useEffect, useState} from "react";
import axios from "axios";
import {Box, Grid} from "@mui/material";
import Header from "./Header.tsx";
import ListContainer from "./ListContainer.tsx";
import FormularContainer from "./FormularContainer.tsx";


function App() {
    const [animals, setAnimals] = useState([]);

    useEffect(() => {
        axios.get('/api/animals')
            .then((response) => setAnimals(response.data))
            .catch((error) => console.error(error));
    }, []);

    // function addAnimal() {
    //     axios.post("/api/animal", {
    //         name
    //     }).then((response) => setAnimals(response.data)).catch(error => console.log(error))
    //     setName("")
    //
    // }

    return (
        <Box sx={{flexGrow: 1}}>
            <Grid container spacing={3} sx={{mt: 0, ml: 0}}>
                <Header/>
                <ListContainer animals={animals} name={name}/>
                <FormularContainer addAnimal={addAnimal} name={name} setName={setName}/>
            </Grid>
        </Box>
    )
}

export default App;
