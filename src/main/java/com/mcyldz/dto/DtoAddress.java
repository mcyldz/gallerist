package com.mcyldz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoAddress extends DtoBase{

    private String city;

    private String district;

    private String neigborhood;

    private String street;
}
