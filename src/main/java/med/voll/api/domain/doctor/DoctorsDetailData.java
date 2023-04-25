package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;

/**
 *
 * @author Alexandre
 */
public record DoctorsDetailData(Long id, String name, String email, String cellphone, String crm, Speciality speciality, Address address) {
    
    public DoctorsDetailData(Doctor doctor){
        this(   doctor.getId(), 
                doctor.getName(), 
                doctor.getEmail(), 
                doctor.getCellphone(), 
                doctor.getCrm(), 
                doctor.getSpeciality(), 
                doctor.getAddress());
    }
    
}
