package fr.icam.emit.spy;

import java.net.URI;

import fr.icam.emit.api.Environment;
import fr.icam.emit.api.Launcher;

public class SpyMeterClientTest {
	
	private final static String HOST = "http://172.16.220.253:8080/emitlauncher/";
	
    public static void main(String[] args) throws Exception {
    	URI uri = URI.create(HOST);
    	Launcher app = new Launcher(uri);
    	System.out.println(app.isMeter());    		
    	Environment info = app.getEnvironment();
    	System.out.println(info.getArch() + " " + info.getSys() + " " + info.getVersion());    		
    	if (app.canLaunch("ls")) {
        	Long t = app.doLaunch(0L, "ls", "-l", "/var/local/emit");
        	System.out.println(t);    		
    	}
    }

}