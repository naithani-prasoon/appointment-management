import UserForm from "./UserForm";
import Navigation from "../Navigation";
import axios from "axios";
import { useNavigate, useParams, useLocation } from 'react-router-dom';
import { useState } from "react";

//setPopup : true, passed as true and when done with form turn false
export default function UpdateUserForm({passedUser, setPopup, edit}) {
    const baseUrl = 'http://localhost:8080/api/v1/user/'
    const nav = useNavigate()
    const { id } = useParams()
    //const location = useLocation()

    const [user, setUser] = useState(passedUser)

    const handleSubmit = (event) => {
        event.preventDefault();
        const params = new URLSearchParams()
        params.append('firstName', user.firstName)
        params.append('lastName', user.lastName)
        params.append('gender', user.gender)
        params.append('age', user.age)
        params.append('emailAddress', user.emailAddress)
        params.append('phoneNumber', user.phoneNumber)

        axios.put(baseUrl + user.id, params)
        .then((res) => {
            //nav('/user/' + res.data.id)
            setPopup(false)
        })
        .catch((e) => {
            console.log(e)
        })
    }

    return(
        <>
            <UserForm user={user} setUser={setUser} request={handleSubmit} setPopup={setPopup} edit={edit} />
        </>
    )
}