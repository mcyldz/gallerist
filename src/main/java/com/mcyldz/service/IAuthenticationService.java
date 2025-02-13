package com.mcyldz.service;

import com.mcyldz.dto.AuthRequest;
import com.mcyldz.dto.AuthResponse;
import com.mcyldz.dto.DtoUser;
import com.mcyldz.dto.RefreshTokenRequest;
import com.mcyldz.model.RefreshToken;

public interface IAuthenticationService {

    DtoUser register(AuthRequest request);

    AuthResponse authenticate(AuthRequest request);

    AuthResponse refreshToken(RefreshTokenRequest request);
}
