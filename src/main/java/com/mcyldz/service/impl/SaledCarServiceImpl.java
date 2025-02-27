package com.mcyldz.service.impl;

import com.mcyldz.dto.*;
import com.mcyldz.enums.CarStatusType;
import com.mcyldz.exception.BaseException;
import com.mcyldz.exception.ErrorMessage;
import com.mcyldz.exception.MessageType;
import com.mcyldz.model.Car;
import com.mcyldz.model.Customer;
import com.mcyldz.model.Gallerist;
import com.mcyldz.model.SaledCar;
import com.mcyldz.repository.CarRepository;
import com.mcyldz.repository.CustomerRepository;
import com.mcyldz.repository.GalleristRepository;
import com.mcyldz.repository.SaledCarRepository;
import com.mcyldz.service.ICurrencyRatesService;
import com.mcyldz.service.ISaledCarService;
import com.mcyldz.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Optional;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    @Autowired
    private SaledCarRepository saledCarRepository;

    public BigDecimal convertCustomerAmountToUSD(Customer customer){

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));

        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        return customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
    }

    private boolean checkAmount(DtoSaledCarIU dtoSaledCarIU){

        Optional<Customer> optionalCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optionalCustomer.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCustomerId().toString()));
        }

        Optional<Gallerist> optionalGallerist = galleristRepository.findById(dtoSaledCarIU.getGalleristId());
        if (optionalGallerist.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getGalleristId().toString()));
        }

        Optional<Car> optionalCar = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optionalCar.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, dtoSaledCarIU.getCarId().toString()));
        }

        BigDecimal customerAmountUSD = convertCustomerAmountToUSD(optionalCustomer.get());

        if (customerAmountUSD.compareTo(optionalCar.get().getPrice()) == 0 || customerAmountUSD.compareTo(optionalCar.get().getPrice()) > 0){
            return true;
        }
        return false;
    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU){
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());

        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

        return saledCar;
    }

    private boolean checkCarStatus(Long carId){
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent() && optionalCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())){
            return false;
        }
        return true;
    }

    private BigDecimal remaningCustomerAmount(Customer customer, Car car){
        BigDecimal customerAmountUSD = convertCustomerAmountToUSD(customer);
        BigDecimal remaningCustomerAmountUSD = customerAmountUSD.subtract(car.getPrice());

        CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(DateUtils.getCurrentDate(new Date()), DateUtils.getCurrentDate(new Date()));
        BigDecimal usd = new BigDecimal(currencyRatesResponse.getItems().get(0).getUsd());

        return remaningCustomerAmountUSD.multiply(usd);
    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {

        if (!checkCarStatus(dtoSaledCarIU.getCarId())){
            throw new BaseException(new ErrorMessage(MessageType.CAR_IS_ALREADY_SALED, dtoSaledCarIU.getCarId().toString()));
        }

        if (!checkAmount(dtoSaledCarIU)){
            throw new BaseException(new ErrorMessage(MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH, ""));
        }

        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));
        
        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);

        carRepository.save(car);

        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
        customerRepository.save(customer);

        return toDto(savedSaledCar);
    }

    private DtoSaledCar toDto(SaledCar saledCar){
        DtoSaledCar dtoSaledCar = new DtoSaledCar();
        DtoCar dtoCar = new DtoCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCustomer dtoCustomer = new DtoCustomer();

        BeanUtils.copyProperties(saledCar, dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(saledCar.getCar(), dtoCar);

        dtoSaledCar.setCar(dtoCar);
        dtoSaledCar.setCustomer(dtoCustomer);
        dtoSaledCar.setGallerist(dtoGallerist);

        return dtoSaledCar;
    }
}
