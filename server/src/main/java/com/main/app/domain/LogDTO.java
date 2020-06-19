package com.main.app.domain;

import com.main.app.domain.model.Log;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LogDTO {

    private Long id;
    private String alarm;
    private String agent;
    private String date;


    public LogDTO(Log log) {
        this.id = log.getId();
        this.agent = log.getAgent();
        this.date = log.getDate();
        this.alarm = log.getAlarm();
    }
}
