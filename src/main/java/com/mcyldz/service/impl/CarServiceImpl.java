package com.mcyldz.service.impl;

import com.mcyldz.dto.DtoCar;
import com.mcyldz.dto.DtoCarIU;
import com.mcyldz.model.Car;
import com.mcyldz.repository.CarRepository;
import com.mcyldz.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    private Car createCar(DtoCarIU dtoCarIU){
        Car car = new Car();
        car.setCreateTime(new Date());
        BeanUtils.copyProperties(dtoCarIU, car);
        return car;
    }

    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {
        Car savedCar = carRepository.save(createCar(dtoCarIU));
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(savedCar, dtoCar);
        return dtoCar;
    }
}
