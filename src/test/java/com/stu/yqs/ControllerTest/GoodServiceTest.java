package com.stu.yqs.ControllerTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stu.yqs.YqsBootApplicationTests;
import com.stu.yqs.service.GoodService;
@Component
public class GoodServiceTest extends YqsBootApplicationTests{
	@Autowired
	private GoodService goodService;
	
	@Test
	public void newTransactionTest() {
		
	}
}
