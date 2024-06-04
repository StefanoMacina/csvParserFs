package com.key4.visualizr.controller;

import com.key4.visualizr.model.Pojo;
import com.key4.visualizr.model.entity.PartlogsEntity;
import com.key4.visualizr.service.impl.PartlogsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/v1")
public class PartlogsController {

    @Autowired
    PartlogsService ps;

    @GetMapping(path = "/sse-parts-api", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamFlux(HttpServletResponse response) {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                uploadLogs(emitter);
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }
        });
        return emitter;
    }

    private void uploadLogs(SseEmitter emitter) {
        try {
            emitter.send(SseEmitter.event().name("progress").data(-1));

            ps.save();

            emitter.send(SseEmitter.event().name("complete").data(-1));

            emitter.complete();
        } catch (IOException ex) {
            emitter.completeWithError(ex);
        }
    }

    @PostMapping("/partslogs")
    public ResponseEntity<Page<PartlogsEntity>> getAll(@RequestBody Pojo pojo) {
        try {
            Page<PartlogsEntity> getAll = ps.getAll(pojo.getPage(), pojo.getSize(), pojo.getFrom_date(),
                    pojo.getTo_date(), pojo.getRange(), pojo.getGlobalfilter(), pojo.getDir(), pojo.getOrder_by());
            return new ResponseEntity<>(getAll, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/partslogupload")
    public ResponseEntity<String> uploadLogs() {
        try {
            int recordsToAdd = ps.save();
            if (recordsToAdd > 0) return new ResponseEntity<>("upload success", HttpStatus.CREATED);
            return new ResponseEntity<>("no records to add", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("upload failed", HttpStatus.CONFLICT);
        }
    }

}
