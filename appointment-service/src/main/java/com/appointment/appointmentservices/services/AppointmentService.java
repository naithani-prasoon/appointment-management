package com.appointment.appointmentservices.services;

import com.appointment.appointmentservices.model.AppointmentsDto;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    List<AppointmentsDto> getAptList();

    AppointmentsDto createApt(AppointmentsDto appointmentsDto);

    AppointmentsDto updateApt(UUID id, AppointmentsDto appointmentsDto);

    AppointmentsDto getApt(UUID id);

    void deleteApt(UUID id);

    void deleteAptByUserId(String userId);

    List<AppointmentsDto> getAptByUserID(String userId);
}
