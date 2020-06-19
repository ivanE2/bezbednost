package com.main.app.service;

import com.main.app.domain.LogDTO;
import com.main.app.domain.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface LogService {
    Log add(LogDTO logDTO);
    Page<Log> getLogs(Pageable page);

}
