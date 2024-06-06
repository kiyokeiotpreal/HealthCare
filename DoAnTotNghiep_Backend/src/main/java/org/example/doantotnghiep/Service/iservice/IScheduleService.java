package org.example.doantotnghiep.Service.iservice;

import org.example.doantotnghiep.Payload.Request.schedule_request.AddScheduleRequest;
import org.example.doantotnghiep.Payload.Request.schedule_request.BookScheduleRequest;
import org.example.doantotnghiep.Payload.Response.DoctorResponse;
import org.example.doantotnghiep.Payload.Response.Services2ScheduleResponse;

public interface IScheduleService {

    String addSchedule(AddScheduleRequest addScheduleRequest) throws Exception;

    String resetSchedule(int id) throws Exception;
    String deleteSchedule(int id) throws Exception;
    Services2ScheduleResponse getScheduleByExamName(String examName) throws Exception;
    DoctorResponse getScheduleByDoctor(String email) throws Exception;

    String bookingSchedule(BookScheduleRequest bookScheduleRequest) throws Exception;
}
