import {useEffect, useState} from "react";
import {Animal} from "./Animal.tsx";
import axios from "axios";


function App() {
    const [animals, setAnimals] = useState([]);

    useEffect(() => {
        axios.get('/api/animals')
            .then((response) => setAnimals(response.data))
            .catch((error) => console.error(error));
    }, []);

    return (
    <><div>
        {animals.map((animal:Animal)=>(<div>{animal.name}</div>))}
    </div>
    </>
    )
}

export default App;
