package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.AppointmentRegistrationData;

/**
 *
 * @author Alexandre
 */
public interface AppointmentValidator {
    
    void validate(AppointmentRegistrationData data);
    
}
