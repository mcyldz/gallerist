package com.mcyldz.controller;

import com.mcyldz.dto.DtoGalleristCar;
import com.mcyldz.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

    RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
