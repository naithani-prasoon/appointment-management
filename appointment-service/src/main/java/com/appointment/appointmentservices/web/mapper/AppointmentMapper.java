package com.appointment.appointmentservices.web.mapper;

import com.appointment.appointmentservices.domain.Appointments;
import com.appointment.appointmentservices.model.AppointmentsDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    Appointments toAppointments(AppointmentsDto appointmentsDto);
    AppointmentsDto toDto(Appointments appointments);
    List<AppointmentsDto> toAppointmentsDtoList(List<Appointments> appointmentsList);
    List<Appointments> toAppointmentsList(List<AppointmentsDto> appointmentsDtos);
}
