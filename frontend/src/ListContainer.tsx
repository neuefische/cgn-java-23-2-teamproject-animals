import {Button, Grid, List, Paper, styled, Typography} from "@mui/material";
import VisibilityIcon from '@mui/icons-material/Visibility';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Animal} from "./Utils.tsx";
import axios from "axios";

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

    function deleteAnimal(id: string) {
        axios.delete(`/api/animals/${id}`)
            .then((response) => {
                setAnimals(animals.filter((currentanimal) => currentanimal.id !== id))
                console.log(response.data)
            }).catch(error => console.log(error))

    }

    return (
        <Grid item xs={5} sx={{mr: 2}}>
            <Item sx={{height: "100vh", backgroundColor: "#35baf6"}}>
                <List>
                    {
                        animals?.map(animal => (
                            <Paper key={animal.id} sx={{
                                height: 50,
                                display: "flex",
                                justifyContent: "space-between",
                                alignItems: "center",
                                padding: 2,
                                margin: 2
                            }}>
                                <Typography> {animal.name}</Typography>
                                <Button variant="contained"
                                        size="small"
                                        sx={{textTransform: "none", margin: 1}}
                                        startIcon={<VisibilityIcon/>}
                                >Show</Button>
                                <Button variant="contained"
                                        size="small"
                                        sx={{textTransform: "none", margin: 1}}
                                        startIcon={<EditIcon/>}
                                >Edit</Button>
                                <Button onClick={() => deleteAnimal(animal.id)} variant="contained"
                                        size="small"
                                        sx={{textTransform: "none", margin: 1}}
                                        startIcon={<DeleteIcon/>}
                                >Delete</Button>
                            </Paper>
                        ))
                    }
                </List>
            </Item>
        </Grid>
    );
}

export default ListContainer;