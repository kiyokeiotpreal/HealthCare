package org.example.doantotnghiep.Service.serviceimpl;

import org.example.doantotnghiep.Payload.Response.ExaminationServiceResponse;
import org.example.doantotnghiep.Repository.ExaminationServiceRepo;
import org.example.doantotnghiep.Repository.ServiceTypeRepo;
import org.example.doantotnghiep.Service.iservice.IExaminationService;
import org.example.doantotnghiep.model.ExaminationService;
import org.example.doantotnghiep.model.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExaminationServive implements IExaminationService {
    @Autowired
    private ServiceTypeRepo serviceTypeRepo;
    @Autowired
    private ExaminationServiceRepo examinationServiceRepo;
    @Override
    public List<ExaminationServiceResponse> getExaminationService(String slugServiceName) throws Exception {
        ServiceType serviceType = serviceTypeRepo.findBySlug(slugServiceName);
        List<ExaminationService> examinationServices = new ArrayList<>();
        if(slugServiceName.equals("kham-tinh-than")){
                    examinationServices = examinationServiceRepo.findAll().stream()
                    .filter(service -> service.getName().toLowerCase().contains("tâm thần") || service.getName().toLowerCase().contains("tâm lý"))
                    .collect(Collectors.toList());
        }
//        else if (slugServiceName.equals("goi-phau-thuat")) {
//            examinationServices = examinationServiceRepo.findAll().stream()
//                    .filter(service -> service.getName().toLowerCase().contains("phẫu thuật"))
//                    .collect(Collectors.toList());
//        }
        else {
            examinationServices = examinationServiceRepo.findByServiceType(serviceType);
        }

        return examinationServices.stream()
                .map(examinationService -> {
                    ExaminationServiceResponse examinationServiceResponse = new ExaminationServiceResponse();
                    examinationServiceResponse.setId(examinationService.getId());
                    examinationServiceResponse.setImg(examinationService.getImg());
                    examinationServiceResponse.setName(examinationService.getName());
                    examinationServiceResponse.setServiceTypeName(serviceType.getName());
                    examinationServiceResponse.setSlug(serviceType.getSlug());
                    examinationServiceResponse.setSlugExaminationService(examinationService.getSlug());
                    return examinationServiceResponse;
                })
                .collect(Collectors.toList());
    }

}
