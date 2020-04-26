package com.stu.yqs;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@Component
@SpringBootTest()
public class test extends YqsBootApplicationTests{
	@Test
	public void testDemo() {
		Logger log=(Logger) LoggerFactory.getLogger(getClass());
		log.info("aaaaaaaaaaaaaaaaaaaaaaaaaa");
	}
}
