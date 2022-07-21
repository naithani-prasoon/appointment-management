package com.appointment.appointmentservices.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Document("appointment")
public class Appointments{

    @Id
    private UUID id;

    @NotNull
    private String userID;

    @NotNull
    private String appointmentName;

    @NotNull
    private String appointmentType;

    private String appointmentDescription;

    @NotNull
    private String appointmentStartTime;

    @NotNull
    private String appointmentEndTime;

    private String appointmentMetaData;

}
