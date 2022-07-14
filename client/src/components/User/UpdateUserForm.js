import UserForm from "./UserForm";
import Navigation from "../Navigation";
import axios from "axios";
import { useNavigate, useParams, useLocation } from 'react-router-dom';
import { useState } from "react";

export default function UpdateUserForm() {
    const baseUrl = 'http://localhost:8080/api/v1/user/'
    const nav = useNavigate()
    const { id } = useParams()
    const location = useLocation()

    const [values, setValues] = useState(location.state)

    const handleSubmit = (event) => {
        event.preventDefault();
        const params = new URLSearchParams()
        params.append('firstName', values.firstName)
        params.append('lastName', values.lastName)
        params.append('gender', values.gender)
        params.append('age', values.age)
        params.append('emailAddress', values.emailAddress)
        params.append('phoneNumber', values.phoneNumber)

        axios.put(baseUrl + id, params)
        .then((res) => {
            nav('/user/' + res.data.id)
        })
        .catch((e) => {
            console.log(e)
        })
    }

    return(
        <>
            <Navigation />
            <UserForm user={values} setUser={setValues} request={handleSubmit} />
        </>
    )
}