package fr.icam.emit.listeners;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LaunchListener implements ServletContextListener, Runnable {

	private ServletContext context;
	
	public void contextInitialized(ServletContextEvent sce) {
		context = sce.getServletContext();
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		scheduler.scheduleAtFixedRate(this, 0, 1, TimeUnit.MINUTES);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

	public void run() {
		RequestDispatcher dispatcher = context.getNamedDispatcher("experiment-launch");
		System.out.println(dispatcher.getClass().getCanonicalName());
		// TODO dispatcher.forward(request, response);
	}

}
