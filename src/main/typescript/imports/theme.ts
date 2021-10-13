import { grey, orange, yellow } from '@mui/material/colors'
import { createTheme } from '@mui/material/styles'

const theme = createTheme({
  palette: {
    primary: orange,
    secondary: yellow,
    text: {
      primary: '#fff'
    },
    grey
  }
})

export default theme
