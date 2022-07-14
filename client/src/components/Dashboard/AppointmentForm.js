import React from "react";
import './form.css'

export default function AppointmentForm(props){

    const [name, setName] = React.useState(props.data.name);
    const [type, setType] = React.useState(props.data.type);
    const [desc, setDesc] = React.useState(props.data.description);
    const [start, setStart] = React.useState(props.data.start);
    const [end, setEnd] = React.useState(props.data.end);

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
    
    return(
        <form>
            <h1>{props.edit ? "Update Appointment" : "Create Appointment"}</h1>
            <div className="form-sections">
                <div className="form-divider">
                    <label>Appointment Name</label>
                    <input type={"text"} name={"name"} value={name} onChange={event => handleChange(event)}/>
                </div>
                <div className="form-divider">
                    <label>Type</label>
                    <input type={"text"} name={"type"} value= {type} onChange={event => handleChange(event)}/>
                </div>
            </div>

            <div className="form-divider">
                <label>Description</label>
                <textarea type={"text"} name={"description"} value={desc} onChange={event => handleChange(event)}/>
            </div>
            <div className="form-sections">
                <div className="form-divider">
                    <label>Start Time</label>
                    <input type={"time"} name={"start"} onChange={event => handleChange(event)}/>
                </div>
                <div className="form-divider">
                    <label> End Time</label>
                    <input type={"time"} name={"end"} onChange={event => handleChange(event)}/>
                </div>
            </div>
            <div className="form-sections">
                <button type={"submit"}>{props.edit ? "Update" : "Create"}</button>
                <button>Close</button>
            </div>

        </form>
    )
}