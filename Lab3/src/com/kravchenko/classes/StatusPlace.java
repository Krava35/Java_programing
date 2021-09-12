package com.kravchenko.classes;

public enum StatusPlace {
    STUFFY("душно"),
    NOISY("шумно"),
    STENCH("вонь"),
    NOT_SAFE("небезапасно");

    final private String status;
    StatusPlace(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

}
