import Navigation from "../components/Navigation"
import AppointmentTable from "../components/Dashboard/AppointmentTable"
import '../components/Dashboard/dashboard.css'
import {useLocation} from "react-router-dom";
import React from "react"

export default function Dashboard() {

    const {state} = useLocation();

    let username = ""
    if(state != null && state.userName != null){
        username = state.userName + "'s";
    }
    else{
        username = "User's"
    }


    return(
        <>
            <Navigation />
            <h1> {username} Dashboard</h1>
            <div>
                <AppointmentTable/>
            </div>
        </>
    )
}