import Navigation from "../components/Navigation"
import AppointmentTable from "../components/Dashboard/AppointmentTable"
import '../components/Dashboard/dashboard.css'

export default function Dashboard() {
    return(
        <>
            <Navigation />
            <h1> User's Dashboard</h1>
            <div>
                <AppointmentTable/>
            </div>
        </>
    )
}