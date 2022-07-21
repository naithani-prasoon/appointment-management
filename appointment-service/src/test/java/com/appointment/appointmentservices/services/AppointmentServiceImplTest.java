package com.appointment.appointmentservices.services;

import com.appointment.appointmentservices.repo.AppointmentRepo;
import com.appointment.appointmentservices.web.model.Appointments;
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

    Appointments getValidAppointment(){
        return Appointments.builder()
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
        List<Appointments> listOfAppointments = new ArrayList<>();
        listOfAppointments.add(getValidAppointment());
        listOfAppointments.add(getValidAppointment());

        //given
        given(appointmentRepo.findAll()).willReturn(listOfAppointments);

        //when
        List<Appointments> allAppointments = appointmentService.getAptList();

        //then
        then(appointmentRepo).should().findAll();
        assertThat(allAppointments).isNotNull();
    }

    @Test
    void createApt() {
        //given
        Appointments appointments = new Appointments();
        given(appointmentRepo.save(any(Appointments.class))).willReturn(appointments);

        //when
        Appointments createdAppointment = appointmentService.createApt(new Appointments());

        //then
        then(appointmentRepo).should().save(any(Appointments.class));
        assertThat(createdAppointment).isNotNull();

    }

    @Test
    void updateApt() {

        //given
        when(appointmentRepo.findAppointmentById(UUID.fromString("e692ab86-0920-11ed-861d-0242ac120002"))).thenReturn(getValidAppointment());
        when(appointmentRepo.save(any(Appointments.class))).thenReturn(getValidAppointment());

        //when
        Appointments updatedAppointment = appointmentService.updateApt(UUID.fromString("e692ab86-0920-11ed-861d-0242ac120002"), getValidAppointment());

        //then
        then(appointmentRepo).should().save(getValidAppointment());
        assertThat(updatedAppointment).isNotNull();
    }

    @Test
    void getApt() {
        //given
        given(appointmentRepo.findAppointmentById(any())).willReturn(getValidAppointment());

        //when
        Appointments appointmentsById = appointmentService.getApt(any());

        //then
        then(appointmentRepo).should().findAppointmentById(any());
        assertThat(appointmentsById).isNotNull();
    }

    @Test
    void getAptByUserID() {
        List<Appointments> listOfAppointments = new ArrayList<>();
        listOfAppointments.add(getValidAppointment());
        listOfAppointments.add(getValidAppointment());

        //given
        given(appointmentRepo.findByUserID(any())).willReturn(listOfAppointments);

        //when
        List<Appointments> appointmentsById = appointmentService.getAptByUserID(any());

        //then
        then(appointmentRepo).should().findByUserID(any());
        assertThat(appointmentsById).isNotNull();
    }
}