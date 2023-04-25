package med.voll.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author Alexandre
 */
public record AddressDataRegistration(
        
        @NotBlank
        String street,
        
        @NotBlank
        String neighborhood,
        
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String zipcode,
        
        @NotBlank
        String city,
        
        @NotBlank
        String state,
        
        String number,
        
        String complement) {
    
}
