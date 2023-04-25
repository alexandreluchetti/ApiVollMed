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
public class SameTimeDoctorsAppointmentValidation implements AppointmentValidator{
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    public void validate(AppointmentRegistrationData data){
        var sameTimeAppointment = appointmentRepository.existsByDoctorIdAndDatahora(data.idDoctor(), data.dateTime());
        
        if(sameTimeAppointment){
            throw new ValidateException("Esse médico já tem uma consulta agendada para esse horário!");
        }
    }
    
}
