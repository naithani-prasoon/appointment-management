package com.appointment.appointmentservices.services;

import com.appointment.appointmentservices.domain.Appointments;
import com.appointment.appointmentservices.repo.AppointmentRepo;
import com.appointment.appointmentservices.model.AppointmentsDto;
import com.appointment.appointmentservices.web.mapper.AppointmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentMapper appointmentMapper;

    @Autowired
    private final AppointmentRepo appointmentRepo;

    @Override
    public List<AppointmentsDto> getAptList() {
        return appointmentMapper.toAppointmentsDtoList(appointmentRepo.findAll());
    }

    @Override
    public AppointmentsDto createApt(AppointmentsDto appointmentsDto) {
        //Had to implement this way as there was an issue with the id with mongoDb
        AppointmentsDto newAppointment = new AppointmentsDto(UUID.randomUUID(),
                appointmentsDto.getUserID(), appointmentsDto.getAppointmentName(),
                appointmentsDto.getAppointmentType(), appointmentsDto.getAppointmentDescription(),
                appointmentsDto.getAppointmentStartTime(), appointmentsDto.getAppointmentEndTime(),
                appointmentsDto.getAppointmentMetaData(), false);

        appointmentRepo.save(appointmentMapper.toAppointments(newAppointment));

        return newAppointment;
    }

    @Override
    public AppointmentsDto updateApt(UUID id, AppointmentsDto appointmentsDto) {
        //Only update type and time of appointment
        Appointments toBeUpdated = appointmentRepo.findAppointmentById(id);
        Appointments updatedAppointment = appointmentMapper.toAppointments(appointmentsDto);

        if (updatedAppointment.getAppointmentStartTime() != null){
            toBeUpdated.setAppointmentStartTime(updatedAppointment.getAppointmentStartTime());
        }

        if (updatedAppointment.getAppointmentEndTime() != null){
            toBeUpdated.setAppointmentEndTime(updatedAppointment.getAppointmentEndTime());
        }

        if (updatedAppointment.getAppointmentType() != null){
            toBeUpdated.setAppointmentType(updatedAppointment.getAppointmentType());
        }

        appointmentRepo.save(toBeUpdated);

        return appointmentMapper.toDto(updatedAppointment);

    }

    @Override
    public AppointmentsDto getApt(UUID id) {
      //Fetch appointment using ID
        return appointmentMapper.toDto(appointmentRepo.findAppointmentById(id));
    }

    @Override
    public void deleteApt(UUID id) {
        //Soft delete
        Appointments toBeUpdated = appointmentRepo.findAppointmentById(id);

        toBeUpdated.setSoftDelete(true);
        appointmentRepo.save(toBeUpdated);
    }

    @Override
    public void deleteAptByUserId(String userId) {
        //Sim. delete appointments if a user is deleted (soft/hard)
        List<AppointmentsDto> appointmentsDtoList = getAptByUserID(userId);

        for(AppointmentsDto appointmentDto: appointmentsDtoList){
            appointmentDto.setSoftDelete(true);
            appointmentRepo.save(appointmentMapper.toAppointments(appointmentDto));
        }
    }

    @Override
    public List<AppointmentsDto> getAptByUserID(String userId) {
        //Fetches appointment on the basis of userId
        return appointmentMapper.toAppointmentsDtoList(appointmentRepo.findByUserID(userId));
    }
}
