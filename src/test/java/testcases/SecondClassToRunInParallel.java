package testcases;

import org.junit.Test;

public class SecondClassToRunInParallel {


    @Test
    public void _1() throws InterruptedException {
        Thread.sleep(6000);
    }

    @Test
    public void _2() throws InterruptedException {
        Thread.sleep(6000);
    }

    @Test
    public void _3() throws InterruptedException {
        Thread.sleep(6000);
    }

}
