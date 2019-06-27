package pool;

import lombok.Data;

@Data
public class MyException extends RuntimeException {
    public String code;
    private String msg;
    private MyData mydata;

    public MyException(String msg) {
        super(msg);
    }
    public MyException(String msg, MyData mydata) {
        super(msg);
        this.mydata = mydata;
    }

    ;

}
