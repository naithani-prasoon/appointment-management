package com.appointment.appointmentservices.web.contollers;

import com.appointment.appointmentservices.model.AppointmentsDto;
import com.appointment.appointmentservices.services.AppointmentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RequestMapping(path="${service.api.path}")
@RestController
public class AppointmentController {


    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping({"/{appointmentId}"})
    public ResponseEntity getAppointment(@PathVariable("appointmentId") UUID appointmentId){
        AppointmentsDto requestedAppointment = appointmentService.getApt(appointmentId);
        if(requestedAppointment != null){
            return new ResponseEntity<>(requestedAppointment, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping({"/getAll"})
    public ResponseEntity<List<AppointmentsDto>> getAllAppointments(){
        return new ResponseEntity<>(appointmentService.getAptList(), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getUserAppointments(@RequestParam String userId){
        List<AppointmentsDto> userAppointmentList = appointmentService.getAptByUserID(userId);
        if(userAppointmentList != null){
           return new ResponseEntity<>(userAppointmentList, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity handlePost(@Validated AppointmentsDto appointmentsDto){
        System.out.println(appointmentsDto);
        AppointmentsDto createAppointment = appointmentService.createApt(appointmentsDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;");

        return new ResponseEntity<>(createAppointment, headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{appointmentId}"})
    public ResponseEntity<?> handlePut(@PathVariable("appointmentId") UUID appointmentId, @Valid AppointmentsDto appointmentsDto){
        if(appointmentService.getApt(appointmentId) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        appointmentService.updateApt(appointmentId, appointmentsDto);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping({"/{appointmentId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteApt(@PathVariable("appointmentId") UUID appointmentId){
        appointmentService.deleteApt(appointmentId);
    }

    @DeleteMapping({"/delete-user/{userId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAptByUserId(@PathVariable("userId") String userId) {
        System.out.println("Received");
        System.out.println(userId);
        appointmentService.deleteAptByUserId(userId);
    }
}
