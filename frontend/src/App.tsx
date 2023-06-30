import {useEffect, useState} from "react";
import {Animal} from "./Animal.tsx";
import axios from "axios";


function App() {
    const [animals, setAnimals] = useState([]);
    const [name, setName] = useState("")
    useEffect(() => {
        axios.get('/api/animals')
            .then((response) => setAnimals(response.data))
            .catch((error) => console.error(error));
    }, []);

    function addAnimal() {
        axios.post("/api/animal", {
            id: animals.length + 1,
            name
        }).then((response) => console.log(response.data)).catch(error => console.log(error))
    }

    return (
        <>
            <div>
                <form onSubmit={addAnimal}>
                    <input type="text" value={name} onChange={(event) =>
                        setName(event.target.value)}/>
                    <button>add animal</button>
                </form>
                {animals.map((animal: Animal) => (<div key={animal.id}>{animal.name}</div>))}
            </div>
        </>
    )
}

export default App;
