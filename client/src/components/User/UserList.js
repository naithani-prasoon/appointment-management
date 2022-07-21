import axios from "axios";
import UserItem from "./UserItem";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router";
import './UserList.css'
import CreateUserForm from "./CreateUserForm";
import UpdateUserForm from './UpdateUserForm'
import UserPopup from './UserPopup'


/*  props
    users : users(list ot users)
    handleSetUsers: setUsers
*/
export default function UserList(props) {
    const baseUrl = 'http://localhost:8080/api/v1/user/'

    //pop up states
    const [popup, setPopup] = useState(false)
    const [currentUser, setCurrentUser] = useState(props.users[0])
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
            props.handleSetUsers(Object.values(res.data))
        })).catch((err) => { 
            console.log(err)
        })
    }, [change, popup]) 

    return(
        <>
        <UserItem 
            users={props.users}
            handleSetUsers={props.handleSetUsers}
            handleCreateUser={handleCreateUser}
            handleUpdateUser={handleUpdateUser}
            handleDeleteUserPopup={handleDeleteUserPopup}
            handleAppointment={handleAppointment} />
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
        </>
    )
}