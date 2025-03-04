package example2.tests;

import all.utils.GenerateDriverAll;
import all.utils.JsonUtils;
import example2.actions.Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginUserWithValidCredentialsTests {

    private static final Logger logger = LogManager.getLogger(LoginUserWithValidCredentialsTests.class);
    WebDriver driver;
    Actions actions;

    /**
     * Sets up the test environment by initializing the WebDriver and Actions.
     * Reads the URL and browser type from the JSON configuration file.
     */
    @BeforeSuite(description = "setting up the test environment", alwaysRun = true)
    public void setUp() {
        String URL_FOR_SUBSCRIPTION = JsonUtils.readJsonFromFile("url_for_subscription");
        String BROWSER = JsonUtils.readJsonFromFile("browser");
        logger.debug("WebDriver setup complete: {}, {}", BROWSER, URL_FOR_SUBSCRIPTION);
        driver = GenerateDriverAll.initDriver(BROWSER, URL_FOR_SUBSCRIPTION);
        actions = new Actions(driver);
    }

    /**
     * Testing the login positive scenario.
     */
    @Test(description = "testing the login positive", groups = {"smoke", "regression", "login"})
    public void loginPositiveTest() {
        boolean result = actions.loginValidCredentials();
        if (result) {
            logger.info("Login form positive test passed.");
        } else {
            logger.error("Login form positive test failed.");
        }
        Assert.assertTrue(result, "Login form positive test failed.");
    }

    /**
     * Cleans up the test environment by quitting the WebDriver.
     */
    @AfterSuite(description = "cleaning up the test environment", alwaysRun = true)
    public void tearDown() {
        GenerateDriverAll.cleanDriver(driver);
        logger.debug("Registration form positive test ended.");
    }
}