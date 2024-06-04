package com.key4.visualizr.helper;

import com.key4.visualizr.exceptions.FileNotFoundEx;
import com.key4.visualizr.model.entity.ErrorEntity;
import com.key4.visualizr.model.entity.PartlogsEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


public class CSVHelper {

    public static final String LOGS_FILE_PATH = "../csvfolder/i4Parts_log.csv";
    public static final String ERROR_FILE_PATH = "../csvfolder/i4Error_log.csv";
    public static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy  HH:mm:ss");

    public static List<PartlogsEntity> csvToPartlog() {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(LOGS_FILE_PATH));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withDelimiter(';')
                        .withIgnoreEmptyLines()
                        .withAllowMissingColumnNames()
                        .withTrim());
        ) {
            List<PartlogsEntity> logsEntities = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                int log_index = Integer.parseInt(csvRecord.get("Index"));
                double bar_length = Double.parseDouble(csvRecord.get("Bar length"));
                double length = Double.parseDouble(csvRecord.get("Length"));
                boolean restPiece = Boolean.parseBoolean(csvRecord.get("Is a rest piece"));
                String jobCode = csvRecord.get("Job Code");
                String article = csvRecord.get("Article");
                String barcode = csvRecord.get("Barcode");
                String profileCode = csvRecord.get("Profile code");
                String colour = csvRecord.get("Colour");
                LocalDateTime startTime = csvRecord.get("Start time").isBlank() ? null : LocalDateTime.parse(csvRecord.get("Start time"), DATE_TIME_FORMATTER);
                LocalDateTime endTime = csvRecord.get("End time").isBlank() ? null : LocalDateTime.parse(csvRecord.get("End time"), DATE_TIME_FORMATTER);
                String totalSpan = csvRecord.get("Total span");
                String totalProducingSpan = csvRecord.get("Total producing span");
                String overfeed = csvRecord.get("Overfeed");
                String operator = csvRecord.get("Operator");
                boolean completed = Boolean.parseBoolean(csvRecord.get("Completed"));
                boolean redone = Boolean.parseBoolean(csvRecord.get("Redone"));
                String redoneReason = csvRecord.get("Redone reason");
                String arming_start = csvRecord.get("Arming start time");
                String arming_end = csvRecord.get("Arming end time");
                String arming_duration = csvRecord.get("Arming duration");
                String working_start = csvRecord.get("Working start time");
                String working_end = csvRecord.get("Working end time");
                String working_duration = csvRecord.get("Working duration");

                PartlogsEntity logEntity = new PartlogsEntity(
                        log_index,
                        bar_length,
                        length,
                        restPiece,
                        jobCode,
                        article,
                        barcode,
                        profileCode,
                        colour,
                        startTime,
                        endTime,
                        totalSpan.isBlank() ? "" : totalSpan,
                        totalProducingSpan.isBlank() ? "" : totalProducingSpan,
                        overfeed.isBlank() ? "" : overfeed,
                        operator,
                        completed,
                        redone,
                        redoneReason,
                        arming_start.isBlank() ? "" : arming_start,
                        arming_end.isBlank() ? "" : arming_end,
                        arming_duration.isBlank() ? "" : arming_duration,
                        working_start.isBlank() ? "" : working_start,
                        working_end.isBlank() ? "" : working_end,
                        working_duration.isBlank() ? "" : working_duration
                );

                logsEntities.add(logEntity);
            }
            reader.close();
            return logsEntities;
        } catch (FileNotFoundEx e) {
            throw new FileNotFoundEx("-------------- FILE ERROR LOG NOT FOUND IN %s -----------%n".formatted(ERROR_FILE_PATH), e);
        } catch (IOException e){
            throw new RuntimeException("-------------- ERROR PARSING PARTSLOG CSV FILE --------------",e);
        }

    }

    public static List<ErrorEntity> csvToErrorlog() {

        try {
            CSVFormat csvFormat = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withAllowMissingColumnNames()
                    .withDelimiter(';');

            CSVParser parser = new CSVParser(new FileReader(ERROR_FILE_PATH), csvFormat);

            List<CSVRecord> records = parser.getRecords();

            List<ErrorEntity> errorEntities = new ArrayList<>();

            for (int i = 1; i < records.size(); i++) {
                CSVRecord record = records.get(i);
                CSVRecord prevRecord = records.get(i - 1);

                if (isNumeric(prevRecord.get(0))) {
                    if (prevRecord.get(3).isEmpty()) {
                        errorEntities.add(
                                new ErrorEntity(
                                        Integer.parseInt(prevRecord.get(0)),
                                        String.format("%s %s", prevRecord.get(1), record.get(0)),
                                        record.get(1),
                                        Integer.parseInt(record.get(2)),
                                        record.get(3),
                                        LocalDateTime.parse(record.get(4), DATE_TIME_FORMATTER)
                                )
                        );
                    } else {
                        errorEntities.add(
                                new ErrorEntity(
                                        Integer.parseInt(prevRecord.get(0)),
                                        prevRecord.get(1),
                                        prevRecord.get(2),
                                        Integer.parseInt(prevRecord.get(3)),
                                        prevRecord.get(4),
                                        LocalDateTime.parse(prevRecord.get(5), DATE_TIME_FORMATTER)
                                )
                        );
                    }
                }
            }

            // iterare sull'ultimo elemento se non è stato iterato
            if (isNumeric(records.get(records.size() - 1).get(0))) {
                CSVRecord lastRecord = records.get(records.size() - 1);
                if (!lastRecord.get(3).isEmpty()) {
                    errorEntities.add(
                            new ErrorEntity(
                                    Integer.parseInt(lastRecord.get(0)),
                                    lastRecord.get(1),
                                    lastRecord.get(2),
                                    Integer.parseInt(lastRecord.get(3)),
                                    lastRecord.get(4),
                                    LocalDateTime.parse(lastRecord.get(5), DATE_TIME_FORMATTER)
                            )
                    );
                }
            }

            return errorEntities;

        } catch (FileNotFoundException e) {
            throw new FileNotFoundEx("-------------- FILE ERROR LOG NOT FOUND IN %s -----------%n".formatted(ERROR_FILE_PATH), e );
        } catch (IOException e){
            throw new RuntimeException(" ----------------- ERROR WHILE PARSING ERROR LOGS FILE --------------", e);
        }
    }

    public static boolean isNumeric(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
