package com.appointment.appointmentservices.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("appointment")
public class Appointments {
    @Id
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