package com.collections.junit.timeout;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class TimeoutTest {

    @Test
    @Timeout(2)
    void testLongRunningTask() throws InterruptedException {
        Thread.sleep(1000);
    }
}