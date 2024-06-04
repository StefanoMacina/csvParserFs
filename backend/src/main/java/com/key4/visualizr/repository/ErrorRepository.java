package com.key4.visualizr.repository;

import com.key4.visualizr.model.entity.ErrorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ErrorRepository extends JpaRepository<ErrorEntity, Integer> {

    @Query(value = "SELECT * FROM errorlogs e WHERE " +
            "e.description LIKE %:keyword% " +
            "OR e.code LIKE %:keyword% " +
            "OR e.duration LIKE %:keyword% " +
            "OR e.state LIKE %:keyword% " +
            "OR e.occurences LIKE %:keyword%",
            nativeQuery = true
    )
    Page<ErrorEntity> search(@Param("keyword") String keyword, Pageable pageable);

    @Query(value = "SELECT * FROM errorlogs e WHERE " +
            "(e.description LIKE %:keyword% " +
            "OR e.code LIKE %:keyword% " +
            "OR e.duration LIKE %:keyword% " +
            "OR e.state LIKE %:keyword% " +
            "OR e.occurences LIKE %:keyword%) " +
            "AND e.date >= :fromDate AND e.date < :toDate + INTERVAL 1 DAY",
            nativeQuery = true
    )
    Page<ErrorEntity> searchTextInRange(
            @Param("keyword") String keyword, Pageable pageable,
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate
    );

    @Query(value = "SELECT * FROM errorlogs e WHERE " +
            "e.date >= :fromDate and e.date < :toDate + INTERVAL 1 DAY ", nativeQuery = true)
    Page<ErrorEntity> getErrorsByRange(
            @Param("fromDate")LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
                                Pageable pageable);
}

