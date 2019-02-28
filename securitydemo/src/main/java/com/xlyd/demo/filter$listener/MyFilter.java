package com.xlyd.demo.filter$listener;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;

import com.xlyd.demo.util.LogUtils;

@WebFilter
public class MyFilter implements Filter {

	private final Logger log = LogUtils.getLog(MyFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String reqId = request.getLocalName();
		log.info("*** filter invocated: " + reqId);
		chain.doFilter(request, response);
	}

}
