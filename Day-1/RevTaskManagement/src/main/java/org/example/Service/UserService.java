package org.example.Service;

import org.example.DAO.UserDAO;
import org.example.DAO.UserDAOImpl;
import org.example.Models.Project;
import org.example.Models.Task;
import org.example.Models.User;
import org.example.Models.UserActivity;

import java.util.List;

public class UserService {

    private static UserDAO userDAO;

    public UserService() {
        this.userDAO = new UserDAOImpl(); // Initialize UserDAO implementation
    }

    public boolean registerUser(User user) {
        return userDAO.register(user);
    }

    public User loginUser(String username, String password) {
        return userDAO.login(username, password);
    }

    public boolean createUser(User user) {
        return userDAO.createUser(user);
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }

    public boolean deactivateUser(int userId) {
        return userDAO.deactivateUser(userId);
    }

    public List<UserActivity> getgetUserByUsername(String username) {
        return userDAO.getUserActivitiesByUsername(username);
    }

    public User getUserByUsername(String username){
        return userDAO.getUserByUsername(username);

    }
    public static List<UserActivity> getgetUserTasksByUsername(String username) {
        return userDAO.getUserTaskByUsername(username);
    }
    public List<Task> getTasksAssignedToUser(String username) {
        return userDAO.getTasksAssignedToUser(username);
    }
    public List<Project> getProjectsByUsername(String username) {
        return userDAO.getProjectsByUsername(username);
    }
    public List<Project> getProjectsByPMId(int pmId) {

        return userDAO.getProjectsByPMId(pmId);
    }

    public boolean getassignTaskToUser(int projectId, int assignedToUserId, String taskName) {
        return userDAO.assignTaskToUser(projectId,assignedToUserId,taskName);
    }





}
