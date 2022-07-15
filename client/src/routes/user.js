import { useState, useEffect } from "react"
import { useParams, useNavigate  } from 'react-router-dom';
import Navigation from "../components/Navigation";
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
    const navigate = useNavigate()
    const url = `http://localhost:8080/api/v1/user/`

    useEffect(() => {
        axios.get(url + id).then((res) => {
            setData(res.data)
        })
        .catch((err) => { 
            console.log(err)
        })
    }, [])

    const deleteUser = () => {
        axios.delete(url + id)
        navigate('/users')
    }

    const updateUser = () => {
        navigate('/update/' + id, {state : user})
    }

    return(
        <>
            <Navigation />
            <h1>{user.firstName} {user.lastName}</h1>
            <button onClick={updateUser}>Edit</button>
            <button onClick={deleteUser}>Delete</button>
        </>
    )
}