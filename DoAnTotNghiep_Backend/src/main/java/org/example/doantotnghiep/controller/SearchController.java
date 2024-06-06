package org.example.doantotnghiep.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doantotnghiep.Payload.Request.specialist_request.AddSpecialistRequest;
import org.example.doantotnghiep.Payload.Response.DoctorSearchResponse;
import org.example.doantotnghiep.Payload.Response.SearchResponse;
import org.example.doantotnghiep.Service.serviceimpl.SearchService;
import org.example.doantotnghiep.Service.serviceimpl.SpecialistService;
import org.example.doantotnghiep.exceptions.DataIntegrityViolationException;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.example.doantotnghiep.model.Specialist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/user")
@RequiredArgsConstructor
public class SearchController {
    @Autowired
    private SearchService searchService;
    @GetMapping("/search")
    public ResponseEntity<?> Search(@Valid @RequestParam String name){
        try {
            SearchResponse result = searchService.search(name);
            return ResponseEntity.ok().body(result);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
