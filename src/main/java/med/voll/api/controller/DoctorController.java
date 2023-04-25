package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.doctor.DoctorsDataRegistration;
import med.voll.api.domain.doctor.DoctorsDataUpdate;
import med.voll.api.domain.doctor.DoctorsDetailData;
import med.voll.api.domain.doctor.DoctorsListData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author Alexandre
 */
@RestController
@RequestMapping("/doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {
    
    @Autowired
    private DoctorRepository repository;
    
    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorsDataRegistration data, UriComponentsBuilder uriBuilder){
        var doctor = new Doctor(data);
        repository.save(doctor);
        
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();
        
        return ResponseEntity.created(uri).body(new DoctorsDetailData(doctor));
    }
    
    @GetMapping
    public ResponseEntity<Page<DoctorsListData>> getDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var page =  repository.findAllByActiveTrue(pageable).map(DoctorsListData::new);
        return ResponseEntity.ok(page);
    }
    
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DoctorsDataUpdate data){
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
        
        return ResponseEntity.ok(new DoctorsDetailData(doctor));
    }
    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.delete();
        
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DoctorsDetailData> getDoctor(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        
        return ResponseEntity.ok(new DoctorsDetailData(doctor));
    }
    
}
