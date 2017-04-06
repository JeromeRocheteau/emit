package fr.icam.emit.listeners;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class LaunchListener implements ServletContextListener, Runnable {

	private ServletContext context;

	private ScheduledExecutorService scheduler;
	
	public void contextInitialized(ServletContextEvent sce) {
		context = sce.getServletContext();
		scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(this, 0, 10, TimeUnit.SECONDS);
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		scheduler.shutdown();
	}

	public void run() {
		RequestDispatcher dispatcher = context.getNamedDispatcher("experiment-launch");
		HttpServletRequest request = new MockHttpServletRequest(context);
		HttpServletResponse response = new MockHttpServletResponse();
		try {
			dispatcher.include(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
