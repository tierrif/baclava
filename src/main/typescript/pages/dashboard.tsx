import type { NextPage } from 'next'
import Nav from '../imports/components/Nav'
import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'
import config from '../../../../frontendConfig.json'
const Dashboard: NextPage = () => {
  const router = useRouter()
  const [state, setState] = useState({})

  useEffect(() => {
    let token = localStorage.getItem('baclava-token')
    if (token === null) {
      const params = new URLSearchParams(window.location.search)
      token = params.get('token')
      if (token !== null) localStorage.setItem('baclava-token', token)
      else {
        router.push('/')
        return
      }
    }

    // Remove any query params.
    router.push(router.asPath.split('?')[0])

    fetch(`${config.baseUri}messages`, { headers: { Authorization: token } })
      .then(async (res) => await res.json())
      .catch(console.log)
      .then((bundle) => {
        setState(bundle)
      })
  }, [])

  return (
    <Nav active='dashboard' />
  )
}

export default Dashboard
