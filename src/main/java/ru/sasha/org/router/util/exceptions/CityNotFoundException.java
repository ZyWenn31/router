package ru.sasha.org.router.util.exceptions;

public class CityNotFoundException extends RuntimeException{
    public CityNotFoundException(String msg){
        super(msg);
    }
}
