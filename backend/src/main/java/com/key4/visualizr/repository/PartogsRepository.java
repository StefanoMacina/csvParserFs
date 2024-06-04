package com.key4.visualizr.repository;

import com.key4.visualizr.model.entity.PartlogsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;


@Repository
public interface PartogsRepository extends JpaRepository<PartlogsEntity, Integer> {

    /*
    *    /**
     * @Query(value ="SELECT e.start_time, MAX(e.end_time) as end_time, " +
     *             "e.job_code, e.article, e.barcode, e.profile_code, e.color, " +
     *             "e.bar_length, e.length, e.rest_piece, " +
     *             "e.total_span, e.total_producing_span, e.overfeed, e.operator, e.completed, e.redone " +
     *             "FROM partslogs e " +
     *             "WHERE e.start_time >= :fromDate AND e.start_time < :toDate + INTERVAL 1 DAY " +
     *             "GROUP BY e.start_time, e.job_code, e.article, e.barcode, e.profile_code, e.color, " +
     *             "e.bar_length, e.length, e.rest_piece, " +
     *             "e.total_span, e.total_producing_span, e.overfeed, e.operator, e.completed, e.redone",
     *             nativeQuery = true)
     */

    @Query(value = "SELECT * FROM partslogs e WHERE " +
            "e.start_time >= :fromDate AND e.start_time < :toDate + INTERVAL 1 DAY",
            nativeQuery = true)
    Page<PartlogsEntity> findByStartTimeBetween(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            Pageable pageable
    );

    @Query(value = "SELECT * FROM partslogs e WHERE " +
            "e.end_time >= :fromDate AND e.end_time < :toDate + INTERVAL 1 DAY",
            nativeQuery = true)
    Page<PartlogsEntity> findByEndTimeBetween(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            Pageable pageable
    );

    @Query(value = "SELECT * FROM partslogs e WHERE " +
           "(e.job_code LIKE %:keyword% " +
            "OR e.log_index LIKE %:keyword% " +
            "OR e.bar_length LIKE %:keyword% " +
            "OR e.rest_piece LIKE %:keyword% " +
            "OR e.barcode LIKE %:keyword% " +
            "OR e.article LIKE %:keyword% " +
            "OR e.profile_code LIKE %:keyword% " +
            "OR e.start_time LIKE %:keyword% " +
            "OR e.end_time LIKE %:keyword% " +
            "OR e.color LIKE %:keyword%) " +
            "AND e.start_time >= :fromDate AND e.start_time < :toDate + INTERVAL 1 DAY",
            nativeQuery = true)
    Page<PartlogsEntity> searchInRangeByStartTime(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("keyword") String keyword,
            Pageable pageable
    );


    @Query(value = "SELECT * FROM partslogs e WHERE " +
            "(e.job_code LIKE %:keyword% " +
            "OR e.log_index LIKE %:keyword% " +
            "OR e.bar_length LIKE %:keyword% " +
            "OR e.rest_piece LIKE %:keyword% " +
            "OR e.barcode LIKE %:keyword% " +
            "OR e.article LIKE %:keyword% " +
            "OR e.profile_code LIKE %:keyword% " +
            "OR e.start_time LIKE %:keyword% " +
            "OR e.end_time LIKE %:keyword% " +
            "OR e.color LIKE %:keyword%) " +
            "AND e.end_time >= :fromDate AND e.end_time < :toDate + INTERVAL 1 DAY",
            nativeQuery = true)
    Page<PartlogsEntity> searchInRangeByEndTime(
            @Param("fromDate") LocalDate fromDate,
            @Param("toDate") LocalDate toDate,
            @Param("keyword") String keyword,
            Pageable pageable
    );



//    @Query(value = "SELECT * FROM partslogs e WHERE " +
//            "e.job_code LIKE %:keyword% " +
//            "OR e.article LIKE %:keyword% " +
//            "OR e.profile_code LIKE %:keyword% " +
//            "OR e.color LIKE %:keyword% ",
//            nativeQuery = true
//    )
//    Page<PartlogsEntity> search(@Param("keyword") String keyword, Pageable pageable);
//
//    @Query(value = "SELECT * FROM partslogs e WHERE " +
//            "(e.job_code LIKE %:keyword% " +
//            "OR e.article LIKE %:keyword% " +
//            "OR e.profile_code LIKE %:keyword% " +
//            "OR e.color LIKE %:keyword%) " +
//            "AND e.start_time >= :fromDate AND e.start_time < :toDate + INTERVAL 1 DAY",
//            nativeQuery = true
//    )
//    Page<PartlogsEntity> searchInRange(
//            @Param("keyword") String keyword, Pageable pageable,
//            @Param("fromDate") LocalDate fromDate,
//            @Param("toDate") LocalDate toDate
//
//    );
//
//
//    @Query(value = "SELECT * FROM partslogs e WHERE " +
//            "(e.job_code LIKE %:keyword% " +
//            "OR e.article LIKE %:keyword% " +
//            "OR e.profile_code LIKE %:keyword% " +
//            "OR e.color LIKE %:keyword%) AND "+
//            "e.start_time >= :fromDate AND e.start_time < :toDate + INTERVAL 1 DAY",
//            nativeQuery = true)
//    Page<PartlogsEntity> getPartLogsBetweenSTime(
//            @Param("fromDate")LocalDate fromDate,
//            @Param("toDate") LocalDate toDate,
//            @Param("keyword") String keyword,
//            Pageable pageable
//            );
//
//
//    @Query(value = "SELECT * FROM partslogs e WHERE " +
//                    "(e.job_code LIKE %:keyword% " +
//                    "OR e.article LIKE %:keyword% " +
//                    "OR e.profile_code LIKE %:keyword% " +
//                    "OR e.color LIKE %:keyword%) AND " +
//                    "e.end_time >= :fromDate AND e.end_time < :toDate + INTERVAL 1 DAY",
//                    nativeQuery = true)
//    Page<PartlogsEntity> getPartLogsBetweenETime(
//            @Param("fromDate") LocalDate fromDate,
//            @Param("toDate") LocalDate toDate,
//            @Param("keyword") String keyword,
//            Pageable pageable
//    );

//    Optional<PartlogsEntity> getOneBylogIndexStartTime(
//            @Param("log index") Integer logIndex,
//            @Param("start_time") LocalDateTime startTime
//            );


}
