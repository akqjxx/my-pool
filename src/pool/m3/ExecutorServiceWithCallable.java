package pool.m3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceWithCallable {

    public void executorServiceForCallable(int poolSize) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);

        List<Future> futures = new ArrayList<Future>();
        for (int index = 0; index < poolSize; index++) {
            Future future = executorService.submit(new RepeatingGreetings(index));
            futures.add(future);
        }

        for (Future future : futures) {
            System.out.println(future.get());
        }
    }

    class RepeatingGreetings implements Callable<String> {
        private int index;

        RepeatingGreetings(int index) {
            this.index = index;
        }

        @Override
        public String call() throws Exception {
            System.out.println("Callable: " + index + " is working");
            int sleepValue = (int) (Math.random() * 1000);
            Thread.sleep(sleepValue);
            return ("Callable: " + index + " sleept for: " + sleepValue);
        }
    }
}