package com.mcyldz.service;

import com.mcyldz.dto.DtoSaledCar;
import com.mcyldz.dto.DtoSaledCarIU;

public interface ISaledCarService {

    DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU);
}
