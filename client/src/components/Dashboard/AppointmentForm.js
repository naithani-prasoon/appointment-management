import React from "react";
import './form.css'
import axios from 'axios';
import {useLocation} from "react-router-dom";

export default function AppointmentForm(props){

    const {state} = useLocation();

    const [name, setName] = React.useState(props.data[0]);
    const [type, setType] = React.useState(props.data[1]);
    const [desc, setDesc] = React.useState(props.data[2]);
    const [start, setStart] = React.useState(props.data[3]);
    const [end, setEnd] = React.useState(props.data[4]);

    const [loading, setLoading] =  React.useState(false);
    const [message, setMessage] = React.useState("")

    const baseUrl = 'http://localhost:8081/api/v1/appointments'

    function handleChange(event){
        switch(event.target.name){
            case 'name':
                setName(event.target.value);
                break;
            case 'type':
                setType(event.target.value);
                break;
            case 'description':
                setDesc(event.target.value);
                break;
            case 'start':
                setStart(event.target.value);
                break;
            case 'end':
                setEnd(event.target.value);
                break;
            default:
                console.log('error');
        }
    }

    function onSubmit(event){
        event.preventDefault();
        try
        {
            console.log(name)
            setLoading(true);
            const data = new URLSearchParams()
            data.append('appointmentName', name)
            data.append('appointmentType',type)
            data.append('appointmentDescription',desc)
            data.append('appointmentStartTime',start.replace("T", " "))
            data.append('appointmentEndTime',end.replace("T", " "))
            if(state && state.userId){
                data.append('userID', state.userId)
            }

            console.log(baseUrl + '/' + props.data[5])

            if(props.edit){
                axios.put(baseUrl + '/' + props.data[5], data).then(response => {
                    console.log(response.data);
                })
            }
            else{
                axios.post(baseUrl, data).then(response =>{
                    console.log(response);
                    console.log(response.data);
                })
            }

            window.location.reload()
            setMessage("Success")

        }
        catch{
            setLoading(false);
            console.log("error")
        }
    }

    function closeButton(){
        window.location.reload()
    }
    
    return(
        <form onSubmit={onSubmit}>
            <h1>{props.edit ? "Update Appointment" : "Create Appointment"}</h1>
            <div className="form-sections">
                <div className="form-divider">
                    <label>Appointment Name*</label>
                    <input disabled={props.edit} pattern={"^d*[a-zA-Z][a-zA-Z0-9]*$"} title={"No Spaces"} required={true} type={"text"} name={"name"} value={name} onChange={event => handleChange(event)}/>
                </div>
                <div className="form-divider">
                    <label>Type*</label>
                    <input required={true} pattern={"^d*[a-zA-Z][a-zA-Z0-9]*$"} title={"No Spaces"}  type={"text"} name={"type"} value= {type} onChange={event => handleChange(event)}/>
                </div>
            </div>

            <div className="form-divider">
                <label>Description</label>
                <textarea disabled={props.edit} type={"text"} name={"description"} value={desc} onChange={event => handleChange(event)}/>
            </div>
            <div className="form-sections">
                <div className="form-divider">
                    <label>Start Time*</label>
                    <input required={true} type={"datetime-local"} name={"start"} value={start} onChange={event => handleChange(event)}/>
                </div>
                <div className="form-divider">
                    <label> End Time*</label>
                    <input required={true} type={"datetime-local"} name={"end"} value={end} onChange={event => handleChange(event)}/>
                </div>
            </div>
            <div className="form-sections">
                <button disabled={loading} type={"submit"}>{props.edit ? "Update" : "Create"}</button>
                <button type={"button"} disabled={loading} onClick={()=>closeButton()}>Close</button>
            </div>
        </form>
    )
}