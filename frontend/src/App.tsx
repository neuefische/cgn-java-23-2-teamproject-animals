import {useEffect, useState} from "react";
import {Animal} from "./Animal.tsx";


function App() {
    const [animals, setAnimals] = useState([]);

    useEffect(() => {
        fetch('/api/animals')
            .then((response) => response.json())
            .then((data) => setAnimals(data))
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
