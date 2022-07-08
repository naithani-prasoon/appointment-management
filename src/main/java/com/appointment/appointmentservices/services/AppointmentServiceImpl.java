package com.appointment.appointmentservices.services;

import com.appointment.appointmentservices.model.Appointments;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Override
    public List<Appointments> getAptList() {

        List<Appointments> firstAppointment = new ArrayList<Appointments>();

        firstAppointment.add(
                Appointments.builder()
                        .id(UUID.randomUUID())
                        .appointmentName("Prasoon's Dentist Appointment")
                        .appointmentType("Cleaning")
                        .appointmentDescription("")
                        .appointmentStartTime(LocalDateTime.now())
                        .appointmentEndTime(LocalDateTime.now())
                        .build()
        );

        return firstAppointment;
    }

    @Override
    public Appointments createApt(Appointments appointments) {
        return Appointments.builder()
                .id(UUID.randomUUID())
                .appointmentName(appointments.appointmentName)
                .appointmentType(appointments.appointmentType)
                .appointmentStartTime(appointments.appointmentStartTime)
                .appointmentDescription(appointments.appointmentDescription)
                .appointmentEndTime(appointments.appointmentEndTime)
                .appointmentMetaData(appointments.appointmentMetaData)
                .build();
    }

    @Override
    public void updateApt(UUID id, Appointments appointments) {
        //will implement later
    }

    @Override
    public Appointments getApt(UUID id) {
      //will implement with real data
        return new Appointments();
    };

    @Override
    public void deleteApt(UUID id) {
        //will implement later
    }
}
