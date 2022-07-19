package com.appointment.appointmentservices.repo;

import com.appointment.appointmentservices.web.model.Appointments;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepo extends MongoRepository<Appointments, UUID>{

    Appointments findAppointmentById(UUID id);

    List<Appointments> findByUserID(String userId);
}
