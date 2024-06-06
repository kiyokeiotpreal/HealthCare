package org.example.doantotnghiep.Service.serviceimpl;

import org.example.doantotnghiep.Payload.Request.specialist_request.AddSpecialistRequest;
import org.example.doantotnghiep.Repository.HospitalRepo;
import org.example.doantotnghiep.Repository.SpecialistRepo;
import org.example.doantotnghiep.Service.iservice.ISpecialistService;
import org.example.doantotnghiep.exceptions.DataIntegrityViolationException;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.example.doantotnghiep.model.Hospital;
import org.example.doantotnghiep.model.Specialist;
import org.example.doantotnghiep.utils.MessageKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialistService implements ISpecialistService {
    @Autowired
    private HospitalRepo hospitalRepo;
    @Autowired
    private SpecialistRepo specialistRepo;
    @Override
    public Specialist addSpecialist(AddSpecialistRequest addSpecialistRequest) throws Exception {
        Hospital hospital = hospitalRepo.findByName(addSpecialistRequest.getHospitalName());
        if(hospital == null){
            throw new DataNotFoundException(MessageKeys.HOSPITAL_DOES_NOT_EXIST);
        }
        if(specialistRepo.existsByNameAndHospitalName(addSpecialistRequest.getName(), addSpecialistRequest.getHospitalName())){
            throw new DataIntegrityViolationException(MessageKeys.SPECIALIST_ALREADY_EXISTS);
        }
        Specialist specialist = new Specialist();
        specialist.setName(addSpecialistRequest.getName());
        specialist.setDescription(addSpecialistRequest.getDescription());
        specialist.setHospital(hospital);
        specialistRepo.save(specialist);
        return specialist;
    }
}
