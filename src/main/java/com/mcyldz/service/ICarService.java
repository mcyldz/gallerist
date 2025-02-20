package com.mcyldz.service;

import com.mcyldz.dto.DtoCar;
import com.mcyldz.dto.DtoCarIU;

public interface ICarService {

    DtoCar saveCar(DtoCarIU dtoCarIU);
}
