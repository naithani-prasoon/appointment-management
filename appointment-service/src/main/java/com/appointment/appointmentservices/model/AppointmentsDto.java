package com.appointment.appointmentservices.model;

import com.appointment.appointmentservices.web.mapper.DeserializeTime;
import com.appointment.appointmentservices.web.mapper.SerializeTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.Instant;
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

    @JsonFormat(pattern = "yyyy-MM-ddTHH:mm", timezone = "UTC")
    @JsonDeserialize(using = DeserializeTime.class)
    @JsonSerialize(using = SerializeTime.class)
    private Instant appointmentStartTime;

    @JsonFormat(pattern = "yyyy-MM-ddTHH:mm", timezone = "UTC")
    @JsonDeserialize(using = DeserializeTime.class)
    @JsonSerialize(using = SerializeTime.class)
    private Instant appointmentEndTime;

    private String appointmentMetaData;
    private Boolean softDelete;
}
