import {Button, Grid, List, Paper, Typography} from "@mui/material";
import VisibilityIcon from '@mui/icons-material/Visibility';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import {Animal, Item} from "./Utils.tsx";

type Props = {

    animals: Animal[]
}


function ListContainer({animals}: Props) {
    return (
        <Grid item xs={5} sx={{mr: 2}}>
            <Item sx={{height: "100vh", backgroundColor: "#35baf6"}}>
                <List>
                    {
                        animals.map((animal) => (

                            <Paper key={animal.id} sx={{
                                height: 50,
                                display: "flex",
                                justifyContent: "space-between",
                                alignItems: "center",
                                padding: 2,
                                margin: 2
                            }}>
                                <Typography>{animal.name}</Typography>
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
                                <Button variant="contained"
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