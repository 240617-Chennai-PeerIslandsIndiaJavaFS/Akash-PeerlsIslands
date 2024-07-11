package org.example.DAO;

import org.example.Connection.DBConnection;
import org.example.Models.*;

import java.sql.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    Connection conn;
    public UserDAOImpl(){
        this.conn = DBConnection.getConnection();
    }


    @Override
    public boolean register(User user) {
        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                logger.info("User registered successfully: {}", user.getUsername());
                return true;
            } else {
                logger.warn("Failed to register user: {}", user.getUsername());
                return false;
            }
        } catch (SQLException ex) {
            logger.error("Error registering user: {}", ex.getMessage(), ex);
            return false;
        }
    }


    @Override
    public User login(String username, String password) {

        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String email = rs.getString("email");
                    String role = rs.getString("role");
                    return new User(userId, username, password, email, role);
                } else {
                    logger.warn("User login failed for username: {}", username);
                }
            }
        } catch (SQLException ex) {
            logger.error("Error logging in: {}", ex.getMessage(), ex);
        }
        return null;
    }

    @Override
    public boolean createUser(User user) {

        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                logger.info("User created successfully: {}", user.getUsername());
                return true;
            } else {
                logger.warn("Failed to create user: {}", user.getUsername());
                return false;
            }
        } catch (SQLException ex) {
            logger.error("Error creating user: {}", ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password = ?, email = ?, role = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.setInt(5, user.getUserId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                logger.info("User updated successfully: {}", user.getUsername());
                return true;
            } else {
                logger.warn("Failed to update user: {}", user.getUsername());
                return false;
            }
        } catch (SQLException ex) {
            logger.error("Error updating user: {}", ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public boolean deactivateUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                logger.info("User deactivated successfully. User ID: {}", userId);
                return true;
            } else {
                logger.warn("Failed to deactivate user. User ID: {}", userId);
                return false;
            }
        } catch (SQLException ex) {
            logger.error("Error deactivating user. User ID: {}. Error: {}", userId, ex.getMessage(), ex);
            return false;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String role = rs.getString("role");
                    return new User(userId, username, password, email, role);
                } else {
                    logger.warn("No user found with username: {}", username);
                }
            }
        } catch (SQLException ex) {
            logger.error("Error fetching user by username: {}", username, ex);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String role = rs.getString("role");
                userList.add(new User(userId, username, password, email, role));
            }
        } catch (SQLException ex) {
            logger.error("Error fetching users: {}", ex.getMessage(), ex);
        }
        return userList;
    }

    @Override
    public List<User> getAllUsersByRole(String role) {

        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE role = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, role);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int userId = rs.getInt("user_id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    userList.add(new User(userId, username, password, email, role));
                }
            }
        } catch (SQLException ex) {
            logger.error("Error fetching users by role: {}", ex.getMessage(), ex);
        }
        return userList;
    }

    @Override
    public boolean assignTeamMemberToProject(int projectId, int userId) {

        return false;
    }

    @Override
    public List<UserActivity> getUserActivitiesByUsername(String username) {
        List<UserActivity> activities = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT UA.ActivityID, U.Username, U.User_id, U.email, U.role, " +
                    "       UA.ActivityType, UA.ActivityTimestamp, " +
                    "       T.TaskID, T.TaskName, " +
                    "       M.MilestoneID, M.MilestoneName, M.DueDate " +
                    "FROM UserActivity UA " +
                    "JOIN Users U ON UA.User_id = U.User_id " +
                    "LEFT JOIN Task T ON UA.TaskID = T.TaskID " +
                    "LEFT JOIN Milestone M ON UA.MilestoneID = M.MilestoneID " +
                    "WHERE U.Username = ? " +
                    "ORDER BY UA.ActivityTimestamp DESC";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int activityId = rs.getInt("ActivityID");
                String activityType = rs.getString("ActivityType");
                Timestamp activityTimestamp = rs.getTimestamp("ActivityTimestamp");

                // Retrieve User data
                int userId = rs.getInt("User_id");
                String userEmail = rs.getString("email");
                String userRole = rs.getString("role");
                User user = new User(userId, username, null /* password */, userEmail, userRole);

                // Retrieve Task data
                int taskId = rs.getInt("TaskID");
                String taskName = rs.getString("TaskName");
                Task task = new Task(taskId, taskName, null /* projectid */, null /* assignedto */, null /* status */);

                // Retrieve Milestone data
                int milestoneId = rs.getInt("MilestoneID");
                String milestoneName = rs.getString("MilestoneName");
                Date dueDate = rs.getDate("DueDate");
                Milestone milestone = new Milestone(milestoneId, milestoneName, null /* projectid */, dueDate);

                // Create UserActivity object
                UserActivity activity = new UserActivity(activityId, user, task, milestone, activityType, activityTimestamp);
                activities.add(activity);
            }
        } catch (SQLException ex) {
            logger.error("Error fetching user activities by username: {}", username, ex);
        } finally {

            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                logger.error("Error closing database resources: {}", e.getMessage(), e);
            }
        }

        return activities;
    }

    @Override
    public List<UserActivity> getUserTaskByUsername(String username) {
        List<UserActivity> activities = new ArrayList<>();
        String sql = "SELECT UA.ActivityID, U.Username, UA.ActivityType, UA.ActivityTimestamp, " +
                "T.TaskName, M.MilestoneName " +
                "FROM UserActivity UA " +
                "JOIN Users U ON UA.User_id = U.User_id " +
                "LEFT JOIN Task T ON UA.TaskID = T.TaskID " +
                "LEFT JOIN Milestone M ON UA.MilestoneID = M.MilestoneID " +
                "WHERE U.Username = ? " +
                "ORDER BY UA.ActivityTimestamp DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int activityId = rs.getInt("ActivityID");
                    String activityType = rs.getString("ActivityType");
                    Timestamp activityTimestamp = rs.getTimestamp("ActivityTimestamp");

                    User user = new User();
                    user.setUsername(rs.getString("Username"));

                    Task task = null;
                    String taskName = rs.getString("TaskName");
                    if (taskName != null) {
                        task = new Task();
                        task.setTaskName(taskName);
                    }

                    Milestone milestone = null;
                    String milestoneName = rs.getString("MilestoneName");
                    if (milestoneName != null) {
                        milestone = new Milestone();
                        milestone.setMilestoneName(milestoneName);
                    }

                    UserActivity activity = new UserActivity(activityId, user, task, milestone, activityType, activityTimestamp);
                    activities.add(activity);
                }
            }
        } catch (SQLException ex) {
            logger.error("Error fetching user activities by username: {}", username, ex);
        }
        return activities;
    }

    public List<Task> getTasksAssignedToUser(String username) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM task t " +
                "JOIN Users u ON t.AssignedTo = u.User_id " +
                "WHERE u.Username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task();
                    task.setTaskId(rs.getInt("TaskID"));
                    task.setTaskName(rs.getString("TaskName"));
                    task.setStatus(rs.getString("Status"));
                    tasks.add(task);
                }
            }
        } catch (SQLException ex) {
            logger.error("Error fetching tasks assigned to user '{}': {}", username, ex.getMessage(), ex);
        }
        return tasks;
    }

    public List<Project> getProjectsByUsername(String username) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT c.ClientID, c.ClientName, c.ClientDetails, " +
                "p.ProjectID, p.ProjectName " +
                "FROM client c " +
                "INNER JOIN project p ON c.ClientID = p.ClientID " +
                "INNER JOIN users u ON p.PM_ID = u.User_id " +
                "WHERE u.Username = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Client client = new Client();
                    client.setClientId(rs.getInt("ClientID"));
                    client.setClientName(rs.getString("ClientName"));
                    client.setClientDetails(rs.getString("ClientDetails"));

                    Project project = new Project();
                    project.setProjectId(rs.getInt("ProjectID"));
                    project.setProjectName(rs.getString("ProjectName"));
                    project.setClientid(client); // Set the client object in the project

                    projects.add(project);
                }
            }
        } catch (SQLException ex) {
            logger.error("Error fetching projects for user '{}': {}", username, ex.getMessage(), ex);
        }
        return projects;
    }

    public List<Project> getProjectsByPMId(int pmId) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT " +
                "    p.ProjectID, " +
                "    p.ProjectName, " +
                "    p.ProjectDetails, " +
                "    p.Start_date, " +
                "    p.End_date, " +
                "    t.TaskID, " +
                "    t.TaskName, " +
                "    t.Status, " +
                "    u.Username AS AssignedToUsername " +
                "FROM " +
                "    project p " +
                "    INNER JOIN task t ON p.ProjectID = t.ProjectID " +
                "    INNER JOIN users u ON t.AssignedTo = u.User_id " +
                "WHERE " +
                "    p.PM_ID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pmId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int projectId = rs.getInt("ProjectID");
                    String projectName = rs.getString("ProjectName");
                    String projectDetails = rs.getString("ProjectDetails");
                    int startDate = rs.getInt("Start_date");
                    int endDate = rs.getInt("End_date");

                    Project project = new Project();
                    project.setProjectId(projectId);
                    project.setProjectName(projectName);
                    project.setProjectDetails(projectDetails);
                    project.setStartDate(startDate);
                    project.setEndDate(endDate);

                    Task task = new Task();
                    task.setTaskId(rs.getInt("TaskID"));
                    task.setTaskName(rs.getString("TaskName"));
                    task.setStatus(rs.getString("Status"));

                    project.setTask(task);

                    User assignedTo = new User();
                    assignedTo.setUsername(rs.getString("AssignedToUsername"));

                    task.setAssignedto(assignedTo);

                    // Check if project already exists in list
                    if (!projects.contains(project)) {
                        projects.add(project);
                    }
                }
            }
        } catch (SQLException ex) {
            logger.error("Error fetching projects for PM ID '{}': {}", pmId, ex.getMessage(), ex);
        }

        return projects;
    }

    public boolean assignTaskToUser(int projectId, int assignedToUserId, String taskName) {
        String sql = "INSERT INTO task (TaskName, ProjectID, AssignedTo, Status) " +
                "VALUES (?, ?, ?, 'Pending')";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taskName);
            stmt.setInt(2, projectId);
            stmt.setInt(3, assignedToUserId);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException ex) {
            logger.error("Error assigning task '{}' to user with ID '{}' for project ID '{}': {}",
                    taskName, assignedToUserId, projectId, ex.getMessage(), ex);
            return false;
        }
    }
}