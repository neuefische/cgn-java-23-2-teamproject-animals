import {FormEvent, useState} from 'react';
import axios from "axios";

function LoginPage() {
    const[username, setUsername]= useState<string>("")
    const[password, setPassword]= useState<string>("")

    function onLogin(event:FormEvent){
        event.preventDefault()
        axios.post("/api/users/Login",null,{auth:{username,password}})
            .then(console.log)
    }

    return (
        <form onSubmit={onLogin}>
            <p>Login</p>
            <input value={username} onChange={event => setUsername(event.target.value)} placeholder= "Username"/>
            <input value={password} onChange={event => setPassword(event.target.value)}placeholder= "Password" type = "password"/>
            <button>Login</button>

        </form>
    );
}

export default LoginPage;