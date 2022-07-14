import React from "react";
import axios from "axios";
import './dashboard.css';
import AppointmentForm from "./AppointmentForm";
import EditIcon from '../Assets/edit.svg'
import DeleteIcon from '../Assets/delete.svg'

function createData(name, type, description, start, end) {
    return { name, type, description, start, end };
}

//trial data
const rows = [
    createData('Frozen yoghurt', "159", 0, 24, 4.0),
    createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
    createData('Eclair', 262, 16.0, 24, 6.0),
    createData('Cupcake', 305, 3.7, 67, 4.3),
    createData('Gingerbread', 356, 16.0, 49, 3.9),
];

export default function AppointmentTable() {

    //appointment data
    const baseUrl = 'http://localhost:8080/api/v1/appointment/'
    const [appointmentList, setAppointmentList] = React.useState([]);

    //pop up states
    const [popup, setPopup] = React.useState(false);
    const [createAppointment, setCreateAppointment] = React.useState(false);
    const [editAppoitment, setEditAppointment] = React.useState({name:'', type:'', description:'', start:'', end:''});

    function handleEditClick(appointment){
        setEditAppointment(appointment);
        setPopup(true)
    }

    function handleCreateAppointment(){
        setPopup(true);
        setCreateAppointment(true);
    }

    React.useEffect(() => {
        axios.get(baseUrl).then((res => {
            console.log(res.data);
        }))
    }, [])

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

                {rows.map((row) => (
                    <div className="table-appointments">
                        <h2 style={{textAlign:"left"}}>{row.name}</h2>
                        <h2>{row.type}</h2>
                        <h2 style={{textAlign:"left"}}>{row.description}</h2>
                        <h2>{row.start}</h2>
                        <h2>{row.end}</h2>
                        <img src={EditIcon} onClick={() => handleEditClick(row)}/>
                        <img src={DeleteIcon}/>
                    </div>
                ))}
                {
                    popup ?
                        <div className="edit-popup">
                            <div className="popup">
                                {createAppointment ? <AppointmentForm edit={false} data={editAppoitment}/> :
                                    <AppointmentForm edit={true} data ={editAppoitment}/>}
                            </div>
                        </div>
                        :
                        <></>
                }
            </div>
    )
}