package com.mcyldz.service;

import com.mcyldz.dto.DtoAddress;
import com.mcyldz.dto.DtoAddressIU;

public interface IAddressService {

    DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
}
