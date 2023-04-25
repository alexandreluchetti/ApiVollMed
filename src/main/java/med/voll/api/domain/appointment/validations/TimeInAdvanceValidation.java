package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import java.time.Duration;
import java.time.LocalDateTime;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandre
 */
@Component
public class TimeInAdvanceValidation implements AppointmentValidator{

    public void validate(AppointmentRegistrationData data){
        var appointmentTime = data.dateTime();
        var now = LocalDateTime.now();
        var timeInAdvance = Duration.between(now, appointmentTime).toMinutes();
        
        if(timeInAdvance < 30){
            throw new ValidationException("As consultas devem ser agendadas com antecedência mínima de 30 minutos!");
        }
    }

}
