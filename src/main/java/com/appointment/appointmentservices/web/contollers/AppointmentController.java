package com.appointment.appointmentservices.web.contollers;

import com.appointment.appointmentservices.web.model.Appointments;
import com.appointment.appointmentservices.services.AppointmentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/api/v1/appointments")
@RestController
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping({"/{appointmentId}"})
    public ResponseEntity<Appointments> getAppointments(@PathVariable("appointmentId") UUID appointmentId){
        return new ResponseEntity<>(appointmentService.getApt(appointmentId), HttpStatus.OK);
    }

    @GetMapping({"/getAll"})
    public ResponseEntity<List<Appointments>> getAppointments(){
        return new ResponseEntity<>(appointmentService.getAptList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@Valid Appointments appointments){
        Appointments createAppointment = appointmentService.createApt(appointments);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Location","api/v1/appointments/"+ createAppointment.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{appointmentId}"})
    public ResponseEntity<?> handlePut(@PathVariable("appointmentId") UUID appointmentId, @Valid Appointments appointments){
        appointmentService.updateApt(appointmentId,appointments);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{appointmentId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApt(@PathVariable("appointmentId") UUID appointmentId){
        appointmentService.deleteApt(appointmentId);
    }
}
