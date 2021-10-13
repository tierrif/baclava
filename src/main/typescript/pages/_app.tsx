import type { AppProps } from 'next/app'
import Head from 'next/head'
import 'normalize.css'
import { ThemeProvider } from '@mui/material'
import theme from '../imports/theme'

const App = ({ Component, pageProps }: AppProps) => {
  return (
    <>
      <Head>
        <title>Baclava</title>
        <meta name='description' content='The bot that speaks to you like a human.' />
        <link rel='icon' href='/favicon.ico' />
      </Head>
      <ThemeProvider theme={theme}>
        <Component {...pageProps} />
      </ThemeProvider>
    </>
  )
}

export default App
