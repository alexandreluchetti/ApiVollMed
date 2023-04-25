package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressDataRegistration;

/**
 *
 * @author Alexandre
 */
public record PatientsDataRegistration(
        @NotBlank
        String name,
        
        @NotBlank
        @Email
        String email,
        
        @NotBlank
        String cellphone,
        
        @NotBlank
        @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
        String cpf,
        
        @NotNull
        @Valid
        AddressDataRegistration address) {

}
