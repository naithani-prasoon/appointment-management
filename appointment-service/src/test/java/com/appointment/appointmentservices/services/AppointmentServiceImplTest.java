package com.appointment.appointmentservices.services;

import com.appointment.appointmentservices.repo.AppointmentRepo;
import com.appointment.appointmentservices.model.AppointmentsDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {

    @Mock
    AppointmentRepo appointmentRepo;

    @InjectMocks
    AppointmentServiceImpl appointmentService;

    AppointmentsDto getValidAppointment(){
        return AppointmentsDto.builder()
                .id(UUID.fromString("e692ab86-0920-11ed-861d-0242ac120002"))
                .userID("54")
                .appointmentName("Prasoon's Dental Appointment")
                .appointmentType("Urgent")
                .appointmentDescription("Root Canal")
                .appointmentStartTime("Tonight")
                .appointmentEndTime("Tomorrow")
                .appointmentMetaData("NA")
                .build();
    }


    @Test
    void getAptList() {
        List<AppointmentsDto> listOfAppointments = new ArrayList<>();
        listOfAppointments.add(getValidAppointment());
        listOfAppointments.add(getValidAppointment());

        //given
        given(appointmentRepo.findAll()).willReturn(listOfAppointments);

        //when
        List<AppointmentsDto> allAppointments = appointmentService.getAptList();

        //then
        then(appointmentRepo).should().findAll();
        assertThat(allAppointments).isNotNull();
    }

    @Test
    void createApt() {
        //given
        AppointmentsDto appointmentsDto = new AppointmentsDto();
        given(appointmentRepo.save(any(AppointmentsDto.class))).willReturn(appointmentsDto);

        //when
        AppointmentsDto createdAppointment = appointmentService.createApt(new AppointmentsDto());

        //then
        then(appointmentRepo).should().save(any(AppointmentsDto.class));
        assertThat(createdAppointment).isNotNull();

    }

    @Test
    void updateApt() {

        //given
        when(appointmentRepo.findAppointmentById(UUID.fromString("e692ab86-0920-11ed-861d-0242ac120002"))).thenReturn(getValidAppointment());
        when(appointmentRepo.save(any(AppointmentsDto.class))).thenReturn(getValidAppointment());

        //when
        AppointmentsDto updatedAppointment = appointmentService.updateApt(UUID.fromString("e692ab86-0920-11ed-861d-0242ac120002"), getValidAppointment());

        //then
        then(appointmentRepo).should().save(getValidAppointment());
        assertThat(updatedAppointment).isNotNull();
    }

    @Test
    void getApt() {
        //given
        given(appointmentRepo.findAppointmentById(any())).willReturn(getValidAppointment());

        //when
        AppointmentsDto appointmentsDtoById = appointmentService.getApt(any());

        //then
        then(appointmentRepo).should().findAppointmentById(any());
        assertThat(appointmentsDtoById).isNotNull();
    }

    //AppointmentsDto Error
    //Will resolve this later
    @Test
    void getAptByUserID() {
        List<AppointmentsDto> listOfAppointments = new ArrayList<>();
        listOfAppointments.add(getValidAppointment());
        listOfAppointments.add(getValidAppointment());

        //given
        given(appointmentRepo.findByUserID(any())).willReturn(listOfAppointments);

        //when
        List<AppointmentsDto> appointmentsDtoById = appointmentService.getAptByUserID(any());

        //then
        then(appointmentRepo).should().findByUserID(any());
        assertThat(appointmentsDtoById).isNotNull();
    }
}