package edu.cn.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Before("execution(public * edu.cn.demo.service.impl.*.*(..))")
    public void beforeService(JoinPoint jp){
        String info = "进入" + jp.getSignature() + "方法";
        info += "参数是：";
        for(Object arg : jp.getArgs()){
            info += arg + "";
        }
        logger.info(info);
    }

}
