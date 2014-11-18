package com.easyservice.hessian;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.remoting.caucho.HessianExporter;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.util.NestedServletException;

import com.caucho.hessian.server.HessianSkeleton;

public class MyHessianExporter extends HessianExporter implements HttpRequestHandler{
	/**
	 * Processes the incoming Hessian request and creates a Hessian response.
	 */
	private MyHessianSkeleton skeleton;
	public MyHessianExporter(MyHessianSkeleton skeleton)
	{
		this.skeleton=skeleton;
	}
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!"POST".equals(request.getMethod())) {
			throw new HttpRequestMethodNotSupportedException(request.getMethod(),
					new String[] {"POST"}, "HessianServiceExporter only supports POST requests");
		}

		response.setContentType(CONTENT_TYPE_HESSIAN);
		try {
			doInvoke(skeleton,request.getInputStream(), response.getOutputStream());
		}
		catch (Throwable ex) {
		  throw new NestedServletException("Hessian skeleton invocation failed", ex);
		}
	}
	public HessianSkeleton getSkeleton() {
		return skeleton;
	}
	public void setSkeleton(MyHessianSkeleton skeleton) {
		this.skeleton = skeleton;
	}
}
