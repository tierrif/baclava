import type { NextPage } from 'next'
import Nav from '../imports/components/Nav'
import { useEffect, useState } from 'react'
import { useRouter } from 'next/router'
import config from '../config.json'

const Dashboard: NextPage = () => {
  const router = useRouter()
  const [messages, setMessages] = useState([])

  useEffect(() => {
    if ((localStorage.getItem('baclava-token')) === null) {
      void router.push('/')
      return
    }

    fetch(`${config.baseUri}auth`)
      .then((res) => res.json())
      .then((user) => {

      })
  }, [])

  return (
    <Nav active='dashboard' />
  )
}

export default Dashboard
