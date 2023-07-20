import {Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import VisibilityIcon from '@mui/icons-material/Visibility';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Animal} from "./Utils.tsx";

type Props = {
    animal: Animal,
    deleteAnimal: (id: string) => void
    setAnimalId: React.Dispatch<React.SetStateAction<string>>
    setCurrentAnimal: React.Dispatch<React.SetStateAction<Animal>>
    setShowAnimalMode: React.Dispatch<React.SetStateAction<boolean>>
}


function AnimalCard({animal, deleteAnimal, setAnimalId, setCurrentAnimal, setShowAnimalMode}: Props) {

    function handleEditClick(id: string) {
        setAnimalId(id)
    }


    return (
        <Card sx={{minWidth: 50, mb: 2}}>
            <CardContent>
                <Typography sx={{fontSize: 14}} color="text.secondary">
                    Name: {animal.name}
                </Typography>
                <Typography sx={{fontSize: 14}} color="text.secondary">
                    favorite food: {animal.favoriteFood}
                </Typography>
                <Typography sx={{fontSize: 14}} color="text.secondary">
                    type: {animal.type}
                </Typography>
                <Typography sx={{fontSize: 14}} color="text.secondary">
                    date of birth: {animal.dateOfBirth}
                </Typography>
            </CardContent>
            <CardContent sx={{
                display: "flex", alignItems: "center", p: 0
            }}>
                <CardActions>
                    <Button
                        onClick={() => {
                            setCurrentAnimal(animal)
                            setShowAnimalMode(prev => !prev)
                        }}
                        variant="contained"
                        startIcon={<VisibilityIcon/>}
                        size="small" sx={{textTransform: "none"}}>show animal</Button>
                </CardActions>
                <CardActions>
                    <Button
                        onClick={() => handleEditClick(animal.id)}
                        variant="contained"
                        color="success"
                        startIcon={<EditIcon/>}
                        size="small" sx={{textTransform: "none"}}>edit</Button>
                </CardActions>
                <CardActions>
                    <Button
                        onClick={() => deleteAnimal(animal.id)}
                        variant="contained"
                        color="secondary"
                        startIcon={<DeleteIcon/>}
                        size="small" sx={{textTransform: "none"}}>delete</Button>
                </CardActions>
            </CardContent>
        </Card>
    );
}

export default AnimalCard;