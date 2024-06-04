package com.key4.visualizr.model.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ErrorDto {

    private int id;
    private int code;
    private String description;
    private LocalTime duration;
    private int occurences;
    private String state;
    private LocalDateTime date;

}
