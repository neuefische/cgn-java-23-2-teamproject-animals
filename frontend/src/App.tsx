import {useEffect, useState} from "react";
import {Animal} from "./Animal.tsx";
import axios from "axios";
import {Box, Grid} from "@mui/material";
import Header from "./Header.tsx";
import ListContainer from "./ListContainer.tsx";
import FormularContainer from "./FormularContainer.tsx";


function App() {
    const [animals, setAnimals] = useState([]);
    const [name, setName] = useState("")
    useEffect(() => {
        axios.get('/api/animals')
            .then((response) => setAnimals(response.data))
            .catch((error) => console.error(error));
    }, []);

    function addAnimal() {
        axios.post("/api/animal", {
            id: animals.length + 1,
            name
        }).then((response) => console.log(response.data)).catch(error => console.log(error))
    }

    return (
        <Box sx={{flexGrow: 1}}>
            <Grid container spacing={3} sx={{mt: 0, ml: 0}}>
                <Header/>
                <ListContainer/>
                <FormularContainer/>
            </Grid>
        </Box>
    )
}

export default App;
