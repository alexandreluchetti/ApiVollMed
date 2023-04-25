package med.voll.api.domain.appointment;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.Patient;

/**
 *
 * @author Alexandre
 */
@Table(name = "appointments")
@Entity(name = "Appointment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_patient")
    private Patient patient;
    
    
    private LocalDateTime datahora;

    @Column(name = "reason")
    @Enumerated(EnumType.STRING)
    private ReasonCancel reasonCancel;

    public void cancel(ReasonCancel reason) {
        this.reasonCancel = reason;
    }
    
}
