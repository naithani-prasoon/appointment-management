import React from "react";
import axios from "axios";
import './dashboard.css';

function createData(name, calories, fat, carbs, protein) {
    return { name, calories, fat, carbs, protein };
}

const rows = [
    createData('Frozen yoghurt', 159, 6.0, 24, 4.0),
    createData('Ice cream sandwich', 237, 9.0, 37, 4.3),
    createData('Eclair', 262, 16.0, 24, 6.0),
    createData('Cupcake', 305, 3.7, 67, 4.3),
    createData('Gingerbread', 356, 16.0, 49, 3.9),
];

export default function AppointmentTable() {
    const baseUrl = 'http://localhost:8080/api/v1/appointment/'
    const [appointmentList, setAppointmentList] = React.useState([]);
    const [popup, setPopup] = React.useState(false);
    const [editAppoitment, setEditAppointment] = React.useState({});

    function handleEditClick(appointment){
        setEditAppointment(appointment);
        setPopup(true)
    }

    React.useEffect(() => {
        axios.get(baseUrl + '62c5a35436faf068fc9d4791').then((res => {
            console.log(res.data);
        }))
    }, [])

    return(
            <div className="table">
                <div className="table-header">
                    <h2 style={{textAlign:"left"}}> Name </h2>
                    <h2> Type </h2>
                    <h2> Description </h2>
                    <h2> Start Time </h2>
                    <h2> End Time </h2>
                    <h2></h2>
                </div>

                {rows.map((row) => (
                    <div className="table-appointments">
                        <h2 style={{textAlign:"left"}}>{row.name}</h2>
                        <h2>{row.calories}</h2>
                        <h2>{row.fat}</h2>
                        <h2>{row.carbs}</h2>
                        <h2>{row.protein}</h2>
                        <h2 onClick={() => handleEditClick(row)}> EDIT </h2>
                    </div>
                ))}
                {
                    popup ?
                        <div className="edit-popup">
                            <div className="popup" >
                                <h2 onClick={() => setPopup(false)}> Close </h2>
                                {editAppoitment.name}
                            </div>
                        </div>
                        :
                        <></>
                }

            </div>
    )
}