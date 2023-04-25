package med.voll.api.domain.doctor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Alexandre
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    @Query("""
             select d from Doctor d
             where 
             d.active = 1
             and
             d.speciality = :speciality
             and
             d.id not in(
                select a.doctor.id from Appointment a
                where
                a.datahora = :datahora
             )
             order by rand()
             limit 1
            """)
    Doctor chooseRandomDoctorOnDate(Speciality speciality, LocalDateTime datahora);

    @Query("""
            SELECT d.active FROM Doctor d
            WHERE d.id = :id
            """)
    Boolean findActiveById(Long id);
}

//    select d from Doctor d
//        where d.active = 1
//        order by rand()
//        limit 1

