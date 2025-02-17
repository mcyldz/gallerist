package com.mcyldz.dto;

import com.mcyldz.enums.CarStatusType;
import com.mcyldz.enums.CurrencyType;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoCar extends DtoBase{

    private String plaka;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;

    private BigDecimal damagePrice;

    private CurrencyType currencyType;

    private CarStatusType carStatusType;
}
