import React, { useState } from "react"
import Button from 'react-bootstrap/Button';
import axios from "axios";
import Form from 'react-bootstrap/Form';


export default function UserForm() {
    const [values, setValues] = useState({
        firstName: "",
        lastName: "",
        age: "",
        gender: "",
        email: "",
        phoneNumber: "",
    })

    const baseUrl = 'http://localhost:8080/api/v1/user/'

    const handleInputChange = (event) => {
          setValues((values) => ({
            ...values,
            [event.target.name]: event.target.value,
          }));
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        const params = new URLSearchParams()
        params.append('firstName', values.firstName)
        params.append('lastName', values.lastName)
        params.append('gender', values.gender)
        params.append('age', values.age)
        params.append('emailAddress', values.email)
        params.append('phoneNumber', values.phoneNumber)

        axios.post(baseUrl, params)
    }

    return(
        <div className='App d-flex flex-column align-items-center'>
        <Form onSubmit={handleSubmit}>
            <Form.Group>
                <Form.Label>First Name</Form.Label>
                <Form.Control type='text' name="firstName" value={values.firstName} onChange={handleInputChange} />
            </Form.Group>
            <Form.Group>
                <Form.Label>Last Name</Form.Label>
                <Form.Control type='text' name="lastName" value={values.lastName} onChange={handleInputChange} />
            </Form.Group>
            <Form.Group>
                <Form.Label>Gender</Form.Label>
                <Form.Control as='select' name="gender" value={values.gender}  onChange={handleInputChange}>
                    <option value='MALE'>Male</option>
                    <option value='FEMALE'>Female</option>
                    <option value='OTHER'>Other</option>
                </Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Age</Form.Label>
                <Form.Control type='number' name="age" value={values.age} onChange={handleInputChange}/>
            </Form.Group>
            <Form.Group>
                <Form.Label>Email</Form.Label>
                <Form.Control type='email' name="email" value={values.email} onChange={handleInputChange}/>
            </Form.Group>
            <Form.Group>
                <Form.Label>Phone Number</Form.Label>
                <Form.Control type='text' name="phoneNumber" value={values.phoneNumber} onChange={handleInputChange}/> 
            </Form.Group>
            <Button type='submit'>Submit</Button>
        </Form>
        </div>
    )
}