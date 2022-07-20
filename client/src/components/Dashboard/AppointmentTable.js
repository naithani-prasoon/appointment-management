import React from "react";
import axios from "axios";
import './dashboard.css';
import AppointmentForm from "./AppointmentForm";
import EditIcon from '../Assets/edit.svg'
import DeleteIcon from '../Assets/delete.svg'
import {useLocation} from "react-router-dom";

export default function AppointmentTable() {

    //appointment data
    const baseUrl = 'http://localhost:8081/api/v1/appointments'

    const { state } = useLocation()

    //pop up states
    const [popup, setPopup] = React.useState(false);
    const [createAppointment, setCreateAppointment] = React.useState(false);
    const [editAppointment, setEditAppointment] = React.useState({name:'', type:'', description:'', start:'', end:''});

    //appointment table rows
    const [row, setRow] = React.useState([])

    function createData(data) {
        return [data['appointmentName'], data['appointmentType'], data['appointmentDescription'], data['appointmentStartTime'], data['appointmentEndTime'], data['id'], data['userID']];
    }

    function handleEditClick(appointment){
        console.log(appointment)
        setEditAppointment(appointment);
        setPopup(true)
    }

    function handleCreateAppointment(){
        setPopup(true);
        setCreateAppointment(true);
    }

    function handleDeleteAppointment(appointment){
        axios.delete(baseUrl + '/' + appointment[5]).then((res => {
            console.log(res)
            console.log("deleted")
        }))

        window.location.reload();
    }


    React.useEffect(() => {
        if(state != null && state.userId != null){
            axios.get(baseUrl + '/?userId=' + state.userId).then((res =>{
                let tempRow = []
                for(let info = 0; info < res.data.length; info++){
                    tempRow.push(createData(res.data[info]))
                }
                setRow(tempRow);
            }))
        }
    }, [state])

    return(
            <div className="table">
                <div className="action-buttons">
                    <button onClick={() => handleCreateAppointment()}> Create Appointment </button>
                </div>
                <div className="table-header">
                    <h2> Name </h2>
                    <h2> Type </h2>
                    <h2> Description </h2>
                    <h2> Start Time </h2>
                    <h2> End Time </h2>
                    <h2></h2>
                    <h2></h2>
                </div>

                {row.map((row,index) => (

                    <div className="table-appointments" key={index}>
                        <h2 style={{textAlign:"left"}}>{row[0]}</h2>
                        <h2>{row[1]}</h2>
                        <h2 style={{textAlign:"left"}}>{row[2]}</h2>
                        <h2>{row[3].replace("T", " ")}</h2>
                        <h2>{row[4].replace("T", " ")}</h2>
                        <img alt={"Edit Button"} src={EditIcon} onClick={() => handleEditClick(row)}/>
                        <img alt={"Delete Button"} src={DeleteIcon} onClick={() => handleDeleteAppointment(row)}/>
                    </div>
                ))}
                {
                    popup ?
                        <div className="edit-popup">
                            <div className="popup">
                                {createAppointment ? <AppointmentForm edit={false} data={editAppointment}/> :
                                    <AppointmentForm edit={true} data ={editAppointment}/>}
                            </div>
                        </div>
                        :
                        <></>
                }
            </div>
    )
}