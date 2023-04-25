package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.AppointmentCancelData;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.appointment.AppointmentSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Alexandre
 */
@RestController
@RequestMapping("appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {
    
    @Autowired
    private AppointmentSchedule appointmentSchedule;
    
    @PostMapping
    @Transactional
    public ResponseEntity setAppointment(@RequestBody @Valid AppointmentRegistrationData data){
        var dto = appointmentSchedule.setAppointment(data);
        
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid AppointmentCancelData data) {
        appointmentSchedule.cancel(data);
        return ResponseEntity.noContent().build();
    }
    
}
