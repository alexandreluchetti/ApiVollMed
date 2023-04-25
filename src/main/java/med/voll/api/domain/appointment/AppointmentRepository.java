package med.voll.api.domain.appointment;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Alexandre
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
    
    boolean existsByDoctorIdAndDatahora(Long idDoctor, LocalDateTime dateTime);
    boolean existsByPatientIdAndDatahoraBetween(Long idDoctor, LocalDateTime openingTime, LocalDateTime closingTime);
    
}
