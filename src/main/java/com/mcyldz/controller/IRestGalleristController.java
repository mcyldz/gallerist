package com.mcyldz.controller;

import com.mcyldz.dto.DtoGallerist;
import com.mcyldz.dto.DtoGalleristIU;

public interface IRestGalleristController {

    RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU dtoGalleristIU);
}
