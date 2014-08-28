package com.easyservice.test;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
@Service
public class UserServiceImpl implements IUserService {

	@Override
	public User find(String id) {
		HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		return new User("wangm",1);
	}

	@Override
	public List<User> findAll() {
		List<User> list=new ArrayList<User>();
		list.add(new User("wangm",0));
		list.add(new User("wangm2",1));
		return list;
	}

	@Override
	public void create(User user) {
		System.out.println("create:user"+user);
	}

}
