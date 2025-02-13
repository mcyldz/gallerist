package com.mcyldz.controller.impl;

import com.mcyldz.controller.IRestAuthenticationController;
import com.mcyldz.controller.RestBaseController;
import com.mcyldz.controller.RootEntity;
import com.mcyldz.dto.AuthRequest;
import com.mcyldz.dto.AuthResponse;
import com.mcyldz.dto.DtoUser;
import com.mcyldz.dto.RefreshTokenRequest;
import com.mcyldz.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping(value = "/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest request) {
        return ok(authenticationService.register(request));
    }

    @PostMapping(value = "/authenticate")
    @Override
    public RootEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
        return ok(authenticationService.authenticate(request));
    }

    @PostMapping(value = "/refreshToken")
    @Override
    public RootEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return ok(authenticationService.refreshToken(request));
    }
}
