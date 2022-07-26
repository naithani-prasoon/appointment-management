import {useState} from "react";
import EditIcon from '../Assets/edit.svg'
import DeleteIcon from '../Assets/delete.svg'
import CalendarIcon from '../Assets/calendar.svg'
import './UserList.css'


/*  props
    users : users(list ot users)
    handleSetUsers: setUsers
*/
export default function UserItem(props) {
    const [firstNameFilter, setFirstNameFilter] = useState(false)
    const [lastNameFilter, setLastNameFilter] = useState(false)
    const [ageFilter, setAgeFilter] = useState(false)

    const sortByFirstName = () => {
        setFirstNameFilter(!firstNameFilter)
        if (firstNameFilter) {
            props.handleSetUsers([...props.users].sort((a, b) => 
                a.firstName > b.firstName ? 1 : -1,
                ))
        } else {
            props.handleSetUsers([...props.users].sort((a, b) => 
                a.firstName > b.firstName ? -1 : 1,
                ))
        }
    }

    const sortByLastName = () => {
        setLastNameFilter(!lastNameFilter)
        if (lastNameFilter) {
            props.handleSetUsers([...props.users].sort((a, b) => 
                a.lastName > b.lastName ? 1 : -1,
                ))
        } else {
            props.handleSetUsers([...props.users].sort((a, b) => 
                a.lastName > b.lastName ? -1 : 1,
                ))
        }
    }

    const sortByAge = () => {
        setAgeFilter(!ageFilter)
        if (ageFilter) {
            props.handleSetUsers([...props.users].sort((a, b) => 
                a.age > b.age ? 1 : -1,
                ))
        } else {
            props.handleSetUsers([...props.users].sort((a, b) => 
                a.age > b.age ? -1 : 1,
                ))
        }
    }
    
    return (
        <div className="table">
            <div className="action-buttons">
                <button onClick={props.handleCreateUser}>Create User</button>
            </div>  
            <div className="table-header">
                <h2 onClick={sortByFirstName} style={{cursor:"pointer"}}>First Name</h2>
                <h2 onClick={sortByLastName} style={{cursor:"pointer"}}>Last Name</h2>
                <h2 onClick={sortByAge} style={{cursor:"pointer"}}>Age</h2>
                <h2>Gender</h2>
                <h2>Email</h2>
                <h2>Phone</h2>
                <h2></h2>
                <h2></h2>
                <h2></h2> 
            </div>
            
            {props.users.map((user) => (
                <div className="table-appointmentsDto">
                    <h2 style={{textAlign:"center"}}>{user.firstName}</h2>
                    <h2 style={{textAlign:"center"}}>{user.lastName}</h2>
                    <h2>{user.age}</h2>
                    <h2>{user.gender}</h2>
                    <h2 style={{textAlign:"left"}}>{user.emailAddress}</h2>
                    <h2>{user.phoneNumber}</h2>

                    <img src={EditIcon} onClick={() => props.handleUpdateUser(user)} />
                    <img src={DeleteIcon} onClick={() => props.handleDeleteUserPopup(user)} />
                    <img src={CalendarIcon} onClick={() => props.handleAppointment(user.id, user.firstName)} />
                </div>
            ))}
        </div>
    )
}
