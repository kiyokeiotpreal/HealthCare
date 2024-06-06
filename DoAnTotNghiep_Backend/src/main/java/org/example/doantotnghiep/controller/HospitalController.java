package org.example.doantotnghiep.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doantotnghiep.Payload.Request.hospital_request.AddHospitalRequest;
import org.example.doantotnghiep.Payload.Request.hospital_request.UpdateHospitalRequest;
import org.example.doantotnghiep.Service.serviceimpl.HospitalService;
import org.example.doantotnghiep.exceptions.DataIntegrityViolationException;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.example.doantotnghiep.model.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/user")
@RequiredArgsConstructor
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;
    @PostMapping("/addHospital")
    public ResponseEntity<?> addHospital(@Valid @RequestBody AddHospitalRequest hospital){
        try {
            Hospital result = hospitalService.addHospital(hospital);
            return ResponseEntity.ok().body(result);
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/updateHospital")
    public ResponseEntity<?> updateHospital(@Valid @RequestBody UpdateHospitalRequest hospital){
        try {
            Hospital result = hospitalService.updateHospital(hospital);
            return ResponseEntity.ok().body(result);
        }catch (DataNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
