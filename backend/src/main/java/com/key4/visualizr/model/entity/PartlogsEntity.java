package com.key4.visualizr.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Objects;


@Entity @NoArgsConstructor @Data
@Table(name = "partslogs")
public class PartlogsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "log_index")
    private Integer log_index;

    @Column(name = "bar_length")
    private Double bar_length;

    @Column(name = "length")
    private Double length;

    @Column(name = "rest_piece")
    private Boolean rest_piece;

    @Column(name = "job_code")
    private String job_code;

    @Column(name = "article")
    private String article;

    @Column(name = "barcode")
    @Nullable
    private String barcode;

    @Column(name = "profile_code")
    private String profile_code;

    @Column(name = "color")
    @Nullable
    private String color;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start_time")
    @Nullable
    private LocalDateTime start_time;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_time")
    @Nullable
    private LocalDateTime end_time;

    @Column(name = "total_span")
    @Nullable
    private String total_span;

    @Column(name = "total_producing_span")
    @Nullable
    private String totalProducingSpan;

    @Column(name = "overfeed")
    @Nullable
    private String overfeed;

    @Column(name = "operator")
    private String operator;

    @Column(name = "completed")
    private Boolean completed;

    @Column(name = "redone")
    @Nullable
    private Boolean redone;

    @Column(name = "Redone reason")
    @Nullable
    private String redoneReason;

    @Column(name = "Arming start time")
    @Nullable
    private String armingStartTime;

    @Column(name = "Arming end time")
    @Nullable
    private String armingEndTime;

    @Column(name = "Arming duration")
    @Nullable
    private String armingDuration;

    @Column(name = "Working start time")
    @Nullable
    private String workingStartTime;

    @Column(name = "Working end time")
    @Nullable
    private String workingEndTime;

    @Column(name = "Working duration")
    @Nullable
    private String workingDuration;


    public PartlogsEntity(int logIndex, double barLength, double length, boolean restPiece, String jobCode, String article,
                          @Nullable String barcode, String profileCode, @Nullable String colour, @Nullable LocalDateTime start_time,
                          @Nullable LocalDateTime end_time, @Nullable String totalSpan, @Nullable String totalProducingSpan,
                          @Nullable String overfeed, String operator, boolean completed, boolean redone, @Nullable String redoneReason,
                          @Nullable String armingStartTime, @Nullable String armingEndTime, @Nullable String armingDuration,
                          @Nullable String workingStartTime, @Nullable String workingEndTime, @Nullable String workingDuration) {
        this.log_index = logIndex;
        this.bar_length = barLength;
        this.length = length;
        this.rest_piece = restPiece;
        this.job_code = jobCode;
        this.article = article;
        this.barcode = barcode;
        this.profile_code = profileCode;
        this.color = colour;
        this.start_time = start_time;
        this.end_time = end_time;
        this.total_span = totalSpan;
        this.totalProducingSpan = totalProducingSpan;
        this.overfeed = overfeed;
        this.operator = operator;
        this.completed = completed;
        this.redone = redone;
        this.redoneReason = redoneReason;
        this.armingStartTime = armingStartTime;
        this.armingEndTime = armingEndTime;
        this.armingDuration = armingDuration;
        this.workingStartTime = workingStartTime;
        this.workingEndTime = workingEndTime;
        this.workingDuration = workingDuration;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartlogsEntity that = (PartlogsEntity) o;
        return Objects.equals(log_index, that.log_index) &&
                Objects.equals(start_time, that.start_time) &&
                Objects.equals(end_time, that.end_time) &&
                Objects.equals(article, that.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(log_index, start_time, end_time, article);
    }
}


