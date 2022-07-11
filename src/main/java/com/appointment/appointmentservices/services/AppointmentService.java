package com.appointment.appointmentservices.services;

import com.appointment.appointmentservices.web.model.Appointments;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    List<Appointments> getAptList();

    Appointments createApt(Appointments appointments);

    Appointments updateApt(UUID id, Appointments appointments);

    Appointments getApt(UUID id);

    void deleteApt(UUID id);
}
