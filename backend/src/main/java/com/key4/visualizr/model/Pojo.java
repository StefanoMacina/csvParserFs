package com.key4.visualizr.model;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor
public class Pojo {
    @Nullable
    private Integer page = 0;
    @Nullable
    private Integer size = 20;
    @Nullable
    private String order_by = "start_time";
    @Nullable
    private Integer dir = -1;
    @Nullable
    private String globalfilter;
    @Nullable
    private String range = "end_time";

    // data di x periodi fà
    @Nullable
    private LocalDate from_date = LocalDate.now()
            .minusWeeks(4);
    @Nullable
    private LocalDate to_date = LocalDate.now();

}
