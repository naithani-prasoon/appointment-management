package com.appointment.appointmentservices.web.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

//https://stackoverflow.com/questions/69558412/serialize-and-deserialize-a-instant-as-yyyy-mm-dd

public class SerializeTime extends JsonSerializer<Instant> {

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm").withZone(ZoneOffset.UTC);


    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String time = fmt.format(value);

        gen.writeString(time);
    }
}
