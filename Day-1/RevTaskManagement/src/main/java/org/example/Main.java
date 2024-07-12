package org.example;

import org.example.Models.*;
import org.example.Service.UserService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Welcome to User Management System ===");
            System.out.println("+----------------------------------------+");
            System.out.println("|             1. Register                 |");
            System.out.println("|             2. Login                    |");
            System.out.println("|             3. Exit                     |");
            System.out.println("+----------------------------------------+");

            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.println("=== User Registration ===");
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine().trim();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine().trim();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine().trim();
                    System.out.print("Enter role (admin/project manager/team member): ");
                    String role = scanner.nextLine().trim();

                    if (userService.loginUser(username, password) != null) {
                        System.out.println("User already exists. Please login.");
                    } else {
                        User newUser = new User(0, username, password, email, role);
                        boolean registrationSuccess = userService.registerUser(newUser);
                        if (registrationSuccess) {
                            System.out.println("User registration successful!");
                        } else {
                            System.out.println("Failed to register user.");
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n=== User Login ===");
                    System.out.print("Enter username: ");
                    username = scanner.nextLine().trim();
                    System.out.print("Enter password: ");
                    password = scanner.nextLine().trim();

                    User loggedInUser = userService.loginUser(username, password);
                    if (loggedInUser != null) {
                        System.out.println("Login successful! Welcome, " + loggedInUser.getUsername() + " (" + loggedInUser.getRole() + ")");
                        handleUserRole(scanner, userService, loggedInUser);
                    } else {
                        System.out.println("Login failed. Invalid username or password. Please Register.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleUserRole(Scanner scanner, UserService userService, User user) {
        switch (user.getRole().toLowerCase()) {
            case "admin":
                handleAdminFunctions(scanner, userService);
                break;
            case "project manager":
                handleProjectManagerFunctions(scanner, userService);
                break;
            case "team member":
                handleTeamMemberFunctions(scanner, userService,user);
                break;
            default:
                System.out.println("Invalid role.");
        }
    }

    private static void handleAdminFunctions(Scanner scanner, UserService userService) {
        while (true) {
            System.out.println("\n=== Admin Functions ===");
            System.out.println("1. Create User");
            System.out.println("2. Update User");
            System.out.println("3. Deactivate User");
            System.out.println("4. Assign Access Levels");
            System.out.println("5. Track User Activity");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.println("=== User Registration ===");
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine().trim();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine().trim();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine().trim();
                    System.out.print("Enter role (admin/project manager/team member): ");
                    String role = scanner.nextLine().trim();

                    User newUser = new User(0, username, password, email, role);
                    boolean registrationSuccess = userService.registerUser(newUser);
                    if (registrationSuccess) {
                        System.out.println("User registration successful!");
                    } else {
                        System.out.println("Failed to register user.");
                    }
                    break;

                case 2:
                    System.out.println("=== Update User ===");
                    System.out.print("Enter username of the user to update: ");
                    String updateUsername = scanner.nextLine().trim();


                    User userToUpdate = userService.getUserByUsername(updateUsername);
                    if (userToUpdate == null) {
                        System.out.println("User not found.");
                    } else {

                        System.out.println("Current Details:");
                        System.out.println("Username: " + userToUpdate.getUsername());
                        System.out.println("Password: " + userToUpdate.getPassword());
                        System.out.println("Email: " + userToUpdate.getEmail());
                        System.out.println("Role: " + userToUpdate.getRole());


                        System.out.println("\nEnter new details:");
                        System.out.print("New Password: ");
                        String newPassword = scanner.nextLine().trim();
                        System.out.print("New Email: ");
                        String newEmail = scanner.nextLine().trim();
                        System.out.print("New Role (admin/project manager/team member): ");
                        String newRole = scanner.nextLine().trim();


                        userToUpdate.setPassword(newPassword);
                        userToUpdate.setEmail(newEmail);
                        userToUpdate.setRole(newRole);


                        boolean updateSuccess = userService.updateUser(userToUpdate);
                        if (updateSuccess) {
                            System.out.println("User updated successfully!");
                        } else {
                            System.out.println("Failed to update user.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("=== Deactivate User ===");
                    System.out.print("Enter username of the user to deactivate: ");
                    String deactivateUsername = scanner.nextLine().trim();


                    User userToDeactivate = userService.getUserByUsername(deactivateUsername);
                    if (userToDeactivate == null) {
                        System.out.println("User not found.");
                    } else {

                        boolean deactivateSuccess = userService.deactivateUser(userToDeactivate.getUserId());
                        if (deactivateSuccess) {
                            System.out.println("User deactivated successfully!");
                        } else {
                            System.out.println("Failed to deactivate user.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("=== Assign Access Levels ===");
                    System.out.print("Enter username of the user to assign access levels: ");
                    String assignUsername = scanner.nextLine().trim();


                    User userToAssignAccess = userService.getUserByUsername(assignUsername);
                    if (userToAssignAccess == null) {
                        System.out.println("User not found.");
                    } else {
                        System.out.print("Enter new access level (e.g., admin, project manager, team member): ");
                        String newRole = scanner.nextLine().trim();


                        userToAssignAccess.setRole(newRole);
                        boolean updateSuccess = userService.updateUser(userToAssignAccess);
                        if (updateSuccess) {
                            System.out.println("Access level updated successfully!");
                        } else {
                            System.out.println("Failed to update access level.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("=== Track User Activity ===");
                    System.out.print("Enter username of the user to track activity: ");
                    String trackUsername = scanner.nextLine().trim();


                    List<UserActivity> activities = userService.getgetUserByUsername(trackUsername);


                    System.out.println("User activities for username: " + trackUsername);
                    for (UserActivity activity : activities) {
                        System.out.println("Activity ID: " + activity.getActivityId());
                        System.out.println("Username: " + activity.getUser().getUsername());
                        System.out.println("Activity Type: " + activity.getActivityType());
                        System.out.println("Activity Timestamp: " + activity.getActivityTimestamp());


                        if (activity.getTask() != null) {
                            System.out.println("Task Name: " + activity.getTask().getTaskName());
                        }

                        if (activity.getMilestone() != null) {
                            System.out.println("Milestone Name: " + activity.getMilestone().getMilestoneName());
                        }

                        System.out.println();
                    }
                    break;

                case 6:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleProjectManagerFunctions(Scanner scanner, UserService userService) {
        while (true) {
            System.out.println("\n=== Project Manager Functions ===");
            System.out.println("1. Manage Client Information");
            System.out.println("2. Manage Project Details and Tasks");
            System.out.println("3. Add Team Members to Projects and Tasks");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    List<Project> projects = userService.getProjectsByUsername(username);

                    for (Project project : projects) {
                        System.out.println("Client ID: " + project.getClientid().getClientId());
                        System.out.println("Client Name: " + project.getClientid().getClientName());
                        System.out.println("Client Details: " + project.getClientid().getClientDetails());
                        System.out.println("Project ID: " + project.getProjectId());
                        System.out.println("Project Name: " + project.getProjectName());
                        System.out.println("---------------------------");
                    }
                    break;
                case 2:
                    System.out.print("Enter PM_ID of the Project Manager: ");
                    int pmId = scanner.nextInt();
                    scanner.nextLine();
                    List<Project> projectss = userService.getProjectsByPMId(pmId);

                    for (Project project : projectss) {
                        System.out.println("Project ID: " + project.getProjectId());
                        System.out.println("Project Name: " + project.getProjectName());
                        System.out.println("Project Details: " + project.getProjectDetails());
                        System.out.println("Start Date: " + project.getStartDate());
                        System.out.println("End Date: " + project.getEndDate());

                        System.out.println("Tasks:");
                        Task task = project.getTask();
                        if (task != null) {
                            System.out.println("  Task ID: " + project.getTask().getTaskId());
                            System.out.println("  Task Name: " + task.getTaskName());
                            System.out.println("  Status: " + task.getStatus());
                            if (task.getAssignedto() != null) {
                                System.out.println("  Assigned To: " + task.getAssignedto().getUsername());
                            } else {
                                System.out.println("  Assigned To: Not assigned");
                            }
                        } else {
                            System.out.println("  No task assigned to this project.");
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter project ID: ");
                    int projectId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter task name: ");
                    String taskName = scanner.nextLine();

                    System.out.print("Enter team member's user ID to assign the task: ");
                    int assignedToUserId = scanner.nextInt();
                    scanner.nextLine();

                    userService.getassignTaskToUser(projectId, assignedToUserId, taskName);

                    System.out.println("Task '" + taskName + "' assigned to user with ID " + assignedToUserId);




            case 4:
                System.out.println("Logging out...");
                return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    private static void handleTeamMemberFunctions(Scanner scanner, UserService userService,User user) {
        while (true) {
            System.out.println("\n=== Team Member Functions ===");
            System.out.println("1. View project details and assigned tasks");
            System.out.println("2. view the status of assigned tasks and Task details");
            System.out.println("3. update the status of assigned tasks");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(scanner.nextLine().trim());

            switch (choice) {
                case 1:
                    List<UserActivity> userActivities = userService.getgetUserTasksByUsername(user.getUsername());
                    for (UserActivity activity : userActivities) {
                        System.out.println("Activity ID: " + activity.getActivityId());
                        System.out.println("Username: " + activity.getUser().getUsername());
                        System.out.println("Activity Type: " + activity.getActivityType());
                        System.out.println("Activity Timestamp: " + activity.getActivityTimestamp());
                        System.out.println("Task Name: " + (activity.getTask() != null ? activity.getTask().getTaskName() : "N/A"));
                        System.out.println("Milestone Name: " + (activity.getMilestone() != null ? activity.getMilestone().getMilestoneName() : "N/A"));
                        System.out.println("---------------------------");
                    }
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    List<Task> tasks = userService.getTasksAssignedToUser(username);

                    if (tasks.isEmpty()) {
                        System.out.println("No tasks assigned to user: " + username);
                    } else {
                        System.out.println("Tasks assigned to user " + username + ":");
                        for (Task task : tasks) {
                            System.out.println("Task ID: " + task.getTaskId());
                            System.out.println("Task Name: " + task.getTaskName());
                            System.out.println("Status: " + task.getStatus());
                            System.out.println("---------------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter TaskID: ");
                    int taskId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character after nextInt()

                    // Prompt user to enter new status
                    System.out.print("Enter new status (Pending, In Progress, Completed): ");
                    String newStatus = scanner.nextLine();

                    boolean updated = UserService.updateTaskStatus(taskId, newStatus);

                    if (updated) {
                        System.out.println("Task status updated successfully.");
                    } else {
                        System.out.println("Failed to update task status.");
                    }
                    break;


                case 4:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }
}
