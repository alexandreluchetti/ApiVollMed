package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidateException;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandre
 */
@Component
public class SameDayPatientsAppointment implements AppointmentValidator{
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    public void validate(AppointmentRegistrationData data){
        var firstTime = data.dateTime().withHour(7);
        var lastTime = data.dateTime().withHour(18);
        var sameDayAppointment = appointmentRepository.existsByPatientIdAndDatahoraBetween(data.idPatient(), firstTime, lastTime);
        
        if(sameDayAppointment){
            throw new ValidateException("Esse paciente já possuí uma consulta agendada nessa data!");
        }
    }
    
}
