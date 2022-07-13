import { useState, useEffect } from "react"
import { useParams } from 'react-router-dom';
import Navigation from "../Navigation";
import axios from 'axios'

const defaultUser = {
    firstName : "",
    lastName : "",
    age : "",
    emailAddress : "",
    phoneNumber : ""
}

export default function User() {
    const [user, setData] = useState(defaultUser)
    const { id } = useParams()
    const url = `http://localhost:8080/api/v1/user/`

    useEffect(() => {
        axios.get(url + id).then((res) => {
            setData(res.data)
        })
        .catch((err) => { 
            console.log(err)
        })
    }, [])

    return(
        <>
            <Navigation />
            <h1>{user.firstName} {user.lastName}</h1>
        </>
    )
}