import React from "react";

export default function AppointmentForm(){

    return(
        <form>
            <input type={"text"} value={"Appontment Name"}/>
            <input type={"text"} value={"Appointment Type"}/>
            <textarea type={"text"} value={"Appointment Description"}/>
            <input type={"time"}/>
            <input type={"time"}/>
            <button type={"submit"}>Update</button>
            <button onClick={() => setPopup(false)} > Close </button>
        </form>
    )
}