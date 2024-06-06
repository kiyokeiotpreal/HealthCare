package org.example.doantotnghiep.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doantotnghiep.Payload.Request.hospital_request.AddHospitalRequest;
import org.example.doantotnghiep.Payload.Request.specialist_request.AddSpecialistRequest;
import org.example.doantotnghiep.Service.serviceimpl.HospitalService;
import org.example.doantotnghiep.Service.serviceimpl.SpecialistService;
import org.example.doantotnghiep.exceptions.DataIntegrityViolationException;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.example.doantotnghiep.model.Hospital;
import org.example.doantotnghiep.model.Specialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/user")
@RequiredArgsConstructor
public class SpecialistController {
    @Autowired
    private SpecialistService specialistService;
    @PostMapping("/addSpecialist")
    public ResponseEntity<?> addSpecialist(@Valid @RequestBody AddSpecialistRequest addSpecialistRequest){
        try {
            Specialist result = specialistService.addSpecialist(addSpecialistRequest);
            return ResponseEntity.ok().body(result);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (DataNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
