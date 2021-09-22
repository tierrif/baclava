import { AppBar, Box, Button, IconButton, Toolbar, Typography } from '@mui/material'
import MenuIcon from '@mui/icons-material/Menu'

const Nav = () => (
  <Box sx={{ flexGrow: 1 }}>
    <AppBar position='static'>
      <Toolbar>
        <IconButton
          size='large'
          edge='start'
          color='inherit'
          aria-label='menu'
          sx={{ mr: 2 }}
        >
          <MenuIcon color='secondary' style={{ fill: '#fff' }} />
        </IconButton>
        <Typography variant='h6' component='div' color='text.primary' sx={{ flexGrow: 1 }}>
          Baclava
        </Typography>
        <Button color='secondary' style={{ color: '#fff' }}>Login</Button>
      </Toolbar>
    </AppBar>
  </Box>
)

export default Nav
