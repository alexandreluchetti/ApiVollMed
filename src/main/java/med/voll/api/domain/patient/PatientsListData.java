package med.voll.api.domain.patient;

/**
 *
 * @author Alexandre
 */
public record PatientsListData(Long id, String name, String email, String cpf) {
    
    public PatientsListData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
    
}
