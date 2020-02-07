package com.stu.yqs.utils;

import com.stu.yqs.aspect.LogicException;
import com.stu.yqs.domain.EnumPackage.Academy;

public class ParameterUtil {
	public static String getAcademy(String academy) throws LogicException{
		return Academy.stringByValue(academy);
	}
}
