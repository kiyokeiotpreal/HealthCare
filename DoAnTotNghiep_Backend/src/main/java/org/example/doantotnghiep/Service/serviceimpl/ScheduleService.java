package org.example.doantotnghiep.Service.serviceimpl;

import io.micrometer.common.util.StringUtils;
import org.example.doantotnghiep.Payload.Request.schedule_request.AddScheduleRequest;
import org.example.doantotnghiep.Payload.Request.schedule_request.BookScheduleRequest;
import org.example.doantotnghiep.Payload.Response.DoctorResponse;
import org.example.doantotnghiep.Payload.Response.ScheduleResponse;
import org.example.doantotnghiep.Payload.Response.Services2ScheduleResponse;
import org.example.doantotnghiep.Repository.*;
import org.example.doantotnghiep.Service.iservice.IScheduleService;
import org.example.doantotnghiep.exceptions.DataIntegrityViolationException;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.example.doantotnghiep.model.*;
import org.example.doantotnghiep.utils.MessageKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
public class ScheduleService implements IScheduleService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SpecialistRepo specialistRepo;
    @Autowired
    private ScheduleRepo scheduleRepo;
    @Autowired
    private PathologicalRepo pathologicalRepo;
    @Autowired
    private ServiceTypeRepo serviceTypeRepo;
    @Autowired
    private ExaminationServiceRepo examinationServiceRepo;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ClinicRepo clinicRepo;

    @Override
    public String addSchedule(AddScheduleRequest addScheduleRequest) throws Exception {
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();
        // Định dạng ngày tháng
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Chuyển đổi ngày từ String sang LocalDate
        LocalDate requestDate = LocalDate.parse(addScheduleRequest.getDate(), dateFormatter);

        LocalTime startTime = LocalTime.parse(addScheduleRequest.getStartTime());
        LocalTime endTime = LocalTime.parse(addScheduleRequest.getEndTime());

        if (currentDate.isAfter(requestDate)  || endTime.isBefore(startTime)) {
            throw new DataIntegrityViolationException("Ngày hoặc thời gian không hợp lệ");
        }
        Clinic clinic = clinicRepo.findById(addScheduleRequest.getClinicId()).orElse(null);
        if(clinic == null) {
            throw new DataNotFoundException("Phòng khám không tồn tại");
        }
        Schedule schedule = new Schedule();
        schedule.setStartAt(convertToLocalDateTime(addScheduleRequest.getDate(), addScheduleRequest.getStartTime()));
        schedule.setEndAt(convertToLocalDateTime(addScheduleRequest.getDate(), addScheduleRequest.getEndTime()));
        schedule.setEmpty(true);
        schedule.setClinic(clinic);
        schedule.setDoctor(userRepo.findByEmail(addScheduleRequest.getEmailDoctor()).get());
        scheduleRepo.save(schedule);
        return "Thêm lịch trình thành công";
    }

    @Override
    public String resetSchedule(int id) throws Exception {
        Schedule schedule = scheduleRepo.findById(id).get();
        schedule.setNamePatient(null);
        schedule.setYearPatient(null);
        schedule.setGenderPatient(null);
        schedule.setPhonePatient(null);
        schedule.setEmailPatient(null);
        schedule.setReason(null);
        schedule.setMedicalHistoryPatient(null);
        schedule.setEmpty(true);
        scheduleRepo.save(schedule);
        return "Reset lịch trình thành công";
    }


    @Override
    public String deleteSchedule(int id) throws Exception {
        Schedule schedule = scheduleRepo.findById(id).orElse(null);
        if(schedule == null){
            throw new DataNotFoundException("Lịch trình này không tồn tại hoặc đã bị xoá");
        }
        scheduleRepo.deleteById(id);
        return "Xoá lịch trình thành công !";
    }

    public static LocalDateTime convertToLocalDateTime(String date, String time) {
        // Định dạng ngày tháng
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Chuyển đổi date từ String sang LocalDate
        LocalDate localDate = LocalDate.parse(date, dateFormatter);

        // Chuyển đổi startTime từ String sang LocalTime
        LocalTime localTime = LocalTime.parse(time);

        // Kết hợp localDate và localTime để tạo LocalDateTime
        return localDate.atTime(localTime);
    }

    @Override
    public Services2ScheduleResponse getScheduleByExamName(String examName) throws Exception {
        ExaminationService examinationService = examinationServiceRepo.findByName(examName);

        Services2ScheduleResponse response = new Services2ScheduleResponse();
        response.setAvata(examinationService.getImg());
        response.setName(examinationService.getName());
        response.setExaminationServiceName(examinationService.getName());
        response.setShortenedName(examinationService.getShortenedName());
        response.setPrice(examinationService.getPrice());
        response.setDescription(examinationService.getDescription());
        response.setServiceTypeName(serviceTypeRepo.findById(examinationService.getServiceType().getId()).get().getName());
        Set<Schedule> schedules = scheduleRepo.findByExaminationService(examinationService);
        Set<ScheduleResponse> scheduleResponses = new TreeSet<>(Comparator.comparing(ScheduleResponse::getDateTimeKey));
        boolean clinicInfoSet = false;
        for (Schedule schedule : schedules) {
            ScheduleResponse scheduleResponse = new ScheduleResponse();
            scheduleResponse.setId(schedule.getId());
            scheduleResponse.setDayofWeek(getDayOfWeek(schedule.getStartAt()));
            scheduleResponse.setDate(getDate(schedule.getStartAt()));
            scheduleResponse.setTime(formatTime(schedule.getStartAt(), schedule.getEndAt()));
            scheduleResponse.setEmpty(schedule.isEmpty());
            if (!clinicInfoSet) {
                response.setClinicName(schedule.getClinic().getName());
                response.setAddressClinic(schedule.getClinic().getAddress());
                clinicInfoSet = true;
            }
            scheduleResponses.add(scheduleResponse);
        }
        response.setScheduleResponses(scheduleResponses);
        return response;
    }

    @Override
    public DoctorResponse getScheduleByDoctor(String email) throws Exception {
        User doctor = userRepo.findByEmail(email).get();
        Specialist specialist = specialistRepo.findById(doctor.getSpecialist().getId()).get();
        ExaminationService examinationService = examinationServiceRepo.findById(doctor.getExaminationService().getId()).get();
        List<String> pathologicals = new ArrayList<>();
        List<Pathological> pathologicalSet = pathologicalRepo.findBySpecialist(specialist);
        for (Pathological pathological : pathologicalSet){
            pathologicals.add(pathological.getName());
        }
        DoctorResponse doctorResponse = new DoctorResponse();

        doctorResponse.setPathologicals(pathologicals);
        doctorResponse.setAvata(doctor.getAvata());
        doctorResponse.setName(doctor.getName());
        doctorResponse.setExaminationServicename(examinationService.getName());
        if(examinationService.getName().contains("từ xa")){
            doctorResponse.setServiceTypeName(serviceTypeRepo.findById(3).get().getName());
        }else {
            doctorResponse.setServiceTypeName(serviceTypeRepo.findById(1).get().getName());
        }
        doctorResponse.setPosition(doctor.getPosition());
        doctorResponse.setDescription(doctor.getDescription());
        doctorResponse.setPrice(doctor.getPrice());
        Set<Schedule> schedules = scheduleRepo.findByDoctor(doctor);
        Set<ScheduleResponse> scheduleResponses = new TreeSet<>(Comparator.comparing(ScheduleResponse::getDateTimeKey));
        boolean clinicInfoSet = false;
        for (Schedule schedule : schedules) {
            ScheduleResponse scheduleResponse = new ScheduleResponse();
            scheduleResponse.setId(schedule.getId());
            scheduleResponse.setDayofWeek(getDayOfWeek(schedule.getStartAt()));
            scheduleResponse.setDate(getDate(schedule.getStartAt()));
            scheduleResponse.setTime(formatTime(schedule.getStartAt(), schedule.getEndAt()));
            scheduleResponse.setEmpty(schedule.isEmpty());
            scheduleResponse.setNamePatient(schedule.getNamePatient());
            scheduleResponse.setEmailPatient(schedule.getEmailPatient());
            scheduleResponse.setYearPatient(schedule.getYearPatient());
            scheduleResponse.setReason(schedule.getReason());
            scheduleResponse.setGenderPatient(schedule.getGenderPatient());
            scheduleResponse.setPhonePatient(schedule.getPhonePatient());
            scheduleResponse.setMedicalHistoryPatient(schedule.getMedicalHistoryPatient());
            if (!clinicInfoSet) {
                doctorResponse.setClinicName(schedule.getClinic().getName());
                doctorResponse.setAddressClinic(schedule.getClinic().getAddress());
                clinicInfoSet = true;
            }
            scheduleResponses.add(scheduleResponse);
        }
        doctorResponse.setScheduleResponses(scheduleResponses);
        return doctorResponse;
    }

    @Override
    public String bookingSchedule(BookScheduleRequest bookScheduleRequest) throws Exception {
        Schedule schedule = scheduleRepo.findById(bookScheduleRequest.getScheduleID()).get();
        if(!schedule.isEmpty()){
            throw new DataNotFoundException(MessageKeys.SCHEDULE_HAS_BEEN_BOOKED);
        }
        // Kiểm tra xem các trường có rỗng không
        if (StringUtils.isBlank(bookScheduleRequest.getNamePatient()) ||
                StringUtils.isBlank(bookScheduleRequest.getPhonePatient()) ||
                StringUtils.isBlank(bookScheduleRequest.getEmailPatient()) ||
                StringUtils.isBlank(bookScheduleRequest.getGenderPatient()) ||
                StringUtils.isBlank(bookScheduleRequest.getYearPatient()) ||
                StringUtils.isBlank(bookScheduleRequest.getReason())) {
            throw new DataIntegrityViolationException("Các trường thông tin bệnh nhân không được để trống");
        }

        schedule.setNamePatient(bookScheduleRequest.getNamePatient());
        schedule.setPhonePatient(bookScheduleRequest.getPhonePatient());
        schedule.setEmailPatient(bookScheduleRequest.getEmailPatient());
        schedule.setGenderPatient(bookScheduleRequest.getGenderPatient());
        schedule.setYearPatient(bookScheduleRequest.getYearPatient());
        schedule.setReason(bookScheduleRequest.getReason());
        schedule.setMedicalHistoryPatient(bookScheduleRequest.getMedicalHistoryPatient());
        schedule.setEmpty(false);
        scheduleRepo.save(schedule);
        sendConfirmEmail(bookScheduleRequest.getEmailPatient(), bookScheduleRequest.getNamePatient(), getDayOfWeekAndDate(schedule.getStartAt()),
                formatTime(schedule.getStartAt(), schedule.getEndAt()), schedule.getClinic().getName() ,schedule.getClinic().getAddress());
        return "Đặt lịch thành công";
    }
    private void sendEmail(String to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ntnamnt2k2@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);

    }
    public void sendConfirmEmail(String email,String namePatient, String dayOfWeek, String time, String clinicName, String clinicAddress) {
        //todo Gửi email với mã code và thông tin
        String subject = "Xác Nhận Đặt Lịch Khám Thành Công - HealthCare";
        String content = " Xin chào, " + namePatient + ". \n Cảm ơn bạn đã tin tưởng và đặt lịch khám qua HealthCare.\n"
        + "\n Thông tin chi tiết về lịch khám :"
                + "\n    Ngày khám : " + dayOfWeek + "\n    Thời gian : "+ time + "\n    Phòng khám : "
                + clinicName + "\n    Địa chỉ : " + clinicAddress + "\n Xin vui lòng để ý điện thoại. Chúng tôi sẽ liên hệ với bạn sớm !\n"
                + "\n Nếu cần thêm thông tin hoặc hỗ trợ, vui lòng liên hệ với chúng tôi qua số điện thoại 0904167382 hoặc healthcare@gmail.com.\n"
                +"\n Trân trọng," + "\n HealthCare Team";
        sendEmail(email,subject,content);
    }

    public static String getDayOfWeek(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        return convertDayOfWeekToString(dayOfWeek);
    }

    public static String getDayOfWeekAndDate(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        String dayOfWeekStr = convertDayOfWeekToString(dayOfWeek);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        String dateStr = dateTime.format(dateFormatter);

        return dayOfWeekStr + " - " + dateStr;
    }

    public static String convertDayOfWeekToString(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return "Thứ 2";
            case TUESDAY:
                return "Thứ 3";
            case WEDNESDAY:
                return "Thứ 4";
            case THURSDAY:
                return "Thứ 5";
            case FRIDAY:
                return "Thứ 6";
            case SATURDAY:
                return "Thứ 7";
            case SUNDAY:
                return "Chủ nhật";
            default:
                return ""; // Trường hợp không xác định
        }
    }

    public static String getDate(LocalDateTime dateTime) {
        int day = dateTime.getDayOfMonth();
        int month = dateTime.getMonthValue();
        String dayStr = (day < 10) ? "0" + day : String.valueOf(day);
        String monthStr = (month < 10) ? "0" + month : String.valueOf(month);
        return dayStr + "/" + monthStr + "/" + dateTime.getYear();
    }

    public static String formatTime(LocalDateTime startAt, LocalDateTime endAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedStart = startAt.format(formatter);
        String formattedEnd = endAt.format(formatter);
        return formattedStart + "-" + formattedEnd;
    }
    // Định nghĩa hàm để lấy thời gian bắt đầu đã được định dạng
    private String getStartAtFormatted(ScheduleResponse scheduleResponse) {
        return scheduleResponse.getTime().split("-")[0];
    }

}
