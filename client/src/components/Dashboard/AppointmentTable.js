import React from "react";
import axios from "axios";
import './dashboard.css';
import AppointmentForm from "./AppointmentForm";
import EditIcon from '../Assets/edit.svg'
import DeleteIcon from '../Assets/delete.svg'
import {useLocation} from "react-router-dom";
import {AppointmentFilter} from "./AppointmentFilter";

export default function AppointmentTable() {

    //appointment data
    const baseUrl = 'http://localhost:8081/api/v1/appointments'

    const { state } = useLocation()

    //pop up states
    const [popup, setPopup] = React.useState(false);
    const [filter, setFilter] = React.useState(false);
    const [createAppointment, setCreateAppointment] = React.useState(false);
    const [editAppointment, setEditAppointment] = React.useState({name:'', type:'', description:'', start:'', end:''});

    //appointment table rows
    const [row, setRow] = React.useState([])

    function createData(data) {
        return [data['appointmentName'], data['appointmentType'], data['appointmentDescription'], data['appointmentStartTime'], data['appointmentEndTime'], data['id'], data['userID']];
    }

    function handleFilters(){
        setFilter(true);
        setPopup(false);
    }

    function handleEditClick(appointment){
        console.log(appointment)
        setEditAppointment(appointment);
        setPopup(true)
        setFilter(false);
    }

    function handleCreateAppointment(){
        setPopup(true);
        setCreateAppointment(true);
        setFilter(false)
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
                    console.log(res.data[info]["softDelete"])
                    if(!res.data[info]["softDelete"]){

                        tempRow.push(createData(res.data[info]))
                    }
                }
                setRow(tempRow);
            }))
        }
        else{
            axios.get(baseUrl + '/getAll').then((res =>{
                let tempRow = []
                for(let info = 0; info < res.data.length; info++){
                    if(!res.data[info]["softDelete"]){
                        tempRow.push(createData(res.data[info]))
                    }
                }
                setRow(tempRow);
            }))
        }
    }, [state])

    return(
            <div className="table">
                <div className="action-buttons">
                    <button onClick={() => handleFilters()} style={{background:"transparent", color:"black", marginRight:'10px'}}> Filters </button>
                    <button onClick={() => handleCreateAppointment()}> Create Appointment </button>
                </div>
                <div className="table-headers">
                    <h2> Name </h2>
                    <h2> Type </h2>
                    <h2> Description </h2>
                    <h2> Start Time </h2>
                    <h2> End Time </h2>
                    <h2></h2>
                    <h2></h2>
                </div>

                {row.map((row,index) => (

                    <div className="table-appointment" key={index}>
                        <h2 style={{textAlign:"left"}}>{row[0]}</h2>
                        <h2>{row[1]}</h2>
                        <h2 style={{textAlign:"left"}}>{row[2]}</h2>
                        <h2>{row[3]? row[3].replace("T", " "): row[3]}</h2>
                        <h2>{row[4]? row[4].replace("T", " "):row[4]}</h2>
                        <img alt={"Edit Button"} src={EditIcon} onClick={() => handleEditClick(row)}/>
                        <img alt={"Delete Button"} src={DeleteIcon} onClick={() => handleDeleteAppointment(row)}/>
                    </div>
                ))}
                {
                    popup || filter ?
                        <div className="edit-popup">
                            <div className="popup">
                                {filter ? <AppointmentFilter setFilter={setFilter} /> :
                                    createAppointment ? <AppointmentForm edit={false} data={editAppointment}/> :
                                        <AppointmentForm edit={true} data ={editAppointment}/>
                                }
                            </div>
                        </div>
                        :
                        <></>
                }
            </div>
    )
}