package com.mcyldz.exception;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("1004", "KAYIT BULUNAMADI."),
    TOKEN_IS_EXPIRED("1005", "TOKEN EXPIRE OLMUŞTUR."),
    USERNAME_NOT_FOUND("1006", "USERNAME BULUNAMADI."),
    USERNAME_OR_PASSWORD_INVALID("1007","KULLANICI ADI VEYA ŞİFRE HATALI"),
    REFRESH_TOKEN_NOT_FOUND("1008", "REFRESH TOKEN BULUNAMADI."),
    REFRESH_TOKEN_IS_EXPIRED("1009", "REFRESH TOKEN EXPIRE OLMUŞ."),
    CURRENCY_RATES_IS_OCCURED("1010", "DÖVİZ KURU ALINAMADI"),
    CUSTOMER_AMOUNT_IS_NOT_ENOUGH("1011","MÜŞTERİNİN PARASI YETERSİZDİR."),
    CAR_IS_ALREADY_SALED("1012","ARABA ZATEN SATILDI."),
    GENERAL_EXCEPTION("9999", "GENEL HATA OLUŞTU.");

    private String code;

    private String message;

    MessageType(String code, String message){
        this.code = code;
        this.message = message;
    }
}
