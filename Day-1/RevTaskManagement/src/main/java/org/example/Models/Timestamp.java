package org.example.Models;

import java.time.LocalDateTime;

public class Timestamp {
    private int TimestampID;
    private int TaskID;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;

    public Timestamp(int TimestampID, int TaskID, LocalDateTime StartTime, LocalDateTime EndTime) {
        this.TimestampID = TimestampID;
        this.TaskID = TaskID;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
    }

    public int getTimestampID() {
        return TimestampID;
    }

    public void setTimestampID(int timestampID) {
        this.TimestampID = timestampID;
    }

    public int getTaskID() {
        return TaskID;
    }

    public void setTaskID(int taskID) {
        this.TaskID = taskID;
    }

    public LocalDateTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.StartTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return EndTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.EndTime = endTime;
    }
}
