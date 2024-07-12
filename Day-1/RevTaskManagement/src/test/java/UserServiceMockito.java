import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.DAO.UserDAO;
import org.example.Models.*;
import org.example.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.List;

public class UserServiceMockito {

    @Mock
    private UserDAO userDAOMock;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserService();
        userService.userDAO = userDAOMock; // Inject mock into UserService
    }

    @Test
    public void testRegisterUser() {
        User user = new User(0,"testuser", "password", "testuser@example.com", "Team member");
        when(userDAOMock.register(user)).thenReturn(true);

        boolean result = userService.registerUser(user);

        assertTrue(result);
        verify(userDAOMock, times(1)).register(user);
    }

    @Test
    public void testLoginUser() {
        String username = "testuser";
        String password = "password";
        User expectedUser = new User(1, "testuser", "password", "testuser@example.com", "user");
        when(userDAOMock.login(username, password)).thenReturn(expectedUser);

        User actualUser = userService.loginUser(username, password);

        assertNotNull(actualUser);
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        verify(userDAOMock, times(1)).login(username, password);
    }

    @Test
    public void testCreateUser() {
        User user = new User(0,"testuser", "password", "testuser@example.com", "user");
        when(userDAOMock.createUser(user)).thenReturn(true);

        boolean result = userService.createUser(user);

        assertTrue(result);
        verify(userDAOMock, times(1)).createUser(user);
    }

    @Test
    public void testUpdateUser() {
        User user = new User(1, "testuser", "password", "testuser@example.com", "user");
        when(userDAOMock.updateUser(user)).thenReturn(true);

        boolean result = userService.updateUser(user);

        assertTrue(result);
        verify(userDAOMock, times(1)).updateUser(user);
    }

    @Test
    public void testDeactivateUser() {
        int userId = 1;
        when(userDAOMock.deactivateUser(userId)).thenReturn(true);

        boolean result = userService.deactivateUser(userId);

        assertTrue(result);
        verify(userDAOMock, times(1)).deactivateUser(userId);
    }

    @Test
    public void testGetUserByUsername() {
        String username = "testuser";
        User expectedUser = new User(1, "testuser", "password", "testuser@example.com", "user");
        when(userDAOMock.getUserByUsername(username)).thenReturn(expectedUser);

        User actualUser = userService.getUserByUsername(username);

        assertNotNull(actualUser);
        assertEquals(expectedUser.getUsername(), actualUser.getUsername());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        verify(userDAOMock, times(1)).getUserByUsername(username);
    }

    @Test
    public void testGetUserActivitiesByUsername() {
        String username = "testuser";
        List<UserActivity> expectedActivities = Arrays.asList(
                new UserActivity(1, new User(), new Task(), new Milestone(), "Type1", null),
                new UserActivity(2, new User(), new Task(), new Milestone(), "Type2", null)
        );
        when(userDAOMock.getUserActivitiesByUsername(username)).thenReturn(expectedActivities);

        List<UserActivity> actualActivities = userService.getgetUserByUsername(username);

        assertNotNull(actualActivities);
        assertFalse(actualActivities.isEmpty());
        assertEquals(expectedActivities.size(), actualActivities.size());
        verify(userDAOMock, times(1)).getUserActivitiesByUsername(username);
    }

    @Test
    public void testGetUserTaskByUsername() {
        String username = "testuser";
        List<UserActivity> expectedTasks = Arrays.asList(
                new UserActivity(1, new User(), new Task(), new Milestone(), "Type1", null),
                new UserActivity(2, new User(), new Task(), new Milestone(), "Type2", null)
        );
        when(userDAOMock.getUserTaskByUsername(username)).thenReturn(expectedTasks);

        List<UserActivity> actualTasks = UserService.getgetUserTasksByUsername(username);

        assertNotNull(actualTasks);
        assertFalse(actualTasks.isEmpty());
        assertEquals(expectedTasks.size(), actualTasks.size());
        verify(userDAOMock, times(1)).getUserTaskByUsername(username);
    }

    @Test
    public void testGetTasksAssignedToUser() {
        String username = "testuser";
        List<Task> expectedTasks = Arrays.asList(
                new Task(0, "Task1", 1, 3, "pending"),
                new Task(1, "Task2", 2, 2, "completed")
        );
        when(userDAOMock.getTasksAssignedToUser(username)).thenReturn(expectedTasks);

        List<Task> actualTasks = userService.getTasksAssignedToUser(username);

        assertNotNull(actualTasks);
        assertFalse(actualTasks.isEmpty());
        assertEquals(expectedTasks.size(), actualTasks.size());
        verify(userDAOMock, times(1)).getTasksAssignedToUser(username);
    }

    @Test
    public void testGetProjectsByUsername() {
        String username = "testuser";
        List<Project> expectedProjects = Arrays.asList(
                new Project(1, "Project1", new Client(), new User(), "Details1", 2023, 2024, new Task()),
                new Project(2, "Project2", new Client(), new User(), "Details2", 2023, 2024, new Task())
        );
        when(userDAOMock.getProjectsByUsername(username)).thenReturn(expectedProjects);

        List<Project> actualProjects = userService.getProjectsByUsername(username);

        assertNotNull(actualProjects);
        assertFalse(actualProjects.isEmpty());
        assertEquals(expectedProjects.size(), actualProjects.size());
        verify(userDAOMock, times(1)).getProjectsByUsername(username);
    }

    @Test
    public void testGetProjectsByPMId() {
        int pmId = 1;
        List<Project> expectedProjects = Arrays.asList(
                new Project(1, "Project1", new Client(), new User(), "Details1", 2023, 2024, new Task()),
                new Project(2, "Project2", new Client(), new User(), "Details2", 2023, 2024, new Task())
        );
        when(userDAOMock.getProjectsByPMId(pmId)).thenReturn(expectedProjects);

        List<Project> actualProjects = userService.getProjectsByPMId(pmId);

        assertNotNull(actualProjects);
        assertFalse(actualProjects.isEmpty());
        assertEquals(expectedProjects.size(), actualProjects.size());
        verify(userDAOMock, times(1)).getProjectsByPMId(pmId);
    }

    @Test
    public void testAssignTaskToUser() {
        int projectId = 1;
        int assignedToUserId = 2;
        String taskName = "Task1";
        when(userDAOMock.assignTaskToUser(projectId, assignedToUserId, taskName)).thenReturn(true);

        boolean result = userService.getassignTaskToUser(projectId, assignedToUserId, taskName);

        assertTrue(result);
        verify(userDAOMock, times(1)).assignTaskToUser(projectId, assignedToUserId, taskName);
    }

    // Add more tests for other methods as needed...

}
