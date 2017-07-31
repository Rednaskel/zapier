package fixtures;

import driver.wrapper.DriverWrapper;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class SuccessRule extends TestWatcher {

    DriverWrapper driver;

    public SuccessRule(DriverWrapper driver) {
        this.driver = driver;
    }

    @Override
    protected void succeeded(Description description) {
        driver.close();
    }

}
