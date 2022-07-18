import React from "react"
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import './UserForm.css'


export default function UserForm({user, setUser, request, setPopup, setCreateUser, edit}) {

    const handleInputChange = (event) => {
          setUser((user) => ({
            ...user,
            [event.target.name]: event.target.value,
          }));
    }

    const handleCreateUser = () => {
        setPopup(false)
        setCreateUser(false)
    }

    return(
        <form onSubmit={request}>
            <h1>{edit ? "Create User" : "Update User"}</h1>
            <div className="form-sections">
                <div className="form-divider">
                    <label>First Name</label>
                    <input type={"text"} name={"firstName"} value={user.firstName} onChange={handleInputChange}/>
                </div>
                <div className="form-divider">
                    <label>Last Name</label>
                    <input type={"text"} name={"lastName"} value={user.lastName} onChange={handleInputChange}/>
                </div>
            </div>

            <div className="form-sections">
                <div className="form-divider">
                    <label>Gender</label>
                    <select name="gender">
                        <option value='MALE'>Male</option>
                        <option value='FEMALE'>Female</option>
                        <option value='OTHER'>Other</option>
                    </select>
                </div>
                <div className="form-divider">
                    <label>Age</label>
                    <input type="number" name={"age"} value={user.age} onChange={handleInputChange}/>
                </div>
            </div>

            <div className="form-sections">
                <div className="form-divider">
                    <label>Email</label>
                    <input type={"text"} name={"emailAddress"} value={user.emailAddress} onChange={handleInputChange}/>
                </div>
                <div className="form-divider">
                    <label>Phone</label>
                    <input type={"text"} name={"phoneNumber"} value={user.phoneNumber} onChange={handleInputChange}/>
                </div>
            </div>

            <div className="form-sections">
                <button type="submit">Submit</button>
                <button type="button" onClick={handleCreateUser}>Close</button>
            </div>

        </form>
    )
}

/* <div className='App d-flex flex-column align-items-center'>
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
                <Form.Control type='email' name="emailAddress" value={user.emailAddress} onChange={handleInputChange}/>
            </Form.Group>
            <Form.Group>
                <Form.Label>Phone Number</Form.Label>
                <Form.Control type='text' name="phoneNumber" value={user.phoneNumber} onChange={handleInputChange}/> 
            </Form.Group>
            <Button type='submit'>Submit</Button>
            <button>Close</button>
        </Form>
        </div> */