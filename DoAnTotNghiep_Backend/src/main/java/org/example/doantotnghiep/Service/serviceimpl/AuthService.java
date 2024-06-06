package org.example.doantotnghiep.Service.serviceimpl;

import lombok.Getter;
import org.example.doantotnghiep.Payload.Request.auth_request.ChangePasswordRequest;
import org.example.doantotnghiep.Payload.Request.auth_request.ConfirmForgotPasswordRequest;
import org.example.doantotnghiep.Payload.Request.auth_request.LoginRequest;
import org.example.doantotnghiep.Payload.Request.auth_request.RegisterRequest;
import org.example.doantotnghiep.Payload.Response.LoginResponse;
import org.example.doantotnghiep.Repository.ConfirmEmailRepo;
import org.example.doantotnghiep.Repository.UserRepo;
import org.example.doantotnghiep.Service.iservice.IAuthService;
import org.example.doantotnghiep.components.JwtTokenUtils;
import org.example.doantotnghiep.exceptions.ConfirmEmailExpired;
import org.example.doantotnghiep.exceptions.ConfirmPasswordIncorrect;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.example.doantotnghiep.model.ConfirmEmail;
import org.example.doantotnghiep.model.RefreshToken;
import org.example.doantotnghiep.model.User;
import org.example.doantotnghiep.utils.MessageKeys;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AuthService implements IAuthService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
    private  final AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private ConfirmEmailRepo confirmEmailRepo;
    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    private final Map<String, RegisterRequest> pendingRegistrations = new HashMap<>();
    @Getter
    public String emailTemporary = "";

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(RegisterRequest registerRequest){
        String email = registerRequest.getEmail();

        if(userRepo.existsByEmail(email)){
            throw new DataIntegrityViolationException(MessageKeys.EMAIL_ALREADY_EXISTS);
        }

        pendingRegistrations.put(email, registerRequest);
        User user = userRepo.findByEmail(registerRequest.getEmail()).orElse(null);
        sendConfirmEmail(email);

        emailTemporary = registerRequest.getEmail();

    }
    public RegisterRequest getPendingRegistration(String email) {
        return pendingRegistrations.get(email);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws Exception {
        User existingUser = userRepo.findByEmail(loginRequest.getEmail()).orElse(null);
        if(existingUser == null) {
            throw new DataNotFoundException(MessageKeys.EMAIL_DOES_NOT_EXISTS);
        }
        if(!existingUser.isActive()){
            throw new DisabledException(MessageKeys.USER_ACCOUNT_IS_DISABLED);
        }

        //Chuyền email,password, role vào authenticationToken để xac thực ngươi dùng
        UsernamePasswordAuthenticationToken auToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword(),
                existingUser.getAuthorities()
        );
        //Xác thực người dùng (nếu xác thực không thành công VD: sai pass ) thì sẽ ném ra ngoại lệ
        authenticationManager.authenticate(auToken);

        //Lấy role của user
        User userDetails = (User) userDetailsService.loadUserByUsername(loginRequest.getEmail());
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        //sinh ngẫu nhiên 1 token từ existingUser
        String token = jwtTokenUtils.generateToken(existingUser);

        RefreshToken refreshTokens = refreshTokenService.createRefreshToken(existingUser.getId());

        LoginResponse loginDTO= LoginResponse.builder()
                .id(existingUser.getId())
                .userName(existingUser.getUsername())
                .token(token)
                .refreshToken(refreshTokens.getToken())
                .roles(roles)
                .build();
        return loginDTO;
    }

    @Override
    public String changePassWord(ChangePasswordRequest changePasswordRequest) throws Exception {
        User user = userRepo.findByEmail(changePasswordRequest.getEmail()).get();
        String oldPassword = user.getPassword();
        boolean passwordMatch = bCrypt.matches(changePasswordRequest.getOldPassword(), oldPassword);
        if(!passwordMatch){
            throw new DataNotFoundException(MessageKeys.CURRENT_PASSWORD_INCORRECT);
        }
        if(!changePasswordRequest.getNewPassword().equals(changePasswordRequest.getRetypePassword())){
            throw new DataIntegrityViolationException(MessageKeys.CONFIRM_PASSWORD_INCORRECT);
        }
        user.setPassword(bCrypt.encode(changePasswordRequest.getNewPassword()));
        userRepo.save(user);
        return "Đổi mật khẩu thành công";
    }


    @Override
    public void requestForgotPassword(String email) throws Exception {
        User user = userRepo.findByEmail(email).orElse(null);
        if(user == null) {
            throw new DataNotFoundException(MessageKeys.EMAIL_DOES_NOT_EXISTS);
        }
        if(!user.isActive()){
            throw new DisabledException(MessageKeys.USER_ACCOUNT_IS_DISABLED);
        }
    }

    @Override
    public void confirmForgotPassword(ConfirmForgotPasswordRequest confirmForgotPasswordRequest) throws Exception {
        ConfirmEmail confirmEmail = confirmEmailRepo.findConfirmEmailByConfirmCode(confirmForgotPasswordRequest.getConfirmCode());
        if(confirmEmail == null){
            throw new DataNotFoundException(MessageKeys.INCORRECT_VERIFICATION_CODE);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String mkMoi = confirmForgotPasswordRequest.getMatKhauMoi();
        if(!mkMoi.equals(confirmForgotPasswordRequest.getXacNhanMatKhau())){
            throw new ConfirmPasswordIncorrect(MessageKeys.CONFIRM_PASSWORD_INCORRECT);
        }
        User user = userRepo.findByConfirmEmails(confirmEmail);
        user.setPassword(bCryptPasswordEncoder.encode(mkMoi));
        userRepo.save(user);
        confirmEmail.setUser(null);
        confirmEmailRepo.delete(confirmEmail);
    }

    public User registerRequestToUser(RegisterRequest registerRequest){
        return this.modelMapper.map(registerRequest, User.class);
    }

    @Autowired
    private JavaMailSender javaMailSender;
    private String generateConfirmCode() {
        Random random = new Random();
        int randomNumber = random.nextInt(900000) + 100000;
        return String.valueOf(randomNumber);
    }
    private void sendEmail(String to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ntnamnt2k2@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);

    }

    public boolean isExpired(ConfirmEmail confirmEmail) {
        return LocalDateTime.now().isAfter(confirmEmail.getExpiredTime());
    }

    @Override
    public void sendConfirmEmail(String email) {
        ConfirmEmail confirmEmail = ConfirmEmail.builder()
                .confirmCode(generateConfirmCode())
                .expiredTime(LocalDateTime.now().plusSeconds(60))
                .isConfirm(false)
                .requiredTime(LocalDateTime.now())
                .build();
        confirmEmailRepo.save(confirmEmail);

        //todo Gửi email với mã code và thông tin
        String subject ="Xác nhận email của bạn";
        String content = "Mã xác thực của bạn là: " + confirmEmail.getConfirmCode();
        sendEmail(email,subject,content);
    }

    @Override
    public boolean confirmEmail(String confirmCode) throws Exception {
        ConfirmEmail confirmEmail = confirmEmailRepo.findConfirmEmailByConfirmCode(confirmCode);

        if(confirmEmail == null ){
            throw  new DataNotFoundException(MessageKeys.INCORRECT_VERIFICATION_CODE);
        }

        if (isExpired(confirmEmail)){
            throw new ConfirmEmailExpired(MessageKeys.VERIFICATION_CODE_HAS_EXPIRED);
        }

        confirmEmailRepo.delete(confirmEmail);

        return true;
    }
}
