import React from "react";

export function AppointmentFilter(props){

    function onSubmit(){

    }

    return(
        <form onSubmit={onSubmit}>
            <h1> Filters </h1>
            <div className="form-sections">

                <div className="form-divider" style={{flexDirection:"row", alignItems:"center", alignContent:"center"}}>
                    <input type={"checkbox"} name={"name"}/>
                    <label style={{transform:"translateY(5px)"}}> Appointment Type </label>
                </div>
                <div className="form-divider" style={{flexDirection:"row", alignItems:"center", alignContent:"center"}}>
                    <input type={"checkbox"} name={"name"}/>
                    <label style={{transform:"translateY(5px)"}}> Start Time</label>
                </div>
                <div className="form-divider" style={{flexDirection:"row", alignItems:"center", alignContent:"center"}}>
                    <input type={"checkbox"} name={"name"}/>
                    <label style={{transform:"translateY(5px)"}}> End Time</label>
                </div>
            </div>
            <div className="form-sections">
                <div className="form-divider" style={{flexDirection:"row", alignItems:"center", alignContent:"center"}}>
                    <input type={"checkbox"} name={"name"}/>
                    <label style={{transform:"translateY(5px)"}}>Ascending</label>
                </div>
                <div className="form-divider" style={{flexDirection:"row", alignItems:"center", alignContent:"center"}}>
                    <input type={"checkbox"} name={"name"}/>
                    <label style={{transform:"translateY(5px)"}}> Descending</label>
                </div>
            </div>
            <div className="form-sections">
                <button type={"submit"}>Apply</button>
                <button type={"button"}onClick={()=>window.location.reload()}>Close</button>
            </div>
        </form>
    )
}