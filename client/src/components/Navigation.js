import React from "react";
import { NavLink } from "react-router-dom";

export default function Navigation() {
    return(
        <nav className='navbar navbar-expand navbar-dark bg-dark'>
        <div className='container'>
          <NavLink className="navbar-brand" to="/">
            Appointment Management
          </NavLink>
          <div>
            <ul className="navbar-nav ml-auto">
              <li className="nav-item">
                <NavLink className="nav-link" to="/">
                  Home
                  <span className="sr-only">(current)</span>
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to="/register">
                  Register
                  <span className="sr-only">(current)</span>
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to="/users">
                  Users
                  <span className="sr-only">(current)</span>
                </NavLink>
              </li>
              <li className="nav-item">
                <NavLink className="nav-link" to="/dashboard">
                  Dashboard
                  <span className="sr-only">(current)</span>
                </NavLink>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    )
}