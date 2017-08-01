package testcases.homepage;

import org.junit.*;
import pageobjects.HomePage;
import testcases.TestTemplate;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class LogoTest extends TestTemplate {

    private HomePage homePage = new HomePage(driver);

    public LogoTest() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        homePage.goTo();
    }

    @Test
    public void logoPresent() throws IOException {
        assertThat(homePage.isLogoClassPresent()).as("Zapir Logo").isTrue();
        assertThat(homePage.isLogoImagePresent()).as("Zapir Logo image").isTrue();
    }

    @Test
    public void integrateIconPresent() {
        assertThat(homePage.isIntegrateIconPresent()).as("Integrate Icon present").isTrue();
    }

    @Test
    public void automateIconPresent() {
        assertThat(homePage.isAutomateIconPresent()).as("Automate Icon present").isTrue();
    }

    @Test
    public void innovateIconPresent() {
        assertThat(homePage.isInnovateIconPresent()).as("Innovate Icon present").isTrue();
    }

    @Test
    public void testFail() {
        assertThat(homePage.isInnovateIconPresent()).as("Innovate Icon present").isFalse();
    }

}
