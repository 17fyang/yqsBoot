package com.stu.yqs.aspect;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * date:2020-2-4
 * author:yf
 * detail:配置在参数上的注解，检测空参数的情况。
 * 				若配置了该注解的参数在请求过程中为空，则会抛出501的logicException
 * 				具体的反射逻辑在OverallAspect的emptyParameterDeal方法中
 */
@Retention(RUNTIME)
@Target(PARAMETER)
public @interface NecessaryPara {
	
}
