import {Paper, styled} from "@mui/material";

export type Animal = {
    id: string
    name: string
    favoriteFood: string
    type: Type
    dateOfBirth: string
    imageUrl: string
    AnimalUserid: string
}


export enum Type {
    DOG = "DOG",
    CAT = "CAT",
    RABBIT = "RABBIT",
    GUINEA_PIG = "GUINEA_PIG",
    HAMSTER = "HAMSTER",
    MICE = "MICE",
    RAT = "RAT",
    FERRET = "FERRET",
    BIRD = "BIRD",
    REPTILE = "REPTILE",
    FISH = "FISH",
    OTHER = "OTHER"
}

export const Item = styled(Paper)(({theme}) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    color: theme.palette.text.secondary,

}));