package org.example.doantotnghiep.Service.serviceimpl;

import org.example.doantotnghiep.Payload.Request.hospital_request.AddHospitalRequest;
import org.example.doantotnghiep.Payload.Request.hospital_request.UpdateHospitalRequest;
import org.example.doantotnghiep.Repository.HospitalRepo;
import org.example.doantotnghiep.Service.iservice.IHospitalService;
import org.example.doantotnghiep.exceptions.DataIntegrityViolationException;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.example.doantotnghiep.model.Hospital;
import org.example.doantotnghiep.utils.MessageKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService implements IHospitalService {

    @Autowired
    private HospitalRepo hospitalRepo;
    @Override
    public Hospital addHospital(AddHospitalRequest addHospital) throws Exception{
        if (hospitalRepo.existsByName(addHospital.getName())){
            throw new DataIntegrityViolationException(MessageKeys.HOSPITAL_ALREADY_EXISTS);
        }
        Hospital hospital = new Hospital();
        hospital.setCode(addHospital.getCode());
        hospital.setAddress(addHospital.getAddress());
        hospital.setName(addHospital.getName());
        hospital.setLogoImg(addHospital.getLogoImg());
        hospital.setPositionDescription(addHospital.getPositionDescription());
        hospital.setProcessImg(addHospital.getProcessImg());
        hospital.setPositionImg(addHospital.getPositionImg());
        hospitalRepo.save(hospital);
        return hospital;
    }

    @Override
    public Hospital updateHospital(UpdateHospitalRequest updateHospital) throws Exception{
        Hospital currentHospital = hospitalRepo.findById(updateHospital.getId()).orElse(null);
        if(currentHospital ==  null){
            throw new DataNotFoundException(MessageKeys.HOSPITAL_DOES_NOT_EXIST);
        }
        currentHospital.setCode(updateHospital.getCode());
        currentHospital.setAddress(updateHospital.getAddress());
        currentHospital.setName(updateHospital.getName());
        currentHospital.setLogoImg(updateHospital.getLogoImg());
        currentHospital.setPositionDescription(updateHospital.getPositionDescription());
        currentHospital.setProcessImg(updateHospital.getProcessImg());
        currentHospital.setPositionImg(updateHospital.getPositionImg());
        hospitalRepo.save(currentHospital);
        return currentHospital;
    }

    @Override
    public Hospital deleteHospital(int hospitalId) {
        return null;
    }
}
