package com.mcyldz.controller;

import com.mcyldz.dto.DtoSaledCar;
import com.mcyldz.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

    RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
