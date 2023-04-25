package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

/**
 *
 * @author Alexandre
 */
public record AppointmentDetailData(Long id, Long idDoctor, Long idPatient, LocalDateTime dateTime) {
    
    public AppointmentDetailData(Appointment appointment){
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDatahora());
    }

}
