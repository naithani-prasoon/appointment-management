package com.appointment.appointmentservices.repo;

import com.appointment.appointmentservices.web.model.Appointments;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AppointmentRepo extends MongoRepository<Appointments, String>{

    Appointments findAppointmentById(UUID id);
}
