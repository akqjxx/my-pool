package pool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class DataCallable implements Callable<MyData> {
    private MyData myData;

    public DataCallable(MyData myData) {
        this.myData = myData;
    }

    @Override
    public MyData call() throws Exception {
        System.out.println("Callable: " + myData.getIndex() + " is working");
        Random random = new Random();
        int r = random.nextInt(50);
        if (myData.getIndex() % 5 == 0) {
            TimeUnit.MICROSECONDS.sleep(300);
            throw new MyException("数据处理失败",myData);
        }
        this.myData.setCode(r);
        return myData;
    }
}
