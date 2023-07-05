import {Button, Card, CardActions, CardContent, Typography} from "@mui/material";
import VisibilityIcon from '@mui/icons-material/Visibility';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Animal} from "./Utils.tsx";

type Props = {
    animal: Animal,
    deleteAnimal: (id: string| null) => void
}


function AnimalCard({animal, deleteAnimal}: Props) {
    return (
        <Card sx={{minWidth: 50, mb: 2}}>
            <CardContent>

                <Typography sx={{fontSize: 14}} color="text.secondary">
                    Name: {animal.name}
                </Typography>
            </CardContent>
            <CardContent sx={{
                '@media (max-width: 820px)': {
                    display: "flex",
                    flexDirection: "column",
                    width: '80%'
                },
                display: "flex", alignItems: "center"
            }}>
                <CardActions>
                    <Button
                        variant="contained"
                        startIcon={<VisibilityIcon/>}
                        size="small" sx={{textTransform: "none"}}>show animal</Button>
                </CardActions>
                <CardActions>
                    <Button
                        variant="contained"
                        startIcon={<EditIcon/>}
                        size="small" sx={{textTransform: "none"}}>edit</Button>
                </CardActions>
                <CardActions>
                    <Button
                        onClick={() => deleteAnimal(animal.id)}
                        variant="contained"
                        startIcon={<DeleteIcon/>}
                        size="small" sx={{textTransform: "none"}}>delete</Button>
                </CardActions>
            </CardContent>
        </Card>
    );
}

export default AnimalCard;