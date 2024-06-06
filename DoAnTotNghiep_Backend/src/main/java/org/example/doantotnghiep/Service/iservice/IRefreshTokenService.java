package org.example.doantotnghiep.Service.iservice;


import org.example.doantotnghiep.model.RefreshToken;

public interface IRefreshTokenService {
    RefreshToken createRefreshToken (int userID) throws Exception;
}
