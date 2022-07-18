import UserForm from "./UserForm";
import axios from "axios";
import { useNavigate  } from 'react-router-dom';
import { useState } from "react";


//setPopup : true, passed as true and when done with form turn false
export default function CreateUserForm({setPopup, setCreateUser, edit}) {
    const [values, setValues] = useState({
        firstName: "",
        lastName: "",
        age: "",
        gender: "MALE",
        email: "",
        phoneNumber: "",
    })
    
    const baseUrl = 'http://localhost:8080/api/v1/user/'
    const nav = useNavigate()

    const handleSubmit = (event) => {
        event.preventDefault();
        const params = new URLSearchParams()
        params.append('firstName', values.firstName)
        params.append('lastName', values.lastName)
        params.append('gender', values.gender)
        params.append('age', values.age)
        params.append('emailAddress', values.emailAddress)
        params.append('phoneNumber', values.phoneNumber)
        axios.post(baseUrl, params)
        .then((res) => {
            setPopup(false)
            setCreateUser(false)
        }).catch((e) => {
            console.log(e)
        })
    }

    return (
        <>
            <UserForm user={values} setUser={setValues} request={handleSubmit} setPopup={setPopup} setCreateUser={setCreateUser} edit={edit} />
        </>
    )

}