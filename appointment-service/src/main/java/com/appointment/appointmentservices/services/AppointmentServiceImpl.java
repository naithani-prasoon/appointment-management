package com.appointment.appointmentservices.services;

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

    private final AppointmentRepo appointmentRepo;

    @Override
    public List<AppointmentsDto> getAptList() {
        return appointmentMapper.toAppointmentsDtoList(appointmentRepo.findAll());
    }

    @Override
    public AppointmentsDto createApt(AppointmentsDto appointmentsDto) {

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
        //will implement later
        AppointmentsDto toBeUpdated = appointmentMapper.toDto(appointmentRepo.findAppointmentById(id));

        if (appointmentsDto.getAppointmentStartTime() != null){
            toBeUpdated.setAppointmentStartTime(appointmentsDto.getAppointmentStartTime());
        }

        if (appointmentsDto.getAppointmentEndTime() != null){
            toBeUpdated.setAppointmentEndTime(appointmentsDto.getAppointmentEndTime());
        }

        if (appointmentsDto.getAppointmentType() != null){
            toBeUpdated.setAppointmentType(appointmentsDto.getAppointmentType());
        }

        appointmentRepo.save(appointmentMapper.toAppointments(toBeUpdated));

        return toBeUpdated;

    }

    @Override
    public AppointmentsDto getApt(UUID id) {
      //will implement with real data
        return appointmentMapper.toDto(appointmentRepo.findAppointmentById(id));
    };

    @Override
    public void deleteApt(UUID id) {
        //will implement later
        AppointmentsDto toBeUpdated = appointmentMapper.toDto(appointmentRepo.findAppointmentById(id));

        toBeUpdated.setSoftDelete(true);
        appointmentRepo.save(appointmentMapper.toAppointments(toBeUpdated));
    }

    @Override
    public void deleteAptByUserId(String userId) {
        List<AppointmentsDto> appointmentsDtoList = getAptByUserID(userId);

        for(AppointmentsDto appointmentDto: appointmentsDtoList){
            appointmentDto.setSoftDelete(true);
            appointmentRepo.save(appointmentMapper.toAppointments(appointmentDto));
        }
    }

    @Override
    public List<AppointmentsDto> getAptByUserID(String userId) {
        return appointmentMapper.toAppointmentsDtoList(appointmentRepo.findByUserID(userId));
    }
}
