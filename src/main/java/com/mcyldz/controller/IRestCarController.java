package com.mcyldz.controller;

import com.mcyldz.dto.DtoCar;
import com.mcyldz.dto.DtoCarIU;

public interface IRestCarController {

    RootEntity<DtoCar> saveCar(DtoCarIU dtoCarIU);
}
