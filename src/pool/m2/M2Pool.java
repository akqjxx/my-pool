package pool.m2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class M2Pool {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        List<Future<Integer>> resultList = new ArrayList<Future<Integer>>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int rand = random.nextInt(10);

            FactorialCalculator factorialCalculator = new FactorialCalculator(rand);
            Future<Integer> res = executor.submit(factorialCalculator);//异步提交, non blocking.
            resultList.add(res);
        }
        resultList.forEach((integerFuture) -> {
            Integer integer = null;
            try {
                integer = integerFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(integer);

        });
        executor.shutdown();
    }
}
