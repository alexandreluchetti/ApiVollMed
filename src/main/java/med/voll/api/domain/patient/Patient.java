package med.voll.api.domain.patient;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String email;

    private String cellphone;

    private String cpf;

    @Embedded
    private Address address;

    private Boolean active;

    public Patient(PatientsDataRegistration data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.cellphone = data.cellphone();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
    }

    public void updateData(PatientsDataUpdate data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.cellphone() != null) {
            this.cellphone = data.cellphone();
        }
        if (data.address() != null) {
            this.address.updateData(data.address());
        }

    }

    public void delete() {
        this.active = false;
    }

}
