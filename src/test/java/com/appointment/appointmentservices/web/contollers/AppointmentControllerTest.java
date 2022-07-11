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

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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

        mockMvc.perform(get("/api/v1/appointments/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void handlePost() throws Exception {

        Appointments appointments = getValidAppointment();
        String appointmentsToJSON = objectMapper.writeValueAsString(appointments);

        given(appointmentService.createApt(any())).willReturn(getValidAppointment());

        mockMvc.perform(post("/api/v1/appointments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(appointmentsToJSON))
                .andExpect(status().isCreated());
    }

    @Test
    void handlePut() throws Exception{
        given(appointmentService.updateApt(any(), any())).willReturn(getValidAppointment());

        Appointments appointments = getValidAppointment();
        String appointmentToJSON = objectMapper.writeValueAsString(appointments);

        mockMvc.perform(put("/api/v1/appointments/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(appointmentToJSON))
                .andExpect(status().isNoContent());
    }

    Appointments getValidAppointment(){
        return Appointments.builder()
                .id(UUID.randomUUID())
                .appointmentName("Prasoon's Dental Appointment")
                .appointmentType("Urgent")
                .appointmentDescription("Root Canal")
                .appointmentStartTime("Tonight")
                .appointmentEndTime("Tomorrow")
                .appointmentMetaData("NA")
                .build();
    }

    Appointments getNotValidAppointment(){
        return Appointments.builder()
                .id(UUID.randomUUID())
                .appointmentDescription("Invalid")
                .build();
    }
}