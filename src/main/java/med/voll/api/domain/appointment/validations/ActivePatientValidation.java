package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.ValidateException;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.patient.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandre
 */
@Component
public class ActivePatientValidation implements AppointmentValidator{
    
    @Autowired
    private PatientsRepository patientsRepository;
    
    public void validate(AppointmentRegistrationData data){
        var activePatient = patientsRepository.findActiveById(data.idPatient());
        
        if(activePatient == false){
            throw new ValidateException("Paciente inativo!");
        }
    }
    
}
