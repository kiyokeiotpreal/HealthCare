package org.example.doantotnghiep.Service.serviceimpl;

import org.example.doantotnghiep.Repository.RefreshTokenRepo;
import org.example.doantotnghiep.Repository.UserRepo;
import org.example.doantotnghiep.Service.iservice.IRefreshTokenService;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.example.doantotnghiep.model.RefreshToken;
import org.example.doantotnghiep.model.User;
import org.example.doantotnghiep.utils.MessageKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenService implements IRefreshTokenService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
    @Value("${jwt.expirationRefreshToken}")
    private int expirationRefreshToken;
    @Override
    public RefreshToken createRefreshToken(int userID) throws Exception {
        User user = userRepo.findById(userID).orElse(null);
        if(user == null){
            throw  new DataNotFoundException(MessageKeys.EMAIL_DOES_NOT_EXISTS);
        }
        if (!user.isActive()){
            throw new Exception(MessageKeys.USER_ACCOUNT_IS_DISABLED);
        }
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .expiredTime(LocalDateTime.now().plusSeconds(expirationRefreshToken))
                .user(user)
                .build();
        refreshToken = refreshTokenRepo.save(refreshToken);
        return refreshToken;
    }
}
