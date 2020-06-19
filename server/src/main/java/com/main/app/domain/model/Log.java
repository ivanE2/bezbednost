package com.main.app.domain.model;

import com.main.app.domain.LogDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Log extends AbstractEntity {

    private String date;
    private String alarm;
    private String agent;

    public Log(String date, String alarm, String agent) {
        this.alarm = alarm;
        this.agent = agent;
        this.date = date;
    }

    public Log(LogDTO alarm) {
        this.date = alarm.getDate();
        this.alarm = alarm.getAlarm();
        this.agent = alarm.getAgent();

        this.id = alarm.getId();

    }
}
