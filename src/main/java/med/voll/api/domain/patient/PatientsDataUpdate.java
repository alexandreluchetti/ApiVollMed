package med.voll.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDataRegistration;

/**
 *
 * @author Alexandre
 */
public record PatientsDataUpdate(
        @NotNull
        Long id,
        
        String name,
        String cellphone,
        AddressDataRegistration address) {
    
}
