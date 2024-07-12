import org.example.DAO.UserDAO;
import org.example.DAO.UserDAOImpl;
import org.example.Models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import java.util.List;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOImplTest {

    private static UserDAOImpl userDAO;

    @BeforeAll
    public static void setUp() {
        userDAO = new UserDAOImpl();
    }

    @Test
    public void testRegisterSuccess() {
        User user = new User(0, "testuser", "testpassword", "testuser@example.com", "Team member");

        boolean result = userDAO.register(user);

        assertTrue(result);
    }


    @Test
    public void testLoginSuccess() {

        String username = "testuser";
        String password = "testpassword";


        User result = userDAO.login(username, password);


        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals(password, result.getPassword());
        assertNotNull(result.getEmail());
        assertNotNull(result.getRole());
    }

    @Test
    public void testLoginFailure() {

        String username = "nonexistentuser";
        String password = "incorrectpassword";


        User result = userDAO.login(username, password);


        assertNull(result);
    }

    @Test
    public void testCreateUserSuccess() {

        User user = new User(0, "newuser", "newpassword", "newuser@example.com", "Team member");


        boolean result = userDAO.createUser(user);


        assertTrue(result);

    }



    @Test
    public void testUpdateUserSuccess() {
        // Arrange
        User user = new User(28, "testuser", "testuser", "testuser@gmail.com", "team member");

        // Act
        boolean result = userDAO.updateUser(user);

        // Assert
        assertTrue(result);
        // You can add more assertions if needed, like verifying if the user is actually updated in the database
    }

    @Test
    public void testUpdateUserFailure() {
        // Arrange
        // Simulate a case where updating the user fails (e.g., user not found)
        User nonExistingUser = new User(9999, "nonexistinguser", "newpassword", "nonexisting@example.com", "Team member");

        // Act
        boolean result = userDAO.updateUser(nonExistingUser);

        // Assert
        assertFalse(result);
    }

   // @Test
//    public void testDeactivateUserSuccess() {
//        // Arrange
//        int userId = 11; // Assuming this user ID exists in your database
//
//        // Act
//        boolean result = userDAO.deactivateUser(userId);
//
//        // Assert
//        assertTrue(result);
//        // You can add more assertions if needed, like verifying if the user is actually deactivated in the database
//    }

    @Test
    public void testDeactivateUserFailure() {
        // Arrange
        int nonExistingUserId = 9999; // Assuming this user ID does not exist in your database

        // Act
        boolean result = userDAO.deactivateUser(nonExistingUserId);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testGetUserByUsernameFound() {
        // Arrange
        String username = "existinguser"; // Assuming this username exists in your database

        // Act
        User result = userDAO.getUserByUsername(username);

        // Assert
        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertNotNull(result.getPassword());
        assertNotNull(result.getEmail());
        assertNotNull(result.getRole());
    }

    @Test
    public void testGetUserByUsernameNotFound() {
        // Arrange
        String nonExistingUsername = "nonexistentuser"; // Assuming this username does not exist in your database

        // Act
        User result = userDAO.getUserByUsername(nonExistingUsername);

        // Assert
        assertNull(result);
    }

    @Test
    public void testGetAllUsersNotEmpty() {
        // Act
        List<User> userList = userDAO.getAllUsers();

        // Assert
        assertNotNull(userList);
        assertFalse(userList.isEmpty());
        // You can add more assertions to verify the size of the list, or specific users retrieved
    }

//        @Test
//    public void testGetAllUsersEmpty() {
//        // Arrange: You might want to clear all users in the database first for this test, or ensure there are no users
//        // Act
//        List<User> userList = userDAO.getAllUsers();
//
//        // Assert
//        assertNotNull(userList);
//        assertTrue(userList.isEmpty());
//    }
    @Test
    public void testGetAllUsersByRoleNotEmpty() {
        // Arrange
        String role = "Team member"; // Assuming this role exists in your database

        // Act
        List<User> userList = userDAO.getAllUsersByRole(role);

        // Assert
        assertNotNull(userList);
        assertFalse(userList.isEmpty());
        for (User user : userList) {
            assertEquals(role, user.getRole());
        }
    }

    @Test
    public void testGetAllUsersByRoleEmpty() {
        // Arrange
        String nonExistingRole = "Non-existent role"; // Assuming this role does not exist in your database

        // Act
        List<User> userList = userDAO.getAllUsersByRole(nonExistingRole);

        // Assert
        assertNotNull(userList);
        assertTrue(userList.isEmpty());
    }
//    @Test
//    public void testGetUserActivitiesByUsernameNotEmpty() {
//        // Arrange
//        String username = "testuser"; // Assuming this username exists in your database with activities
//
//        // Act
//        List<UserActivity> activities = userDAO.getUserActivitiesByUsername(username);
//
//        // Assert
//        assertNotNull(activities);
//        assertFalse(activities.isEmpty());
//        for (UserActivity activity : activities) {
//            assertNotNull(activity.getActivityId());
//            assertNotNull(activity.getActivityType());
//            assertNotNull(activity.getActivityTimestamp());
//            assertNotNull(activity.getUser());
//            assertEquals(username, activity.getUser().getUsername());
//            assertNotNull(activity.getTask()); // Assuming tasks can be null based on the left join
//            assertNotNull(activity.getMilestone()); // Assuming milestones can be null based on the left join
//        }
//    }

    @Test
    public void testGetUserActivitiesByUsernameEmpty() {
        // Arrange
        String nonExistingUsername = "nonexistentuser"; // Assuming this username does not exist in your database

        // Act
        List<UserActivity> activities = userDAO.getUserActivitiesByUsername(nonExistingUsername);

        // Assert
        assertNotNull(activities);
        assertTrue(activities.isEmpty());
    }
//    @Test
//    public void testGetUserTaskByUsernameNotEmpty() {
//        // Arrange
//        String username = "testuser"; // Assuming this username exists in your database with activities
//
//        // Act
//        List<UserActivity> activities = userDAO.getUserTaskByUsername(username);
//
//        // Assert
//        assertNotNull(activities);
//        assertFalse(activities.isEmpty());
//        for (UserActivity activity : activities) {
//            assertNotNull(activity.getActivityId());
//            assertNotNull(activity.getActivityType());
//            assertNotNull(activity.getActivityTimestamp());
//            assertNotNull(activity.getUser());
//            assertEquals(username, activity.getUser().getUsername());
//            assertNotNull(activity.getTask()); // Task can be null based on left join
//            assertNotNull(activity.getMilestone()); // Milestone can be null based on left join
//        }
//    }

    @Test
    public void testGetUserTaskByUsernameEmpty() {
        // Arrange
        String nonExistingUsername = "nonexistentuser"; // Assuming this username does not exist in your database

        // Act
        List<UserActivity> activities = userDAO.getUserTaskByUsername(nonExistingUsername);

        // Assert
        assertNotNull(activities);
        assertTrue(activities.isEmpty());
    }
//    @Test
//    public void testGetTasksAssignedToUserNotEmpty() {
//        // Arrange
//        String username = "testuser"; // Assuming this username exists in your database with assigned tasks
//
//        // Act
//        List<Task> tasks = userDAO.getTasksAssignedToUser(username);
//
//        // Assert
//        assertNotNull(tasks);
//        assertFalse(tasks.isEmpty());
//        for (Task task : tasks) {
//            assertNotNull(task.getTaskId());
//            assertNotNull(task.getTaskName());
//            assertNotNull(task.getStatus());
//        }
//    }

    @Test
    public void testGetTasksAssignedToUserEmpty() {
        // Arrange
        String nonExistingUsername = "nonexistentuser"; // Assuming this username does not exist in your database

        // Act
        List<Task> tasks = userDAO.getTasksAssignedToUser(nonExistingUsername);

        // Assert
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }
//    @Test
//    public void testGetProjectsByUsernameNotEmpty() {
//        // Arrange
//        String username = "testuser"; // Assuming this username exists in your database with assigned projects
//
//        // Act
//        List<Project> projects = userDAO.getProjectsByUsername(username);
//
//        // Assert
//        assertNotNull(projects, "Projects list should not be null");
//        assertFalse(projects.isEmpty(), "Projects list should not be empty");
//
//        for (Project project : projects) {
//            assertNotNull(project.getProjectId(), "Project ID should not be null for project: " + project.getProjectName());
//            assertNotNull(project.getProjectName(), "Project name should not be null");
//
//            Client client = project.getClientid();
//            assertNotNull(client, "Client object in project should not be null");
//            assertNotNull(client.getClientId(), "Client ID should not be null for project: " + project.getProjectName());
//            assertNotNull(client.getClientName(), "Client name should not be null for project: " + project.getProjectName());
//            assertNotNull(client.getClientDetails(), "Client details should not be null for project: " + project.getProjectName());
//        }
//    }

    @Test
    public void testGetProjectsByUsernameEmpty() {
        // Arrange
        String nonExistingUsername = "nonexistentuser"; // Assuming this username does not exist in your database

        // Act
        List<Project> projects = userDAO.getProjectsByUsername(nonExistingUsername);

        // Assert
        assertNotNull(projects);
        assertTrue(projects.isEmpty());
    }
    @Test
    public void testGetProjectsByPMIdNotEmpty() {
        // Arrange
        int pmId = 2; // Assuming this PM ID exists in your database with assigned projects

        // Act
        List<Project> projects = userDAO.getProjectsByPMId(pmId);

        // Assert
        assertNotNull(projects);
        assertFalse(projects.isEmpty());
        for (Project project : projects) {
            assertNotNull(project.getProjectId());
            assertNotNull(project.getProjectName());
            assertNotNull(project.getProjectDetails());
            assertNotNull(project.getStartDate()); // Assuming these are properly set
            assertNotNull(project.getEndDate());
            assertNotNull(project.getTask());
            assertNotNull(project.getTask().getTaskId());
            assertNotNull(project.getTask().getTaskName());
            assertNotNull(project.getTask().getStatus());
            assertNotNull(project.getTask().getAssignedto());
            assertNotNull(project.getTask().getAssignedto().getUsername());
        }
    }

    @Test
    public void testGetProjectsByPMIdEmpty() {
        // Arrange
        int nonExistingPmId = -1; // Assuming this PM ID does not exist in your database

        // Act
        List<Project> projects = userDAO.getProjectsByPMId(nonExistingPmId);

        // Assert
        assertNotNull(projects);
        assertTrue(projects.isEmpty());
    }
    @Test
    public void testAssignTaskToUserSuccess() {
        // Arrange
        int projectId = 2; // Assuming this project ID exists in your database
        int assignedToUserId = 2; // Assuming this user ID exists in your database
        String taskName = "Test Task";

        // Act
        boolean result = userDAO.assignTaskToUser(projectId, assignedToUserId, taskName);

        // Assert
        assertTrue(result);
        // You can add additional assertions here if needed
    }

    @Test
    public void testAssignTaskToUserFailure() {
        // Arrange
        int nonExistingProjectId = -1; // Assuming this project ID does not exist in your database
        int nonExistingUserId = -1; // Assuming this user ID does not exist in your database
        String taskName = "Test Task";

        // Act
        boolean result = userDAO.assignTaskToUser(nonExistingProjectId, nonExistingUserId, taskName);

        // Assert
        assertFalse(result);
        // You can add additional assertions here if needed
    }


    // Add more test cases to co





}