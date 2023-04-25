package med.voll.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.address.AddressDataRegistration;

/**
 *
 * @author Alexandre
 */
public record DoctorsDataUpdate(
        @NotNull
        Long id, 
        String name, 
        String cellphone,
        AddressDataRegistration address) {
    
}
