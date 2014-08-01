package com.easyservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HttpTransport {
	@RequestMapping("/easyservice/")
	public void service(HttpServletRequest req, HttpServletResponse resp)
	{
		
	}
}
