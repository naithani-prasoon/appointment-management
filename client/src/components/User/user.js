import React from "react";
import axios from "axios";


export default function User() {
    const [user, setUser] = React.useState(null)
    const [appt, setappt] = React.useState(null)
    const baseUrl = 'http://localhost:8080/api/v1/user/'

    React.useEffect(() => {
        axios.get(baseUrl + '62c5a35436faf068fc9d4791').then((res => {
            setUser(res.data)
        }))
    }, [])

    if (!user) return null

    return (
        <div>
            <h1>{user.firstName} {user.lastName}</h1>

        </div>
    )
}
