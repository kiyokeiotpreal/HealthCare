package org.example.doantotnghiep.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doantotnghiep.Service.serviceimpl.ExaminationServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/user")
@RequiredArgsConstructor
public class ExaminationServiceController {
    @Autowired
    private ExaminationServive examinationServive;
    @GetMapping("/getExaminationService")
    public ResponseEntity<?> getExaminationService(@Valid @RequestParam String slugServiceType) throws Exception {
        return ResponseEntity.ok().body(examinationServive.getExaminationService(slugServiceType));
    }
}
