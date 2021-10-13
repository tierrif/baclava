import { AppBar, Box, Button, Toolbar, Typography } from '@mui/material'
import { NavProps } from '../types'
import config from '../../../../../frontendConfig.json'
import { useRouter } from 'next/router'

const Nav = ({ active }: NavProps) => {
  const router = useRouter()

  const handleLogout = async () => {
    const token = localStorage.getItem('baclava-token')
    console.log(token)
    if (token === null) return
    await fetch(`${config.baseUri}logout`, { headers: { Authorization: token } })
    localStorage.clear()
    await router.push('/')
  }

  const handleLogin = async () => {
    window.location.href = await fetch(`${config.baseUri}login`)
      .then(async (res) => await res.text())
  }

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position='static'>
        <Toolbar>
          <Typography variant='h6' component='div' color='text.primary' sx={{ flexGrow: 1, fontWeight: 'bold' }}>
            Baclava
          </Typography>
          {active === 'dashboard'
            ? <Button color='secondary' style={{ color: '#fff' }} onClick={handleLogout}>Logout</Button>
            : <Button color='secondary' style={{ color: '#fff' }} onClick={handleLogin}>Login</Button>}
        </Toolbar>
      </AppBar>
    </Box>
  )
}

export default Nav
