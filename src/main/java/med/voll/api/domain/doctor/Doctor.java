package med.voll.api.domain.doctor;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

/**
 *
 * @author Alexandre
 */
@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    
    public Doctor(DoctorsDataRegistration data){
        this.name = data.name();
        this.email = data.email();
        this.cellphone = data.cellphone();
        this.crm = data.crm();
        this.speciality = data.speciality();
        this.address = new Address(data.address());
        this.active = true;
    }
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cellphone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @Embedded
    private Address address;
    
    private boolean active;

    public void updateData(DoctorsDataUpdate data) {
        if(data.name() != null)
            this.name = data.name();
        
        if(data.cellphone() != null)
            this.cellphone = data.cellphone();
        
        if(data.address() != null)
            this.address.updateData(data.address());
    }

    public void delete() {
        this.active = false;
    }
    
}
