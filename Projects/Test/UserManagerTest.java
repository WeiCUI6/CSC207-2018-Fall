package fall2018.csc2017.GameCenter;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the class Usermanager
 */
public class UserManagerTest {

    /**
     * Filed that is designed to test
     */
    private UserManager userManager;

    /**
     * Set up before the test
     */
    @Before
    public void setUp() {
        userManager = UserManager.getInstance();
    }

    /**
     * JUnit test for the method singIn in the class UserManger
     */
    @Test
    public void signIn() {
        setUp();

        //Initialize a nwe user for testing
        User user = new User("cuiwei6", "123456");
        Map<String, User> userListTest = new HashMap<>();
        userListTest.put("cuiwei6", user);

        //Variables for testing
        String name1 = "jiangb14";
        String name2 = "cuiwei6";
        String password1 = "123456";
        String password2 = "12345678";

        //Test when the name isn't in the userList and it works properly
        boolean testResult = userManager.signIn(name1, password1, userListTest);
        assertFalse(testResult);

        //Test when the name is in the userList but the password does not match the corresponding user name
        //Therefore it should return false as expected
        testResult = userManager.signIn(name2, password2, userListTest);
        assertFalse(testResult);

        //Test whether it works properly when the user is in the list and the password is correct
        testResult = userManager.signIn(name2, password1, userListTest);
        assertTrue(testResult);
    }

    /**
     * Test whether the method signUp works properly
     */
    @Test
    public void signUp() {
        // Set up for testing
        setUp();

        // Initialize a user for testing
        User user = new User("Kewei Qiu", "123456");
        Map<String, User> userListTest = new HashMap<>();
        userListTest.put("Kewei Qiu", user);

        // Player name and password
        String name = "Kewei Qiu";
        String password = "123456";

        // Actual test
        boolean testResult = userManager.signUp(name, password, userListTest);
        assertFalse(testResult);
    }
}
