package org.example.Models;
import java.sql.Timestamp;

public class UserActivity {
    private int activityId;
    private User user;
    private Task task;
    private Milestone milestone;
    private String activityType;
    private Timestamp activityTimestamp;


    public UserActivity() {
    }

    public UserActivity(int activityId, User user, Task task, Milestone milestone, String activityType, Timestamp activityTimestamp) {
        this.activityId = activityId;
        this.user = user;
        this.task = task;
        this.milestone = milestone;
        this.activityType = activityType;
        this.activityTimestamp = activityTimestamp;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Timestamp getActivityTimestamp() {
        return activityTimestamp;
    }

    public void setActivityTimestamp(Timestamp activityTimestamp) {
        this.activityTimestamp = activityTimestamp;
    }



}
