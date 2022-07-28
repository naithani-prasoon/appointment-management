package com.appointment.appointmentservices.web.mapper;

//https://stackoverflow.com/questions/69558412/serialize-and-deserialize-a-instant-as-yyyy-mm-dd

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DeserializeTime extends JsonDeserializer<Instant> {

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm").withZone(ZoneOffset.UTC);

    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return Instant.from(fmt.parse(p.getText()));
    }
}
