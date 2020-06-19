package com.main.app.service;

import com.main.app.domain.LogDTO;
import com.main.app.domain.model.Log;
import com.main.app.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    private LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }
    @Override
    public Log add(LogDTO logDTO) {
        return logRepository.save(new Log(logDTO));
    }
    @Override
    public Page<Log> getLogs(Pageable page) {
        return logRepository.findAll(page);
    }


}
