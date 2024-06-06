package org.example.doantotnghiep.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doantotnghiep.Payload.Request.schedule_request.AddScheduleRequest;
import org.example.doantotnghiep.Payload.Request.schedule_request.BookScheduleRequest;
import org.example.doantotnghiep.Service.serviceimpl.ScheduleService;
import org.example.doantotnghiep.exceptions.DataIntegrityViolationException;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/user")
@RequiredArgsConstructor
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;


    @GetMapping("/getScheduleByExamName")
    public ResponseEntity<?> getDoctorBySpecialist(@Valid @RequestParam String name) throws Exception {
        try {
            return ResponseEntity.ok().body(scheduleService.getScheduleByExamName(name));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getScheduleByDoctor")
    public ResponseEntity<?> getScheduleByDoctor(@Valid @RequestParam String email) throws Exception {
        try {
            return ResponseEntity.ok().body(scheduleService.getScheduleByDoctor(email));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/addSchedule")
    public ResponseEntity<?> addSchedule(@Valid @RequestBody AddScheduleRequest addScheduleRequest) throws Exception {
        try {
            return ResponseEntity.ok().body(scheduleService.addSchedule(addScheduleRequest));
        }catch (DataNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (DataIntegrityViolationException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/resetSchedule")
    public ResponseEntity<?> resetSchedule(@Valid @RequestParam int id) throws Exception {
        try {
            return ResponseEntity.ok().body(scheduleService.resetSchedule(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @DeleteMapping("/deleteSchedule")
    public ResponseEntity<?> deleteSchedule(@Valid @RequestParam int id) throws Exception {
        try {
            return ResponseEntity.ok().body(scheduleService.deleteSchedule(id));
        }catch (DataNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/bookingSchedule")
    public ResponseEntity<?> bookingSchedule(@Valid @RequestBody BookScheduleRequest bookScheduleRequest) throws Exception{
        try {
            return ResponseEntity.ok().body(scheduleService.bookingSchedule(bookScheduleRequest));
        }catch (DataNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (DataIntegrityViolationException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }
}
