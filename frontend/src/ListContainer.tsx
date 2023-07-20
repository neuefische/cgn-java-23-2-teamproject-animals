import {Grid, List} from "@mui/material";
import {Animal, Item} from "./Utils.tsx";
import axios from "axios";
import AnimalCard from "./AnimalCard.tsx";


type Props = {
    animals: Animal[],
    setAnimals: React.Dispatch<React.SetStateAction<Animal[]>>
    setAnimalId: React.Dispatch<React.SetStateAction<string>>
    setCurrentAnimal: React.Dispatch<React.SetStateAction<Animal>>
    setShowAnimalMode: React.Dispatch<React.SetStateAction<boolean>>
}


function ListContainer({animals, setAnimals, setAnimalId, setCurrentAnimal, setShowAnimalMode}: Props) {

    function deleteAnimal(id: string) {
        axios.delete(`/api/animals/${id}`)
            .then(() => {
                setAnimals(animals.filter((currentAnimal) => currentAnimal.id !== id))
            }).catch(error => console.log(error))
    }

    return (
        <Grid item xs={5}>
            <Item variant="outlined" sx={{minHeight: 300, margin: 4}}>
                <List>
                    {
                        animals?.map((animal) => (
                            <AnimalCard
                                key={animal.id}
                                animal={animal}
                                deleteAnimal={deleteAnimal}
                                setAnimalId={setAnimalId}
                                setCurrentAnimal={setCurrentAnimal}
                                setShowAnimalMode={setShowAnimalMode}
                            />
                        ))
                    }
                </List>
            </Item>
        </Grid>
    );
}

export default ListContainer;