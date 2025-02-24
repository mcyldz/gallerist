package com.mcyldz.service.impl;

import com.mcyldz.dto.DtoAddress;
import com.mcyldz.dto.DtoGallerist;
import com.mcyldz.dto.DtoGalleristIU;
import com.mcyldz.exception.BaseException;
import com.mcyldz.exception.ErrorMessage;
import com.mcyldz.exception.MessageType;
import com.mcyldz.model.Address;
import com.mcyldz.model.Gallerist;
import com.mcyldz.repository.AddressRepository;
import com.mcyldz.repository.GalleristRepository;
import com.mcyldz.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU){
        Optional<Address> optionalAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
        if (optionalAddress.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristIU.getAddressId().toString()));
        }

        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoGalleristIU, gallerist);
        gallerist.setAddress(optionalAddress.get());
        return gallerist;
    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);
        dtoGallerist.setAddress(dtoAddress);

        return dtoGallerist;
    }
}
