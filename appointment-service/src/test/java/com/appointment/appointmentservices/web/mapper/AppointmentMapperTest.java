package com.appointment.appointmentservices.web.mapper;

import com.appointment.appointmentservices.domain.Appointments;
import com.appointment.appointmentservices.model.AppointmentsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AppointmentMapperTest {

    private final AppointmentMapper appointmentMapper = Mappers.getMapper(AppointmentMapper.class);

    Appointments appointments;

    AppointmentsDto appointmentsDto;

    @BeforeEach
    void setUp(){
        String startTime = "2022-08-12 15:33";
        String endTime = "2022-08-12 16:33";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        LocalDateTime start = LocalDateTime.parse(startTime, formatter);
        LocalDateTime end = LocalDateTime.parse(endTime, formatter);

        appointments = Appointments.builder()
                .id(UUID.fromString("e692ab86-0920-11ed-861d-0242ac120002"))
                .userID("54")
                .appointmentName("Prasoon's Dental Appointment")
                .appointmentType("Urgent")
                .appointmentDescription("Root Canal")
                .appointmentStartTime(start)
                .appointmentEndTime(end)
                .appointmentMetaData("NA")
                .softDelete(false)
                .build();
        appointmentsDto = AppointmentsDto.builder()
                .id(UUID.fromString("e692ab86-0920-11ed-861d-0242ac120002"))
                .userID("54")
                .appointmentName("Prasoon's Dental Appointment")
                .appointmentType("Urgent")
                .appointmentDescription("Root Canal")
                .appointmentStartTime("2022-08-12 15:33")
                .appointmentEndTime("2022-08-12 16:33")
                .appointmentMetaData("NA")
                .softDelete(false)
                .build();
    }

    @Test
    void toDto(){
        AppointmentsDto toDto = appointmentMapper.toDto(appointments);

        assertThat(toDto.getAppointmentName()).isEqualTo(appointments.getAppointmentName());
        assertThat(toDto.getAppointmentDescription()).isEqualTo(appointments.getAppointmentDescription());
        assertThat(toDto.getAppointmentType()).isEqualTo(appointments.getAppointmentType());
        assertThat(toDto.getAppointmentMetaData()).isEqualTo(appointments.getAppointmentMetaData());
        assertThat(toDto.getUserID()).isEqualTo(appointments.getUserID());
        assertThat(toDto.getId()).isEqualTo(appointments.getId());
        assertThat(toDto.getSoftDelete()).isEqualTo(appointments.getSoftDelete());
        assertThat(toDto.getAppointmentEndTime()).isEqualTo(appointments.getAppointmentEndTime().toString().replace("T"," "));
        assertThat(toDto.getAppointmentStartTime()).isEqualTo(appointments.getAppointmentStartTime().toString().replace("T", " "));

    }

    @Test
    void toAppointment(){
        Appointments toAppointments = appointmentMapper.toAppointments(appointmentsDto);

        assertThat(toAppointments.getAppointmentName()).isEqualTo(appointmentsDto.getAppointmentName());
        assertThat(toAppointments.getAppointmentDescription()).isEqualTo(appointmentsDto.getAppointmentDescription());
        assertThat(toAppointments.getAppointmentType()).isEqualTo(appointmentsDto.getAppointmentType());
        assertThat(toAppointments.getAppointmentMetaData()).isEqualTo(appointmentsDto.getAppointmentMetaData());
        assertThat(toAppointments.getUserID()).isEqualTo(appointmentsDto.getUserID());
        assertThat(toAppointments.getId()).isEqualTo(appointmentsDto.getId());
        assertThat(toAppointments.getSoftDelete()).isEqualTo(appointmentsDto.getSoftDelete());
        assertThat(toAppointments.getAppointmentEndTime().toString().replace("T"," ")).isEqualTo(appointmentsDto.getAppointmentEndTime());
        assertThat(toAppointments.getAppointmentStartTime().toString().replace("T"," ")).isEqualTo(appointmentsDto.getAppointmentStartTime());
    }

    @Test
    void toAppointmentDtoList(){
        List<Appointments> appointmentsList = new ArrayList<>();
        appointmentsList.add(appointments);
        appointmentsList.add(appointments);

        List<AppointmentsDto> appointmentsDtoList = appointmentMapper.toAppointmentsDtoList(appointmentsList);

        for (AppointmentsDto appointmentDtos: appointmentsDtoList ) {
            assertThat(appointmentDtos.getAppointmentName()).isEqualTo(appointments.getAppointmentName());
            assertThat(appointmentDtos.getAppointmentDescription()).isEqualTo(appointments.getAppointmentDescription());
            assertThat(appointmentDtos.getAppointmentType()).isEqualTo(appointments.getAppointmentType());
            assertThat(appointmentDtos.getAppointmentMetaData()).isEqualTo(appointments.getAppointmentMetaData());
            assertThat(appointmentDtos.getUserID()).isEqualTo(appointments.getUserID());
            assertThat(appointmentDtos.getId()).isEqualTo(appointments.getId());
            assertThat(appointmentDtos.getSoftDelete()).isEqualTo(appointments.getSoftDelete());
            assertThat(appointmentDtos.getAppointmentEndTime()).isEqualTo(appointments.getAppointmentEndTime().toString().replace("T"," "));
            assertThat(appointmentDtos.getAppointmentStartTime()).isEqualTo(appointments.getAppointmentStartTime().toString().replace("T", " "));
        }
    }
}