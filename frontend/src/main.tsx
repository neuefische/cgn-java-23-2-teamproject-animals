import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import {createTheme, CssBaseline, ThemeProvider} from "@mui/material";
import {orange, red, yellow} from "@mui/material/colors";


const theme = createTheme({
    palette: {
        primary: {
            main: orange[800],
        },
        secondary: {
            main: red[800],
        },
        background: {
            default: yellow[50]
        },
    },
    components: {
        MuiButton: {
            variants: [
                {
                    props: {variant: 'contained'},
                    style: {
                        textTransform: "none"
                    }
                }
            ]
        },
    },

});


ReactDOM.createRoot(document.getElementById('root') as HTMLElement).render(
    <React.StrictMode>
        <ThemeProvider theme={theme}>
            <CssBaseline/>
            <App/>
        </ThemeProvider>
    </React.StrictMode>,
)
