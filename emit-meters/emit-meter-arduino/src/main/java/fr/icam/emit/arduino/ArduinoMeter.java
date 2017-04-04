package fr.icam.emit.arduino;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import purejavacomm.CommPortIdentifier;
import purejavacomm.SerialPort;

public class ArduinoMeter implements Runnable {

	private static final String PORT = "COM3"; // "/dev/ttyACM0";
	
	private SerialPort port;
	
	private CommPortIdentifier identifier;
	
    private InputStream stream;
    
    private InputStreamReader reader;

    public void setUp() throws Exception {
    	started = false;
    	identifier = CommPortIdentifier.getPortIdentifier(PORT);
    	port = (SerialPort) identifier.open(this.getClass().getSimpleName(), 2000);
    	port.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    	port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
    	Thread.sleep(100);
    }
    
    public void tearDown() throws Exception {
    	port.close();
    }
    
    private boolean started;
    
    private File file;
    
    private OutputStream out;
    
    public final void doStart() throws Exception {
    	this.started = true;
    	stream = port.getInputStream();
    	reader = new InputStreamReader(stream);
		file = File.createTempFile("emit-meter-arduino-", ".csv");
		out = new FileOutputStream(file);
        new Thread(this).start();
    }

    public void run() {
       	int intch = -1;
    	do {
    		try {
    			intch = reader.read();
    			out.write(intch);
    		} catch (IOException e) { /* TODO */ }
    	} while (intch != -1 && this.started);
    }
	    
    public final void doStop() throws Exception {
    	Thread.currentThread().interrupt();
    	this.started = false;
		out.close();
		reader.close();
		stream.close();
    }
    
    public void doRetrieve(OutputStream stream) throws Exception {
    	InputStream in = new FileInputStream(file);
    	IOUtils.copy(in, stream);
    	in.close();
    	file.delete();
    }
	
    /*
    @Override
    public void interrupt() {
    	super.interrupt();
    	try {
    	    stream.close();
    		reader.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    */
    
}