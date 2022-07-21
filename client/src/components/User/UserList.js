import axios from "axios";
import UserItem from "./UserItem";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router";
import './UserList.css'
import EditIcon from '../Assets/edit.svg'
import DeleteIcon from '../Assets/delete.svg'
import CalendarIcon from '../Assets/calendar.svg'
import CreateUserForm from "./CreateUserForm";
import UpdateUserForm from './UpdateUserForm'
import UserPopup from './UserPopup'


/*  props
    users : users
    usersChangeHandler: setUsers
*/
const defaultUser = {
    id: "",
    firstName : "",
    lastName : "",
    age : "",
    emailAddress : "",
    phoneNumber : ""
}
export default function UserList(props) {
    const baseUrl = 'http://localhost:8080/api/v1/user/'

    //pop up states
    const [popup, setPopup] = useState(false)
    const [currentUser, setCurrentUser] = useState(defaultUser)
    const [createUser, setCreateUser] = useState(false)
    const [change, setChange] = useState(false)
    const [deleteUserPopup, setdeleteUserPopup] = useState(false)
    const nav = useNavigate()


    const handleCreateUser = () => {
        setPopup(true)
        setCreateUser(true)
    }

    const handleUpdateUser = (user) => {
        setCurrentUser(user)
        setPopup(true)
    }

    const handleDeleteUserPopup = (user) => {
        setdeleteUserPopup(true)
        setCurrentUser(user)
    }

    const handleDeleteUser = (id) => {
        axios.delete(baseUrl + id).then((res) => {
            setChange(!change)
            setdeleteUserPopup(false)
        })
    }

    const handleAppointment = (id, name) => {
        nav('/dashboard',{state: {userId: id, userName: name}})
    }

    useEffect(() => {
        axios.get(baseUrl + 'all').then((res => {
            props.usersChangeHandler(Object.values(res.data))
        })).catch((err) => { 
            console.log(err)
        })
    }, [change, popup]) 

    return(
        <div className="table">
            <div className="action-buttons">
                <button onClick={handleCreateUser}>Create User</button>
            </div>  
            <div className="table-header">
                <h2>Name</h2>
                <h2>Age</h2>
                <h2>Gender</h2>
                <h2>Email</h2>
                <h2>Phone</h2>
                <h2></h2>
                <h2></h2> 
            </div>
            
            {props.users.map((user, idx) => (
                <div className="table-appointments">
                    <h2 style={{textAlign:"center"}}>{user.firstName + " " + user.lastName}</h2>
                    <h2>{user.age}</h2>
                    <h2>{user.gender}</h2>
                    <h2>{user.emailAddress}</h2>
                    <h2>{user.phoneNumber}</h2>

                    <img src={EditIcon} onClick={() => handleUpdateUser(user)} />
                    <img src={DeleteIcon} onClick={() => handleDeleteUserPopup(user)} />
                    <img src={CalendarIcon} onClick={() => handleAppointment(user.id, user.firstName)} />
                </div>
            ))}
            {
                popup ?
                    <div className="edit-popup">
                        <div className="popup">
                            {
                                createUser ? <CreateUserForm setPopup={setPopup} setCreateUser={setCreateUser} edit={true}/> :
                                            <UpdateUserForm passedUser={currentUser} setPopup={setPopup} edit={false} />
                            }
                        </div>
                    </div>
                    :
                    <></>                            
            }
            {
                deleteUserPopup ?
                <div className="edit-popup">
                    <div className="popup">
                        <UserPopup setPopup={setdeleteUserPopup} request={handleDeleteUser} userId={currentUser.id}  />
                    </div>
                </div> 
                : 
                <></>
            }
        </div>
    )
}