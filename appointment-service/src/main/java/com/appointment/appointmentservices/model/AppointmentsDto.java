package com.appointment.appointmentservices.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentsDto {
    private UUID id;
    private String userID;
    private String appointmentName;
    private String appointmentType;
    private String appointmentDescription;
    private String appointmentStartTime;
    private String appointmentEndTime;
    private String appointmentMetaData;
    private Boolean softDelete;
}
