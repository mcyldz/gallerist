package com.mcyldz.dto;

import com.mcyldz.enums.CarStatusType;
import com.mcyldz.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DtoCarIU {

    @NotNull
    private String plaka;

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private Integer productionYear;

    @NotNull
    private BigDecimal price;

    @NotNull
    private BigDecimal damagePrice;

    @NotNull
    private CurrencyType currencyType;

    @NotNull
    private CarStatusType carStatusType;
}
