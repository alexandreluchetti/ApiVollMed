package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import med.voll.api.domain.doctor.Speciality;

/**
 *
 * @author Alexandre
 */
public record AppointmentRegistrationData(
        Long idDoctor,
        
        @NotNull
        Long idPatient,
        
        @NotNull
        @Future
//        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime dateTime,
        
        Speciality speciality
        ) {
    
}
