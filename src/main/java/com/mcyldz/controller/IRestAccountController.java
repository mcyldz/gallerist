package com.mcyldz.controller;

import com.mcyldz.dto.DtoAccount;
import com.mcyldz.dto.DtoAccountIU;

public interface IRestAccountController {

    RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
