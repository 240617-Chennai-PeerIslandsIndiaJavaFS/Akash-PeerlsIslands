package org.example.Models;

public class Project {
    private int projectId;
    private String projectName;
    private Client clientid;
    private User pmid;
    private String projectDetails;
    private int startDate;
    private int endDate;

    private Task task;


    public Project() {
    }

    public Project(int projectId, String projectName, Client clientid, User pmid, String projectDetails, int startDate, int endDate,Task task) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.clientid = clientid;
        this.pmid = pmid;
        this.projectDetails = projectDetails;
        this.startDate = startDate;
        this.endDate = endDate;
        this.task = task;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Client getClientid() {
        return clientid;
    }

    public void setClientid(Client clientid) {
        this.clientid = clientid;
    }

    public User getPmid() {
        return pmid;
    }

    public void setPmid(User pmid) {
        this.pmid = pmid;
    }

    public String getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(String projectDetails) {
        this.projectDetails = projectDetails;
    }

    public int getStartDate() {
        return startDate;
    }

    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}




