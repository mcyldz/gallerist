package com.mcyldz.service;

import com.mcyldz.dto.DtoGallerist;
import com.mcyldz.dto.DtoGalleristIU;

public interface IGalleristService {

    DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
