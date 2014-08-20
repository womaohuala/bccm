package com.cn.bccm.common.webContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;


import javax.servlet.ServletContextEvent;

/**
 * @author yelp
 */
public class ApplicationContextUtilsInitListener extends ContextLoaderListener {
	
	private Log log = LogFactory.getLog(ApplicationContextUtilsInitListener.class);
	
	public void contextInitialized(ServletContextEvent event) {
		
		super.contextInitialized(event);
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		ServiceLocator.setApplicationContext(context);
		log.info("ApplicationcContext InitListener sucess!!!");
	}
	
}
