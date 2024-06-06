package org.example.doantotnghiep.Service.iservice;

import org.example.doantotnghiep.Payload.Request.hospital_request.AddHospitalRequest;
import org.example.doantotnghiep.Payload.Request.hospital_request.UpdateHospitalRequest;
import org.example.doantotnghiep.model.Hospital;

public interface IHospitalService {
    Hospital addHospital(AddHospitalRequest hospital) throws Exception;
    Hospital updateHospital(UpdateHospitalRequest hospital) throws Exception;
    Hospital deleteHospital(int hospitalId) throws Exception;
}
