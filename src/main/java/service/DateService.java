package service;

import java.time.LocalDateTime;

public class DateService {

    public static LocalDateTime getNow(){
        return  LocalDateTime.of(2022,01,01,00,00,00);
    }
}
