package com.stu.yqs.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;

/*
 * date:2019-11-8
 * author:yf
 * info:全局的controller异常和逻辑处理方法，
 * 			1、获取controller返回的json，封装成restful模式。
 * 			2、扫描所有的controller包，若controller出现异常则返回errcode：500
 * 			3、处理用户的logicException逻辑异常
 * 			4、检测处理controller中的自定义注解
 */

@Aspect
@Component
public class OverallAspect {

    @Around("execution(* com.stu.yqs.controller..*(..))")
    public Object exceptionDeal(ProceedingJoinPoint pjp) {
        Object result = null;
        JSONObject json = new JSONObject();
        try {
            Object[] args = pjp.getArgs();
            //必要参数检验
            emptyParameterDeal(pjp);
            result = pjp.proceed(args);
        } catch (LogicException logicE) {
            json.put("errcode", logicE.getErrCode());
            json.put("message", logicE.getInfo());
            json.put("data", new JSONObject());
            return json.toString();
        } catch (Throwable e) {
            e.printStackTrace();
            json.put("errcode", 500);
            json.put("message", "未知错误");
            json.put("data", new JSONObject());
            return json.toString();
        }
        json.put("errcode", 200);
        json.put("message", "处理成功");
        json.put("data", JSONObject.parse(result.toString()));
        return json.toJSONString();
    }

    //空参数校验
    private void emptyParameterDeal(ProceedingJoinPoint pjp) throws LogicException {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();
        Object args[] = pjp.getArgs();
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].isAnnotationPresent(NecessaryPara.class)) {
                if (args[i] == null || args[i].equals("")) {
                    String errorLog = parameters[i].getName() + "参数不得为空";
                    throw new LogicException(501, errorLog);
                }
            }
        }
    }
}
