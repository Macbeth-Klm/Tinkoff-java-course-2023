package edu.hw2.task4;

public final class Task4 {
    private Task4() {
    }

    public static CallingInfo callingInfo() {
        StackTraceElement[] callStack = new RuntimeException().getStackTrace();
        return new CallingInfo(callStack[1].getClassName(), callStack[1].getMethodName());
    }

}
