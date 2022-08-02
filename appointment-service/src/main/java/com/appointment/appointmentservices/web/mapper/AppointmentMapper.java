package com.appointment.appointmentservices.web.mapper;

import com.appointment.appointmentservices.domain.Appointments;
import com.appointment.appointmentservices.model.AppointmentsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {
    @Mapping(target = "appointmentStartTime", source="appointmentsDto.appointmentStartTime", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "appointmentEndTime", source="appointmentsDto.appointmentEndTime", dateFormat = "yyyy-MM-dd HH:mm")
    Appointments toAppointments(AppointmentsDto appointmentsDto);
    @Mapping(target = "appointmentStartTime", source="appointments.appointmentStartTime", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "appointmentEndTime", source="appointments.appointmentEndTime", dateFormat = "yyyy-MM-dd HH:mm")
    AppointmentsDto toDto(Appointments appointments);
    List<AppointmentsDto> toAppointmentsDtoList(List<Appointments> appointmentsList);
}
