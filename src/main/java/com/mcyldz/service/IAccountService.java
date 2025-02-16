package com.mcyldz.service;

import com.mcyldz.dto.DtoAccount;
import com.mcyldz.dto.DtoAccountIU;

public interface IAccountService {

    DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
