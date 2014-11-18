package com.easyserivce;

import java.util.List;

import com.caucho.hessian.client.HessianProxyFactory;
import com.easyservice.test.IUserService;
import com.easyservice.test.User;

public class HessianTest {
	
	public static void main(String[] args)
	{
		try {    
            String url = "http://localhost:8080/easyservice/"+IUserService.class.getName();    
            HessianProxyFactory factory = new HessianProxyFactory();    
            IUserService userService = (IUserService) factory.create(IUserService.class, url);    
            List<User> users=userService.findAll();
            System.out.println(users);
        } catch (Exception e) {    
            e.printStackTrace();    
        }  
	}
}
