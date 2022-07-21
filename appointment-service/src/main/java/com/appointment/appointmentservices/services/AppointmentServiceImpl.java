package com.appointment.appointmentservices.services;

import com.appointment.appointmentservices.repo.AppointmentRepo;
import com.appointment.appointmentservices.web.model.Appointments;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private final AppointmentRepo appointmentRepo;

    @Override
    public List<Appointments> getAptList() {
        return appointmentRepo.findAll();
    }

    @Override
    public Appointments createApt(Appointments appointments) {

        Appointments newAppointment = new Appointments(UUID.randomUUID(),
                appointments.getUserID(), appointments.getAppointmentName(),
                appointments.getAppointmentType(), appointments.getAppointmentDescription(),
                appointments.getAppointmentStartTime(), appointments.getAppointmentEndTime(),
                appointments.getAppointmentMetaData());

        appointmentRepo.save(newAppointment);

        return newAppointment;
    }

    @Override
    public Appointments updateApt(UUID id, Appointments appointments) {
        //will implement later
        Appointments toBeUpdated = appointmentRepo.findAppointmentById(id);

        if (appointments.getAppointmentStartTime() != null){
            toBeUpdated.setAppointmentStartTime(appointments.getAppointmentStartTime());
        }

        if (appointments.getAppointmentEndTime() != null){
            toBeUpdated.setAppointmentEndTime(appointments.getAppointmentEndTime());
        }

        if (appointments.getAppointmentType() != null){
            toBeUpdated.setAppointmentType(appointments.getAppointmentType());
        }

        appointmentRepo.save(toBeUpdated);

        return toBeUpdated;

    }

    @Override
    public Appointments getApt(UUID id) {
      //will implement with real data
        return appointmentRepo.findAppointmentById(id);
    };

    @Override
    public void deleteApt(UUID id) {
        //will implement later
        appointmentRepo.deleteById(id);
    }

    @Override
    public List<Appointments> getAptByUserID(String userId) {
        return appointmentRepo.findByUserID(userId);
    }
}
