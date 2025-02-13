package com.mcyldz.controller;

import com.mcyldz.dto.AuthRequest;
import com.mcyldz.dto.AuthResponse;
import com.mcyldz.dto.DtoUser;
import com.mcyldz.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

    RootEntity<DtoUser> register(AuthRequest request);

    RootEntity<AuthResponse> authenticate(AuthRequest request);

    RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
