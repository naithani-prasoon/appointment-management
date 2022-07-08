package com.appointment.appointmentservices.services;

import com.appointment.appointmentservices.model.Appointments;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    List<Appointments> getAptList();

    Appointments createApt(Appointments appointments);

    void updateApt(UUID id, Appointments appointments);

    Appointments getApt(UUID id);

    void deleteApt(UUID id);
}
