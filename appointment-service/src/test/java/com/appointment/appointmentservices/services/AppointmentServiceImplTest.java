package com.appointment.appointmentservices.services;

import com.appointment.appointmentservices.domain.Appointments;
import com.appointment.appointmentservices.repo.AppointmentRepo;
import com.appointment.appointmentservices.model.AppointmentsDto;
import com.appointment.appointmentservices.web.mapper.AppointmentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @Mock
    AppointmentMapper appointmentMapper;

    @Mock
    AppointmentRepo appointmentRepo;

    @InjectMocks
    AppointmentServiceImpl appointmentService;

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
                .build();
    }

    @Test
    void getAptList() {
        List<Appointments> listOfAppointments = new ArrayList<>();
        listOfAppointments.add(appointments);
        listOfAppointments.add(appointments);

        //given
        given(appointmentRepo.findAll()).willReturn(listOfAppointments);

        //when
        List<AppointmentsDto> allAppointments = appointmentService.getAptList();

        //then
        then(appointmentRepo).should().findAll();
        assertThat(allAppointments).isNotNull();
    }

//    @MockitoSettings(strictness = Strictness.WARN)
    @Test
    void createApt() {

        //given
        given(appointmentMapper.toAppointments(appointmentsDto)).willReturn(appointments);
        given(appointmentMapper.toDto(appointments)).willReturn(appointmentsDto);
        given(appointmentRepo.save(any(Appointments.class))).willReturn(appointments);



        //when
        AppointmentsDto createdAppointment = appointmentService.createApt(appointmentsDto);

        //then
        then(appointmentRepo).should().save(any(Appointments.class));
        assertThat(createdAppointment).isNotNull();
    }

    @Test
    void updateApt() {

        //given
        when(appointmentRepo.findAppointmentById(UUID.fromString("e692ab86-0920-11ed-861d-0242ac120002"))).thenReturn(appointments);
        when(appointmentMapper.toAppointments(any(AppointmentsDto.class))).thenReturn(appointments);
        when(appointmentRepo.save(any(Appointments.class))).thenReturn(appointments);
        when(appointmentMapper.toDto(any(Appointments.class))).thenReturn(appointmentsDto);

        //when
        AppointmentsDto updatedAppointment = appointmentService.updateApt(UUID.fromString("e692ab86-0920-11ed-861d-0242ac120002"), appointmentsDto);

        //then
        then(appointmentRepo).should().save(appointments);
        assertThat(updatedAppointment).isNotNull();
    }

    @Test
    void getApt() {
        //given
        given(appointmentRepo.findAppointmentById(any())).willReturn(appointments);
        given(appointmentMapper.toDto(appointments)).willReturn(appointmentsDto);

        //when
        AppointmentsDto appointmentsDtoById = appointmentService.getApt(appointments.getId());

        //then
        then(appointmentRepo).should().findAppointmentById(any(UUID.class));
        assertThat(appointmentsDtoById).isNotNull();
    }

    //AppointmentsDto Error
    //Will resolve this later
    @Test
    void getAptByUserID() {
        List<Appointments> listOfAppointments = new ArrayList<>();
        listOfAppointments.add(appointments);
        listOfAppointments.add(appointments);

        //given
        given(appointmentRepo.findByUserID(any())).willReturn(listOfAppointments);

        //when
        List<AppointmentsDto> appointmentsDtoById = appointmentService.getAptByUserID(any());

        //then
        then(appointmentRepo).should().findByUserID(any());
        assertThat(appointmentsDtoById).isNotNull();
    }
}