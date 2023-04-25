package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidateException;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandre
 */
@Component
public class ActiveDoctortValidation implements AppointmentValidator{
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    public void validate(AppointmentRegistrationData data){
        if(data.idDoctor() == null){
            return;
        }
        
        var activeDoctor = doctorRepository.findActiveById(data.idPatient());
        
        if(activeDoctor == false){
            throw new ValidateException("Médico inativo!");
        }
    }
    
}
