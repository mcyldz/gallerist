package com.mcyldz.controller;

import com.mcyldz.dto.DtoCustomer;
import com.mcyldz.dto.DtoCustomerIU;

public interface IRestCustomerController {

    RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
