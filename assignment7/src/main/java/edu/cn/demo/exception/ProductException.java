package edu.cn.demo.exception;

import edu.cn.demo.domain.Product_supplier;
import lombok.Data;

@Data
public class ProductException extends Exception{
    //定义的各种错误代码常量
    public final static int GET_ERROR = 100;
    public final static int INPUT_ERROR = 101;
    public final static int UPDATE_ERROR = 102;
    public final static int DELETE_ERROR = 103;

    int code;
    public ProductException(int code, String message){
        super(message);
        this.code=code;
    }
}
