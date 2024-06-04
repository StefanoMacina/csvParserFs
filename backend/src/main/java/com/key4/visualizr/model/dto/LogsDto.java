package com.key4.visualizr.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogsDto {

    private int id;
    private int index;
    private double bar_length;
    private double length;
    private boolean is_a_rest_piece;
    private String job_Code;
    private String article;
    private String barcode;
    private String profile_code;
    private String colour;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private double total_span;
    private double total_producing_span;
    private double overfeed;
    private String operator;
    private boolean completed;
    private boolean redone;
    private String redone_reason;
}
