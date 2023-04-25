package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressDataRegistration;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientsDataRegistration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void chooseRandomDoctorOnDate1() {
        //given or arrange
        var nextMondayAtTen = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var doctor = doctorRegistration("Médico", "teste@gmail.com", "656541", Speciality.CARDIOLOGIA);
        var patient = patientRegistration("Paciente", "teste1@gmail.com", "123.456.789-88");
        appointmentRegistration(doctor, patient, nextMondayAtTen);

        //when or act
        var freeDoctor = doctorRepository.chooseRandomDoctorOnDate(Speciality.CARDIOLOGIA, nextMondayAtTen);

        //then or assert
        Assertions.assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando quando ele estiver disponivel na data")
    void chooseRandomDoctorOnDate2() {
        var nextMondayAtTen = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);

        var doctor = doctorRegistration("Médico", "teste@gmail.com", "656541", Speciality.CARDIOLOGIA);

        var freeDoctor = doctorRepository.chooseRandomDoctorOnDate(Speciality.CARDIOLOGIA, nextMondayAtTen);
        Assertions.assertThat(freeDoctor).isEqualTo(doctor);
    }

    private void appointmentRegistration(Doctor doctor, Patient patient, LocalDateTime date) {
        testEntityManager.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor doctorRegistration(String name, String email, String crm, Speciality speciality) {
        var doctor = new Doctor(doctorData(name, email, crm, speciality));
        testEntityManager.persist(doctor);
        return doctor;
    }

    private Patient patientRegistration(String name, String email, String cpf) {
        var patient = new Patient(patientData(name, email, cpf));
        testEntityManager.persist(patient);
        return patient;
    }

    private DoctorsDataRegistration doctorData(String name, String email, String crm, Speciality speciality) {
        return new DoctorsDataRegistration(
                name,
                email,
                "61999999999",
                crm,
                speciality,
                addressData()
        );
    }

    private PatientsDataRegistration patientData(String name, String email, String cpf) {
        return new PatientsDataRegistration(
                name,
                email,
                "61999999999",
                cpf,
                addressData()
        );
    }

    private AddressDataRegistration addressData() {
        return new AddressDataRegistration(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}