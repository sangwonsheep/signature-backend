package com.example.filter.aop;

import com.example.filter.model.UserRequest;
import org.apache.catalina.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class TimerAop {

    @Pointcut(value = "within(com.example.filter.controller.UserApiController)")
    public void timerPointCut() {

    }

    // 메소드 실행되기 전에 실행
    @Before(value = "timerPointCut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before");
    }

    // 메소드 끝난 지점에 실행
    @After(value = "timerPointCut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after");
    }

    // 예외가 발생하지 않은 경우
    @AfterReturning(value = "timerPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("after returning");
    }

    // 예외가 발생한 경우
    @AfterThrowing(value = "timerPointCut()", throwing = "tx")
    public void afterThrowing(JoinPoint joinPoint, Throwable tx) {
        System.out.println("after throwing");
    }

    /**
     *  API가 느린 구간을 찾기 위해 AOP를 활용함.
     *  타이머를 찍어서 얼마나 걸리는지 확인
     */
    @Around(value = "timerPointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("메소드 실행 이전");

        Arrays.stream(joinPoint.getArgs())
                .forEach(i -> {
                    if(i instanceof UserRequest) {
                        UserRequest tempUser = (UserRequest) i;
                        String phoneNumber = tempUser.getPhoneNumber().replace("-", "");
                        tempUser.setPhoneNumber(phoneNumber);
                    }
                    System.out.println(i);
                });

        // 암,복호화 / 로깅
        List<UserRequest> newObjs = Arrays.asList(new UserRequest());

        // 어떤 API가 느린지 확인하기 위함
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        joinPoint.proceed(newObjs.toArray());
        stopWatch.stop();

        System.out.println("총 소요된 시간 : " + stopWatch.getTotalTimeMillis());

        System.out.println("메소드 실행 이후");
    }
}
