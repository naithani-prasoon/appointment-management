package com.appointment.appointmentservices.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointments extends BaseEntity {

    private UUID id;

    public String appointmentName;

    public String appointmentType;

    public String appointmentDescription;

    public LocalDateTime appointmentStartTime;

    public LocalDateTime appointmentEndTime;

    public String appointmentMetaData;

}
