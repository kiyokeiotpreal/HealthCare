package org.example.doantotnghiep.Service.iservice;

import org.example.doantotnghiep.Payload.Request.specialist_request.AddSpecialistRequest;
import org.example.doantotnghiep.model.Specialist;

public interface ISpecialistService {
    Specialist addSpecialist(AddSpecialistRequest addSpecialistRequest) throws Exception;


}
