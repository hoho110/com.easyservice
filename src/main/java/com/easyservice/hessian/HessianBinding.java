package com.easyservice.hessian;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;

import com.caucho.hessian.server.HessianSkeleton;
import com.easyservice.exception.ProtocolParseException;
import com.easyservice.service.IProtocolBinding;
import com.easyservice.service.IRemoteServiceRegister;
import com.easyservice.support.EasyServiceConstant;
import com.easyservice.support.HttpParse;
import com.easyservice.support.ServiceRequest;
import com.easyservice.support.ServiceResponse;
import com.easyservice.support.ServiceResponse.ExceptionType;
import com.easyservice.utils.ApplicationContextUtil;
@Component("HessianBinding")
public class HessianBinding implements IProtocolBinding{
	@Autowired
	IRemoteServiceRegister register;
	private Map<String,MyHessianExporter> exporters=new HashMap<String,MyHessianExporter>();
	@Override
	public ServiceRequest getRequest(HttpParse parse)
			throws ProtocolParseException {
		MyHessianExporter exporter=null;
		exporter=exporters.get(parse.getInterfaceName());
		if(exporter==null)
		{
			Class apiClass=null;
			try {
				apiClass = Class.forName(parse.getInterfaceName());
			} catch (ClassNotFoundException e) {
				throw new ProtocolParseException(e,ExceptionType.ET_SE_CLASS_NOT_FOUND);
			}
			Object service=ApplicationContextUtil.getBean(null, apiClass);
			if(service==null)
			{
				//TODO 回复Hessian错误
				throw new ProtocolParseException("Apply service is null");
			}
			exporter=new MyHessianExporter(new MyHessianSkeleton(service,apiClass));
			exporter.setServiceInterface(apiClass);
			exporter.setService(service);
		}
		ServiceRequest serviceRequest=new ServiceRequest();
		serviceRequest.setHttpParse(parse);
		serviceRequest.setInterfacClass(exporter.getServiceInterface());
		try {
			exporter.handleRequest(parse.getRequest(), parse.getResponse());
		} catch (Throwable e) {
			throw new ProtocolParseException(e,ExceptionType.ET_SE_CLASS_NOT_FOUND);
		}
		return serviceRequest;
	}

	@Override
	public void replyResponse(HttpParse parse, ServiceResponse resp)
			throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getProtocol() {
		return EasyServiceConstant.PROTOCOL_BINGDING_HESSIAN;
	}

	@Override
	public Class getInterfaceClass(String name) throws ClassNotFoundException {
		return null;
	}
	public Object postProcessAfterInitialization(Object bean, String beanName)
			throws BeansException {
		register.register(getProtocol(), this);
		return bean;
	}
}
