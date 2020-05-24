package ru.otus.autologging;

import ru.otus.autologging.annotation.Log;

public class TestLogging implements Service {
    @Log
    @Override
    public void calculationWithLog(int param) {
    }

    @Override
    public void calculationWithoutLog(int param) {
    }
}
