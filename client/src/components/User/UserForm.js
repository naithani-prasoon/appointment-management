import React from "react"
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