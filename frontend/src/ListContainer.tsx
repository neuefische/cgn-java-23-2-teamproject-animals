import {Grid, List, Paper, styled} from "@mui/material";
import {Animal} from "./Utils.tsx";
import axios from "axios";
import AnimalCard from "./AnimalCard.tsx";

type Props = {
    animals: Animal[],
    setAnimals: React.Dispatch<React.SetStateAction<Animal[]>>

}


const Item = styled(Paper)(({theme}) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
    margin: 4
}));


function ListContainer({animals, setAnimals}: Props) {

    function deleteAnimal(id:( string | null)) {
        axios.delete(`/api/animals/${id}`)
            .then(() => {
                setAnimals(animals.filter((currentanimal) => currentanimal.id !== id))
            }).catch(error => console.log(error))
    }

    return (
        <Grid item xs={5} sx={{mr: 2}}>
            <Item sx={{height: "100vh", backgroundColor: "#35baf6"}}>
                <List>
                    {
                        animals?.map((animal) => (
                            <AnimalCard
                                key={animal.id}
                                animal={animal}
                                deleteAnimal={deleteAnimal}
                            />
                        ))
                    }
                </List>
            </Item>
        </Grid>
    );
}

export default ListContainer;