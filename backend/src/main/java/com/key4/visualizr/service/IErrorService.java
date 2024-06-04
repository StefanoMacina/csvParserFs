package com.key4.visualizr.service;

import com.key4.visualizr.model.entity.ErrorEntity;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IErrorService {

    List<ErrorEntity> getAllErrors();

    Page<ErrorEntity> getAllPaginated(int page, int size, int direction, String... orderBy);

    Page<ErrorEntity> fullTextResearch(int page, int size, String keyword ,int direction, String... orderBy);

    Page<ErrorEntity> getErrorsInRange(LocalDate fromDate,
                                       LocalDate toDate,
                                       int directionNumber,
                                       int page, int size,
                                       String... orderBy
    );

    Page<ErrorEntity> searchTextInRange(LocalDate fromdate, LocalDate toDate, String keyword, int directionNumber, int page, int size, String... orderby);

    int save();

}
