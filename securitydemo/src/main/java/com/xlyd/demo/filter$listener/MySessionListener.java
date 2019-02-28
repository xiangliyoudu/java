package com.xlyd.demo.filter$listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;

import com.xlyd.demo.util.LogUtils;

@WebListener
public class MySessionListener implements HttpSessionListener {

	private final Logger log = LogUtils.getLog(MySessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		String msg = "session: " + se.getSession().getId() + " created...";
		log.info(msg);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		String msg = "session: " + se.getSession().getId() + " destoryed...";
		log.info(msg);
	}

}
