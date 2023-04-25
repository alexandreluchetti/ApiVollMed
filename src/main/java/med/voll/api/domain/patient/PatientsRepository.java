package med.voll.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Alexandre
 */
public interface PatientsRepository extends JpaRepository<Patient, Long>{
    Page<Patient> findAllByActiveTrue(Pageable pageable);

    @Query("""
           SELECT p.active FROM Patient p
           WHERE p.id = :id
           """)
    public boolean findActiveById(Long id);
}
