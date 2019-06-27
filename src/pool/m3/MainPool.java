package pool.m3;

import java.util.concurrent.ExecutionException;

public class MainPool {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorServiceWithCallable executorServiceWithCallable = new ExecutorServiceWithCallable();
        executorServiceWithCallable.executorServiceForCallable(5);
    }
}
