package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

/**
 *
 * @author Alexandre
 */
public record PatientsDetailData(Long id, String name, String email, String cpf, String cellphone, Address address) {
    
    public PatientsDetailData(Patient patient){
        this(   patient.getId(), 
                patient.getName(), 
                patient.getEmail(), 
                patient.getCpf(), 
                patient.getCellphone(), 
                patient.getAddress());
    }
}
