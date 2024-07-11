import org.example.DAO.UserDAOImpl;
import org.example.Models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDAOImplTest {

    private static UserDAOImpl userDAO;

    @BeforeAll
    public static void setUp() {
        userDAO = new UserDAOImpl();
    }

    @Test
    public void testRegisterSuccess() {
        User user = new User(0,"testuser", "testpassword", "testuser@example.com", "Team member");

        boolean result = userDAO.register(user);

        assertTrue(result);
    }

    @Test
    public void testRegisterFailure() {

        User existingUser = new User(0,"existinguser", "existingpassword", "existing@example.com", "Team member");
        userDAO.register(existingUser);

        User duplicateUser = new User(0,"existinguser", "newpassword", "new@example.com", "Team member");

        boolean result = userDAO.register(duplicateUser);

        assertFalse(result);
    }
}
