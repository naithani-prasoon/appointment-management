package com.appointment.appointmentservices.web.model;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("appointment")
public class Appointments{

    @Id
    private UUID id;

    @NotNull
    private String appointmentName;

    @NotNull
    private String appointmentType;

    private String appointmentDescription;

    @NotNull
    private LocalDateTime appointmentStartTime;

    @NotNull
    private LocalDateTime appointmentEndTime;

    private String appointmentMetaData;

}
