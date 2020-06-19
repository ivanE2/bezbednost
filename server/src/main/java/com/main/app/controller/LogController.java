package com.main.app.controller;

import com.main.app.domain.LogDTO;
import com.main.app.domain.Entities;
import com.main.app.domain.model.Log;
import com.main.app.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping(consumes ="application/json",produces = "application/json")
    public ResponseEntity<LogDTO> add(@RequestBody LogDTO logDTO) {

        Log savedLog = logService.add(logDTO);
        return new ResponseEntity<>(new LogDTO(savedLog), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Entities> getAlarms(Pageable page) {
        Entities result = new Entities();
        Page<Log> logs = logService.getLogs(page);

        result.setTotal(logs.getTotalElements());
        result.setEntities(logs.getContent());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
