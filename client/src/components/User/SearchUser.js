import { useState } from "react";
import axios from "axios";
import './SearchUser.css'


/*  props
    users : users
    usersChangeHandler: setUsers
*/
export default function SearchUser(props) {

    const [name, setName] = useState("")
    const baseUrl = 'http://localhost:8080/api/v1/user/'

    const nameChangeHandler = (e) => {
        setName(e.target.value)
    }

    const submitHandler = (e) => {
        e.preventDefault()
        axios.get(baseUrl + '?name=' + name).then((res) => {
            props.usersChangeHandler(Object.values(res.data))
        })
    }

    return(
        <>
            <form onSubmit={submitHandler}>
                <div>
                    <input type="text" placeholder="Name" name="name" value={name} onChange={nameChangeHandler}></input>
                </div>
                <button>Search</button>
            </form>
        </>
    )
}