import React from "react"
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';


export default function UserForm({user, setUser, request}) {

    const handleInputChange = (event) => {
          setUser((user) => ({
            ...user,
            [event.target.name]: event.target.value,
          }));
    }

    return(
        <div className='App d-flex flex-column align-items-center'>
        <Form onSubmit={request}>
            <Form.Group>
                <Form.Label>First Name</Form.Label>
                <Form.Control type='text' name="firstName" value={user.firstName} onChange={handleInputChange} />
            </Form.Group>
            <Form.Group>
                <Form.Label>Last Name</Form.Label>
                <Form.Control type='text' name="lastName" value={user.lastName} onChange={handleInputChange} />
            </Form.Group>
            <Form.Group>
                <Form.Label>Gender</Form.Label>
                <Form.Control as='select' name="gender" value={user.gender}  onChange={handleInputChange}>
                    <option value='MALE'>Male</option>
                    <option value='FEMALE'>Female</option>
                    <option value='OTHER'>Other</option>
                </Form.Control>
            </Form.Group>
            <Form.Group>
                <Form.Label>Age</Form.Label>
                <Form.Control type='number' name="age" value={user.age} onChange={handleInputChange}/>
            </Form.Group>
            <Form.Group>
                <Form.Label>Email</Form.Label>
                <Form.Control type='email' name="email" value={user.email} onChange={handleInputChange}/>
            </Form.Group>
            <Form.Group>
                <Form.Label>Phone Number</Form.Label>
                <Form.Control type='text' name="phoneNumber" value={user.phoneNumber} onChange={handleInputChange}/> 
            </Form.Group>
            <Button type='submit'>Submit</Button>
        </Form>
        </div>
    )
}