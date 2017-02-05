package com.nviz.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.nviz.vo.User;

public class SessionControllerFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain pChain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		User user = (User) httpRequest.getSession().getAttribute("user");
		if(user != null){
			request.setAttribute("userName", user.getFirstName());
			request.setAttribute("transient","false");
		} else{
			request.setAttribute("transient","true");
		}
		pChain.doFilter(request,response);
	}
	
	@Override
	public void destroy() {

	}

	@Override
	public void init(FilterConfig paramFilterConfig) throws ServletException {

	}

}
