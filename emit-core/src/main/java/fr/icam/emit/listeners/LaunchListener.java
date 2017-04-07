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
		System.out.println("DISPATCHER");
		RequestDispatcher dispatcher = context.getNamedDispatcher("experiment-launch");
		System.out.println("REQUEST");
		HttpServletRequest request = new MockHttpServletRequest();
		System.out.println("RSPONSE");
		HttpServletResponse response = new MockHttpServletResponse();
		System.out.println("BEFORE " + dispatcher.getClass().getCanonicalName());
		// dispatcher.forward(request, response);
		System.out.println("AFTER " + dispatcher.getClass().getCanonicalName());
	}

}
