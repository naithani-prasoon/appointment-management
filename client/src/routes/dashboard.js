import Navigation from "../components/Navigation"
import AppointmentTable from "../components/Dashboard/AppointmentTable"

export default function Dashboard() {
    return(
        <>
            <Navigation />
            <h1>Dashboard</h1>
            <div>
                <AppointmentTable/>
            </div>
        </>
    )
}