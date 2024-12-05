import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test class for LoginApp
public class LoginAppTest {

    // Test case 1: Valid email and password
    @Test
    public void testValidUser() {
        LoginApp app = new LoginApp();
        try {
            String result = app.authenticateUser("johndoe@example.com", "password123");
            assertEquals("John Doe", result); // Expected name for valid credentials
        } catch (Exception e) {
            fail("Exception should not be thrown for valid credentials: " + e.getMessage());
        }
    }

    // Test case 2: Invalid email
    @Test
    public void testInvalidEmail() {
        LoginApp app = new LoginApp();
        try {
            String result = app.authenticateUser("invalid@example.com", "password123");
            assertNull(result); // No user should be returned for invalid email
        } catch (Exception e) {
            fail("Exception should not be thrown for invalid email: " + e.getMessage());
        }
    }

    // Test case 3: Valid email but invalid password
    @Test
    public void testInvalidPassword() {
        LoginApp app = new LoginApp();
        try {
            String result = app.authenticateUser("johndoe@example.com", "wrongpassword");
            assertNull(result); // Password doesn't match
        } catch (Exception e) {
            fail("Exception should not be thrown for invalid password: " + e.getMessage());
        }
    }

    // Test case 4: Empty email or password
    @Test
    public void testEmptyFields() {
        LoginApp app = new LoginApp();
        try {
            String result = app.authenticateUser("", "");
            assertNull(result); // Both fields are empty
        } catch (Exception e) {
            fail("Exception should not be thrown for empty fields: " + e.getMessage());
        }
    }

    // Test case 5: SQL injection attempt
    @Test
    public void testSQLInjection() {
        LoginApp app = new LoginApp();
        try {
            String result = app.authenticateUser("'; DROP TABLE User; --", "password123");
            assertNull(result); // Ensure injection doesn't return a valid user
        } catch (Exception e) {
            fail("Exception should not be thrown for SQL injection: " + e.getMessage());
        }
    }
}
