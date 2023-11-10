package edu.cn.demo.aspect;

import lombok.Getter;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ControllerAspect {

    //Logger logger = LoggerFactory.getLogger(getClass());
    // API调用次数统计
    @Getter
    Map<String, Long> callingTimes = Collections.synchronizedMap(new HashMap<>());

    // API最长响应时间统计
    @Getter
    Map<String, Long> longestTime = Collections.synchronizedMap(new HashMap<>());

    // API最短响应时间统计
    @Getter
    Map<String, Long> shortestTime = Collections.synchronizedMap(new HashMap<>());

    // API平均响应时间统计
    @Getter
    Map<String, Double> avgTime = Collections.synchronizedMap(new HashMap<>());

    // API异常次数统计
    @Getter
    Map<String, Integer> exceptionTimes = Collections.synchronizedMap(new HashMap<>());


    // 单独定义pointcut
    @Pointcut("execution(public * edu.cn.demo.controller.*.*(..))")
    public void controllerPointCut() {

    }

    // 统计API调用次数的方法，在API调用之后使用
    @After("controllerPointCut()")
    public void countCallingTimes(JoinPoint jp){
        String signature = jp.getSignature().toString();
        if(!callingTimes.containsKey(signature)){
            callingTimes.put(signature, 1L);   // 1L表示为Long类型
            return;
        }

        Long times = callingTimes.get(signature);
        callingTimes.put(signature, times + 1);
    }

    // 统计API最长、最短、平均响应时间的方法
    @Around("controllerPointCut()")
    public void countLongestTime(ProceedingJoinPoint jp) throws Throwable{
        Long t1 = Calendar.getInstance().getTimeInMillis();
        Object retValue = jp.proceed();
        Long t2 = Calendar.getInstance().getTimeInMillis();

        String signature = jp.getSignature().toString();
        Long time = t2 - t1;
        if(!longestTime.containsKey(signature)){
            longestTime.put(signature, time);
            shortestTime.put(signature, time);
            avgTime.put(signature, time.doubleValue());
            return;
        }

        Double avg = ( ( callingTimes.get(signature) - 1 ) * avgTime.get(signature) + time ) / callingTimes.get(signature);
        avgTime.put(signature, avg);
        if(time > longestTime.get(signature)){
            longestTime.put(signature, time);
        }
        if(time < shortestTime.get(signature)){
            shortestTime.put(signature, time);
        }
    }

    // 统计API的异常次数，在有异常产生时使用
    @AfterThrowing(pointcut = "controllerPointCut()")
    public void countExceptions(JoinPoint jp){
        String signature = jp.getSignature().toString();
        if(!exceptionTimes.containsKey(signature)){
            exceptionTimes.put(signature, 1);
            return;
        }

        Integer times = exceptionTimes.get(signature);
        exceptionTimes.put(signature, times + 1);
    }

}
