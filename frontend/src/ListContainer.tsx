import {Button, Grid, List, Paper, styled, Typography} from "@mui/material";
import VisibilityIcon from '@mui/icons-material/Visibility';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

const Item = styled(Paper)(({theme}) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,
    margin: 4
}));

function ListContainer() {
    return (
        <Grid xs={5} sx={{mr: 2}}>
            <Item sx={{height: "100vh", backgroundColor: "#35baf6"}}>
                <List>
                    <Paper sx={{
                        height: 50,
                        display: "flex",
                        justifyContent: "space-between",
                        alignItems: "center",
                        padding: 2
                    }}>
                        <Typography>Name von Tier hdjdkjdkjdkdkkd</Typography>
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
                </List>
            </Item>
        </Grid>
    );
}

export default ListContainer;