import axios from "axios";
import UserItem from "./UserItem";
import { useState, useEffect } from "react";


export default function UserList() {
    const [users, setUsers] = useState([])
    const url = 'http://localhost:8080/api/v1/user/all'

    useEffect(() => {
        axios.get(url).then((res => {
            setUsers(Object.values(res.data))
        }))
    }, []) 

    return(
        <>
            {users.map((user, idx) => {
                return (
                    <UserItem key={idx} user={user} />
                )
            })}
        </>
    )
}