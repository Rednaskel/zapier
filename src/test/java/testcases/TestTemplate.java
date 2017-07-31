package testcases;

import driver.wrapper.DriverWrapper;
import fixtures.rules.SuccessRule;
import fixtures.rules.FailRule;
import org.junit.Rule;

public class TestTemplate {

    protected DriverWrapper driver = new DriverWrapper();

    @Rule
    public final FailRule screenshotRule = new FailRule(driver);
    @Rule
    public final SuccessRule closeDriverRule = new SuccessRule(driver);

    public TestTemplate() throws Exception {
    }

}
