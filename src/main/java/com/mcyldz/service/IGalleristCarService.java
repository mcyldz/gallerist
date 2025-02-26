package com.mcyldz.service;

import com.mcyldz.dto.DtoGalleristCar;
import com.mcyldz.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}
