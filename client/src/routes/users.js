import Navigation from "../components/Navigation";
import SearchUser from "../components/User/SearchUser";
import UserList from "../components/User/UserList";
import { useState } from "react";

export default function Users() {
    const [users, setUsers] = useState([])

    return(
        <>
            <Navigation />
            <SearchUser users={users} usersChangeHandler={setUsers} />
            <UserList users={users} handleSetUsers={setUsers} />
        </>
    )
}