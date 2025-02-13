package com.mcyldz.controller;

import com.mcyldz.dto.DtoAddress;
import com.mcyldz.dto.DtoAddressIU;

public interface IRestAddressController {

    RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
