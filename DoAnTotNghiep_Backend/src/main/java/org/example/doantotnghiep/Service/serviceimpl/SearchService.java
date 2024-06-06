package org.example.doantotnghiep.Service.serviceimpl;

import org.example.doantotnghiep.Payload.Response.DoctorSearchResponse;
import org.example.doantotnghiep.Payload.Response.ExamServiceSearchResponse;
import org.example.doantotnghiep.Payload.Response.SearchResponse;
import org.example.doantotnghiep.Repository.ExaminationServiceRepo;
import org.example.doantotnghiep.Repository.UserRepo;
import org.example.doantotnghiep.Service.iservice.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService implements ISearchService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ExaminationServiceRepo examinationServiceRepo;

    @Override
    public SearchResponse search(String name) throws Exception {
        SearchResponse searchResponse = new SearchResponse();

        List<DoctorSearchResponse> doctorSearchResponses = userRepo.findAll().stream()
                .filter(user -> user.getRole().getId() == 2)
                .filter(doctor -> doctor.getName().toLowerCase().contains(name.toLowerCase()))
                .map(doctor -> DoctorSearchResponse.builder()
                        .avata(doctor.getAvata())
                        .position(doctor.getPosition())
                        .name(doctor.getName())
                        .specialistName(doctor.getSpecialist().getName())
                        .email(doctor.getEmail())
                        .slug(doctor.getSlug())
                        .build())
                .collect(Collectors.toList());

        List<ExamServiceSearchResponse> examServiceSearchResponses = examinationServiceRepo.findAll().stream()
                .filter(exam -> exam.getName().toLowerCase().contains(name.toLowerCase()))
                .map(exam -> ExamServiceSearchResponse.builder()
                        .avata(exam.getImg())
                        .name(exam.getName())
                        .serviceTypeId(exam.getServiceType().getId())
                        .slug(exam.getSlug())
                        .build())
                .collect(Collectors.toList());

        searchResponse.setDoctorSearchResponses(doctorSearchResponses);
        searchResponse.setExamServiceSearchResponses(examServiceSearchResponses);
        return searchResponse;
    }

}
