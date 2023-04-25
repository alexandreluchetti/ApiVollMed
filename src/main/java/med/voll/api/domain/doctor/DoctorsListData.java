package med.voll.api.domain.doctor;

/**
 *
 * @author Alexandre
 */
public record DoctorsListData(Long id, String name, String email, String crm, Speciality speciality) {
    
    public DoctorsListData(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality());
    }
    
}
