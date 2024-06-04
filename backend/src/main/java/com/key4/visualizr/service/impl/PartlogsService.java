package com.key4.visualizr.service.impl;

import com.key4.visualizr.exceptions.FileNotFoundEx;
import com.key4.visualizr.helper.CSVHelper;
import com.key4.visualizr.model.entity.PartlogsEntity;
import com.key4.visualizr.repository.PartogsRepository;
import com.key4.visualizr.service.IPartlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PartlogsService implements IPartlogsService, CommandLineRunner {

    @Autowired
    PartogsRepository pl;

    @Override
    public int save(){
        try {
            // Legge CSV (pre-lavorati)
            List<PartlogsEntity> logsEntities = CSVHelper.csvToPartlog();

            // Fetch dal DB (post-lavorati)
            List<PartlogsEntity> dbEntities = pl.findAll();

            // Creare set di chiavi uniche (log_index + start_time) per gli elementi già presenti nel DB
            Set<String> existingKeys = dbEntities.stream()
                    .map(e -> e.getLog_index() + "-" + e.getStart_time())
                    .collect(Collectors.toSet());

            // Raggruppa e aggiorna gli elementi del CSV
            Map<String, PartlogsEntity> groupedEntities = logsEntities.stream()
                    .collect(Collectors.toMap(
                            e -> e.getLog_index() + "-" + e.getStart_time(),
                            e -> e,
                            (e1, e2) -> {
                                if (e1.getEnd_time() == null && e2.getEnd_time() != null) {
                                    e1.setEnd_time(e2.getEnd_time());
                                    e1.setCompleted(e2.getCompleted());
                                }
                                return e1;
                            },
                            LinkedHashMap::new
                    ));

            // Filtrare gli elementi che non sono già presenti nel DB
            List<PartlogsEntity> uniqueEntities = groupedEntities.values().stream()
                    .filter(e -> !existingKeys.contains(e.getLog_index() + "-" + e.getStart_time()))
                    .collect(Collectors.toList());

            pl.saveAll(uniqueEntities);

            return uniqueEntities.size();

        } catch (FileNotFoundEx e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return 0;
        }

    }


    @Override
    public Page<PartlogsEntity> getAll(int page, int size, LocalDate fromDate,
                                           LocalDate toDate, String range,
                                           String keyword, int direction, String... orderby) {

                PageRequest pr = PageRequest.of(page,size,Sort.Direction.fromString(
                        direction != -1 ? "asc" : "desc"
                ),orderby);

        if (keyword == null || keyword.isBlank()) {
            if ("start_time".equals(range)) {
                return pl.findByStartTimeBetween(fromDate, toDate, pr);
            } else {
                return pl.findByEndTimeBetween(fromDate, toDate, pr);
            }
        }
        if ("start_time".equals(range)) {
            return pl.searchInRangeByStartTime(fromDate, toDate, keyword, pr);
        } else {
            return pl.searchInRangeByEndTime(fromDate, toDate, keyword, pr);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        save();
    }

}
