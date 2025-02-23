package com.mcyldz.service;

import com.mcyldz.dto.DtoCustomer;
import com.mcyldz.dto.DtoCustomerIU;

public interface ICustomerService {

    DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
