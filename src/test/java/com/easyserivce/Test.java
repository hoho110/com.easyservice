package com.easyserivce;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.easyservice.test.IUserService;

public class Test {
	ApplicationContext context = null;
	IUserService userService = null;

	@Before
	public void initContext() {
		this.context = new FileSystemXmlApplicationContext(
				"/src/main/webapp/WEB-INF/spring/root-context.xml");
		this.userService = (IUserService) context.getBean("lenderDao");
	}
}
