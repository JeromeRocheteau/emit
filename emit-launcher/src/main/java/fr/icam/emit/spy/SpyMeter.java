package fr.icam.emit.spy;

import java.io.File;
import java.io.OutputStream;

import org.apache.commons.lang3.SystemUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.icam.emit.api.Environment;

public class SpyMeter implements Runnable {

    private ObjectMapper mapper;
    
    private SpyLauncher launcher;
    
    public void setUp() throws Exception {
    	mapper = new ObjectMapper();
    }
    
    public void tearDown() throws Exception {
    	Thread.currentThread().interrupt();
    }
    
    public final void doLaunch(long timeout, String cmd, String... args) throws Exception {
    	launcher = new SpyLauncher();
    	launcher.setTimeout(timeout);
    	launcher.setCommand(cmd);
    	launcher.setArguments(args);
    	launcher.doLaunch();
    }

    public void run() { }
    
    public void doRetrieve(OutputStream stream) throws Exception {
    	mapper.writeValue(stream, launcher.getDuration());    	
    }
    
	public final void doInfo(OutputStream stream) throws Exception {
		Environment measurement = new Environment();
		measurement.setArch(SystemUtils.OS_ARCH);
		measurement.setSys(SystemUtils.OS_NAME);
		measurement.setVersion(SystemUtils.OS_VERSION);
    	mapper.writeValue(stream, measurement);   
	}

    public final void doCheck(String cmd) throws Exception {
    	File file = this.doFind(cmd);
    	this.doCheck(cmd, file);
    }

	private void doCheck(String cmd, File file) throws Exception {
		if (file == null) {
			throw new Exception(cmd + " not found");
		} else {
			if (file.exists()) {
	    		if (file.isFile()) {
	    			if (file.canExecute()) {
	    				return;
	    			} else {
	    				throw new Exception(cmd + " can't be executed");
	    			}
	    		} else {
	    			throw new Exception(cmd + " isn't a file");	
	    		}
	    	} else {
	    		throw new Exception(cmd + " doesn't exist");
	    	}			
		}
	}
	
	private File doFind(String name) {
		File file = new File(name);
		if (file.isAbsolute()) {
			return file;
		} else {
			String path = System.getenv("PATH");
			String[] paths = path.split(File.pathSeparator);
			for (String dir : paths) {
				File f = new File(dir, name);
				if (f.isFile()) {
					return f;
				}
			}
	        return null;
		}
    }
    
}