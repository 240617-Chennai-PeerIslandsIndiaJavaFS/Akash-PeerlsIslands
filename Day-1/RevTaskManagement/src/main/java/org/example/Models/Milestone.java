package org.example.Models;

import java.util.Date;

public class Milestone {
    private int milestoneId;
    private String milestoneName;
    private Project projectid;
    private Date dueDate;


    public Milestone() {
    }

    public Milestone(int milestoneId, String milestoneName, Project projectid, Date dueDate) {
        this.milestoneId = milestoneId;
        this.milestoneName = milestoneName;
        this.projectid = projectid;
        this.dueDate = dueDate;
    }

    public int getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(int milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public Project getProjectid() {
        return projectid;
    }

    public void setProjectid(Project projectid) {
        this.projectid = projectid;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
