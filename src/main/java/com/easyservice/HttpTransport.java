package com.easyservice;

import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.easyservice.service.IPermissionManager;
import com.easyservice.support.EasyServiceConstant;
import com.easyservice.support.HttpParse;
import com.easyservice.support.Role;

@Controller
public class HttpTransport {
	private static Logger logger = Logger.getLogger(HttpTransport.class);
	@Autowired
	IPermissionManager permissionManager;
	@RequestMapping("/easyservice/")
	public void service(HttpServletRequest req, HttpServletResponse resp)
	{
		try {
			HttpParse parse=new HttpParse(req, resp);
			HttpSession session=req.getSession();
			Object user=session.getAttribute(EasyServiceConstant.SESSION_ATTRIBUTENAME_USER);
			int role=EasyServiceConstant.USER_ANONYMOUS;//默认匿名用户
			if(!(user instanceof Role))
			{
				throw new Exception("用户类需要继承com.easyservice.Role，已登录用户使用EasyServiceConstant.SESSION_ATTRIBUTENAME_USER作为KEY放入Session");
			}
			role=((Role)user).getRole();
			if(parse.isFetchSdl())
			{
				if(!permissionManager.checkPermission(role, parse.isFetchSdl(), parse.getRequestURL().getInterfaceName(), null))
				{
					
				}
			}else
			{
				
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//处理阶段
		//1.请求解析生成请求描述，根据requestURI以及参数（包括header）解析出->a.请求协议;b.请求服务/请求Sdl;c.请求服务接口
		
		//2.根据请求协议调取协议绑定服务，输入请求描述->请求服务描述
		
		//3.根据服务描述调用服务方法执行，然后生成服务响应描述
		
		//4.根据请求协议调用协议绑定服务，输入服务响应描述，写回结果
	}
}
