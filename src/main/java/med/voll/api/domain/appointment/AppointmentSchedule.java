package med.voll.api.domain.appointment;

import java.util.List;
import med.voll.api.domain.ValidateException;
import med.voll.api.domain.appointment.validations.AppointmentCancelValidator;
import med.voll.api.domain.appointment.validations.AppointmentValidator;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alexandre
 */
@Service
public class AppointmentSchedule {
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private PatientsRepository patientsRepository;
    
    @Autowired
    private List<AppointmentValidator> validators;

    @Autowired
    private List<AppointmentCancelValidator> AppointmentCancelValidator;
    
    public AppointmentDetailData setAppointment(AppointmentRegistrationData data){
        if(!patientsRepository.existsById(data.idPatient())){
            throw new ValidateException("O paciente informado não existe!");
        }
        
        if(data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())){
            throw new ValidateException("O médico informado não existe!");
        }
        
        validators.forEach(v -> v.validate(data));
        
        var doctor = chooseDoctor(data);
        if(doctor == null){
            throw new ValidateException("Nenhum médico disponível nessa data!");
        }
        
        var patient = patientsRepository.getReferenceById(data.idPatient());
        var appointment = new Appointment(null, doctor, patient, data.dateTime(), null);
        
        appointmentRepository.save(appointment);
        
        return new AppointmentDetailData(appointment);
    }
    
    private Doctor chooseDoctor(AppointmentRegistrationData data){
        if(data.idDoctor() != null){
            return doctorRepository.getReferenceById(data.idDoctor());
        }
        
        if(data.speciality() == null){
            throw new ValidateException("A especialidade é obrigatória quando o médico não for escolhido!");
        }
        
        return doctorRepository.chooseRandomDoctorOnDate(data.speciality(), data.dateTime());
    }

    public void cancel(AppointmentCancelData data) {
        if (!appointmentRepository.existsById(data.id())) {
            throw new ValidateException("O id da consulta informado não existe!");
        }

        AppointmentCancelValidator.forEach(v -> v.validate(data));

        var appointment = appointmentRepository.getReferenceById(data.id());
        appointment.cancel(data.reason());
    }
    
}
