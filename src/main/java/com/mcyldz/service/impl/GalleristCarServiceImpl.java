package com.mcyldz.service.impl;

import com.mcyldz.dto.*;
import com.mcyldz.exception.BaseException;
import com.mcyldz.exception.ErrorMessage;
import com.mcyldz.exception.MessageType;
import com.mcyldz.model.Car;
import com.mcyldz.model.Gallerist;
import com.mcyldz.model.GalleristCar;
import com.mcyldz.repository.CarRepository;
import com.mcyldz.repository.GalleristCarRepository;
import com.mcyldz.repository.GalleristRepository;
import com.mcyldz.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU){

        Optional<Gallerist> optionalGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        Optional<Car> optionalCar = carRepository.findById(dtoGalleristCarIU.getCarId());
        if (optionalCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getCarId().toString()));
        }
        if (optionalGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoGalleristCarIU.getGalleristId().toString()));
        }

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());
        galleristCar.setGallerist(optionalGallerist.get());
        galleristCar.setCar(optionalCar.get());

        return galleristCar;
    }

    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        GalleristCar savedGallerist = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();
        DtoAddress dtoAddress = new DtoAddress();

        BeanUtils.copyProperties(savedGallerist, dtoGalleristCar);
        BeanUtils.copyProperties(savedGallerist.getGallerist().getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedGallerist.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getCar(), dtoCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);

        return dtoGalleristCar;
    }
}
