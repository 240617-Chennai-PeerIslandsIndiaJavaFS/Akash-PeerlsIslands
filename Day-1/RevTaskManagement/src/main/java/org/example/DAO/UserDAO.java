package org.example.DAO;

import org.example.Models.Project;
import org.example.Models.Task;
import org.example.Models.User;
import org.example.Models.UserActivity;

import java.util.List;

public interface UserDAO {

    boolean register(User user);

    User login(String username, String password);

    boolean createUser(User user);

    boolean updateUser(User user);

    boolean deactivateUser(int userId);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    List<User> getAllUsersByRole(String role);

    boolean assignTeamMemberToProject(int projectId, int userId);


    List<UserActivity> getUserActivitiesByUsername(String username);
    List<UserActivity> getUserTaskByUsername(String username);
    List<Task> getTasksAssignedToUser(String username);

    List<Project> getProjectsByUsername(String username);
    List<Project> getProjectsByPMId(int pmId);


    boolean assignTaskToUser(int projectId, int assignedToUserId, String taskName);
}
