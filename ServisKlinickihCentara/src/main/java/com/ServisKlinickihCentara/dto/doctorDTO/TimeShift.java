package com.ServisKlinickihCentara.dto.doctorDTO;

import java.sql.Time;

public class TimeShift {
    private Time startTime;
    private Time endTime;

    public TimeShift(){
        super();
    }

    public TimeShift(Time startTime, Time endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
