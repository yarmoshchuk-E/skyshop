package org.skypro.skyshop.exception;

public record ShopError(String code, String message) {

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
