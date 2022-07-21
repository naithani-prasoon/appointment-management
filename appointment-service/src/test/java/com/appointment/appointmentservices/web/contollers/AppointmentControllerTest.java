package com.appointment.appointmentservices.web.contollers;

import com.appointment.appointmentservices.services.AppointmentService;
import com.appointment.appointmentservices.web.model.Appointments;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AppointmentService appointmentService;

    @Test
    void getAppointment() throws Exception{
        given(appointmentService.getApt(any())).willReturn(getValidAppointment());

        mockMvc.perform(get("/api/v1/appointments/" + UUID.randomUUID()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllAppointments() throws Exception{
        List<Appointments> appointmentsList = new ArrayList<>();
        appointmentsList.add(getValidAppointment());
        appointmentsList.add(getValidAppointment());

        given(appointmentService.getAptList()).willReturn(appointmentsList);

        mockMvc.perform(get("/api/v1/appointments/getAll").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getNotValidAppointmentId() throws Exception{
        UUID randomUID = UUID.randomUUID();
        when(appointmentService.getApt(randomUID)).thenReturn(null);
        mockMvc.perform(get("/api/v1/appointments/" + randomUID))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAppointmentByUserId() throws Exception{
        List<Appointments> appointmentsList = new ArrayList<>();
        appointmentsList.add(getValidAppointment());
        appointmentsList.add(getValidAppointment());

        given(appointmentService.getAptByUserID(any())).willReturn(appointmentsList);

        mockMvc.perform(get("/api/v1/appointments?userId=54").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAppointmentByNotValidUserId() throws Exception{
        given(appointmentService.getAptByUserID(any())).willReturn(null);
        mockMvc.perform(get("/api/v1/appointments?userId=54").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    //Perfect post test
    @Test
    void handlePost() throws Exception {

        Appointments appointments = getValidAppointment();
        given(appointmentService.createApt(any(Appointments.class))).willReturn(appointments);

        mockMvc.perform(post("/api/v1/appointments/")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .param("appointmentName",appointments.getAppointmentName())
                    .param("userID",appointments.getUserID())
                    .param("appointmentType",appointments.getAppointmentType())
                    .param("appointmentDescription",appointments.getAppointmentDescription())
                    .param("appointmentStartTime",appointments.getAppointmentStartTime())
                    .param("appointmentEndTime",appointments.getAppointmentEndTime()))
                .andExpect(status().isCreated());
    }


    //Handling appointments with non-valid id
    @Test
    void handleNonValidPutID() throws Exception{
        UUID randomUID = UUID.randomUUID();
        Appointments appointments = getValidAppointment();

        given(appointmentService.updateApt(randomUID, getValidAppointment())).willReturn(null);
        mockMvc.perform(put("/api/v1/appointments/" + randomUID)
                .param("appointmentName",appointments.getAppointmentName())
                .param("userID",appointments.getUserID())
                .param("appointmentType",appointments.getAppointmentType())
                .param("appointmentDescription",appointments.getAppointmentDescription())
                .param("appointmentStartTime",appointments.getAppointmentStartTime())
                .param("appointmentEndTime",appointments.getAppointmentEndTime()))
                .andExpect(status().isNotFound());
    }

    //Handling not null tests
    @Test
    void handleNotValidPut() throws Exception{
        given(appointmentService.updateApt(any(), any(Appointments.class))).willReturn(getValidAppointment());

        Appointments appointments = getValidAppointment();

        mockMvc.perform(put("/api/v1/appointments/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("appointmentName",appointments.getAppointmentName())
                        .param("appointmentType",appointments.getAppointmentType())
                        .param("appointmentDescription",appointments.getAppointmentDescription())
                        .param("appointmentStartTime",appointments.getAppointmentStartTime())
                        .param("appointmentEndTime",appointments.getAppointmentEndTime()))
                .andExpect(status().isBadRequest());

        mockMvc.perform(put("/api/v1/appointments/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("userID", appointments.getUserID())
                        .param("appointmentName",appointments.getAppointmentName())
                        .param("appointmentDescription",appointments.getAppointmentDescription())
                        .param("appointmentStartTime",appointments.getAppointmentStartTime())
                        .param("appointmentEndTime",appointments.getAppointmentEndTime()))
                .andExpect(status().isBadRequest());
    }

    //Perfect put request
    @Test
    void handlePut() throws Exception{
        given(appointmentService.getApt(any(UUID.class))).willReturn(getValidAppointment());
        given(appointmentService.updateApt(any(UUID.class), any(Appointments.class))).willReturn(getValidAppointment());

        Appointments appointments = getValidAppointment();

        mockMvc.perform(put("/api/v1/appointments/" + appointments.getId().toString())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("appointmentName",appointments.getAppointmentName())
                        .param("userID",appointments.getUserID())
                        .param("appointmentType",appointments.getAppointmentType())
                        .param("appointmentDescription",appointments.getAppointmentDescription())
                        .param("appointmentStartTime",appointments.getAppointmentStartTime())
                        .param("appointmentEndTime",appointments.getAppointmentEndTime()))
                .andExpect(status().isNoContent());
    }


    Appointments getValidAppointment(){
        return Appointments.builder()
                .id(UUID.randomUUID())
                .userID("54")
                .appointmentName("Prasoon's Dental Appointment")
                .appointmentType("Urgent")
                .appointmentDescription("Root Canal")
                .appointmentStartTime("Tonight")
                .appointmentEndTime("Tomorrow")
                .appointmentMetaData("NA")
                .build();
    }

}