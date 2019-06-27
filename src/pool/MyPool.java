package pool;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class MyPool {
    public static void main(String[] args) {
        ThreadPoolExecutor executor =// Executors.newCachedThreadPool();
                new ThreadPoolExecutor(50,
                        100,
                        60,
                        TimeUnit.SECONDS,
                        new LinkedBlockingQueue(50000),
                        Executors.defaultThreadFactory());
        List<Future<MyData>> futureList = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            MyData myData = new MyData(i, i, "hello");
            DataCallable dataCallable = new DataCallable(myData);
            Future<MyData> myDataFuture = executor.submit(dataCallable);
            futureList.add(myDataFuture);
        }
        do {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (executor.getCompletedTaskCount() < futureList.size());

        futureList.forEach((myDataFuture) -> {
            MyData myData = null;
            try {
                myData = myDataFuture.get();
            } catch (InterruptedException|ExecutionException e) {
                e.printStackTrace();
                System.out.println(e.getClass().getName());
                if (e instanceof MyException){
                    System.out.println(e);
                }
                MyException myException= (MyException)e.getCause();
                System.out.println(myDataFuture.getClass().getName());
                FutureTask futureTask = (FutureTask)myDataFuture;

                System.err.println(myException.getMydata()+"-"+myDataFuture);
            }
           // System.out.println(myData);

        });

        executor.shutdown();
    }

}
