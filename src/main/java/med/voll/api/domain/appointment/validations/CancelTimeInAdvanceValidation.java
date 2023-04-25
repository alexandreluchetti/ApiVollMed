package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentCancelData;
import med.voll.api.domain.appointment.AppointmentRegistrationData;
import med.voll.api.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 *
 * @author Alexandre
 */
@Component
public class CancelTimeInAdvanceValidation implements AppointmentCancelValidator{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(AppointmentCancelData data) {
        var appointment = appointmentRepository.getReferenceById(data.id());
        var now = LocalDateTime.now();
        var timeBetween = Duration.between(now, appointment.getDatahora()).toHours();

        if (timeBetween < 24) {
            throw new ValidationException("A consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
