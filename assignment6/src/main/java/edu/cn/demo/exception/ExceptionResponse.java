package edu.cn.demo.exception;

import lombok.Data;

@Data
public class ExceptionResponse {
    private Integer code;

    private String message;
}
