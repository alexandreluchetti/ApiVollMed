package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Alexandre
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    
    private String street;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String state;
    private String number;
    private String complement;
    
    public Address(AddressDataRegistration data){
        this.street = data.street();
        this.neighborhood = data.neighborhood();
        this.zipcode = data.zipcode();
        this.city = data.city();
        this.state = data.state();
        this.number = data.number();
        this.complement = data.complement();
    }

    public void updateData(AddressDataRegistration data) {
        if(data.street() != null)
            this.street = data.street();
        
        if(data.neighborhood() != null)
            this.neighborhood = data.neighborhood();
        
        if(data.zipcode() != null)
            this.zipcode = data.zipcode();
        
        if(data.city() != null)
            this.city = data.city();
        
        if(data.state() != null)
            this.state = data.state();
        
        if(data.number() != null)
            this.number = data.number();
        
        if(data.complement() != null)
            this.complement = data.complement();
    }

}
