package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import java.time.DayOfWeek;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandre
 */
@Component
public class DayTimeValidation implements AppointmentValidator{
    
    public void validate(AppointmentRegistrationData data){
        var sunday = data.dateTime().getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeSevenAm = data.dateTime().getHour() < 7;
        var afterSixPm = data.dateTime().getHour() > 18;
        
        if(sunday || beforeSevenAm || afterSixPm){
            throw new ValidationException("Consulta fora do horário de funcionamento da clínica!");
        }
    }
    
}
