package org.example.Models;

public class Task {
    private int taskId;
    private String taskName;
    private Project projectid;
    private User assignedto;
    private String status;

    // Constructors
    public Task() {
    }

    public Task(int taskId, String taskName, Project projectid, User assignedto, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.projectid = projectid;
        this.assignedto = assignedto;
        this.status = status;
    }

    public Task(int taskId, String task1, int i, int i1, String pending) {


    }


    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Project getProjectid() {
        return projectid;
    }

    public void setProjectid(Project projectid) {
        this.projectid = projectid;
    }

    public User getAssignedto() {
        return assignedto;
    }

    public void setAssignedto(User assignedto) {
        this.assignedto = assignedto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
