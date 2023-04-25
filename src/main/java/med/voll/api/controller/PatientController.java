package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientsDataRegistration;
import med.voll.api.domain.patient.PatientsDataUpdate;
import med.voll.api.domain.patient.PatientsDetailData;
import med.voll.api.domain.patient.PatientsListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import med.voll.api.domain.patient.PatientsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Alexandre
 */
@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {
    
    @Autowired
    private PatientsRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientsDataRegistration data, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(data);
        repository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientsDetailData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientsListData>> getPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = repository.findAllByActiveTrue(pageable).map(PatientsListData::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientsDataUpdate data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateData(data);

        return ResponseEntity.ok(new PatientsDetailData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPatient(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientsDetailData(patient));
    }
    
}
