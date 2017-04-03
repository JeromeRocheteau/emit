package fr.icam.emit.arduino;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class SerialReading implements Runnable {

	private SerialPort port;
	
	private CommPortIdentifier identifier;
	
    private InputStream stream;
    
    private InputStreamReader reader;
    
	 @SuppressWarnings("unchecked")
     private void doList() throws Exception {
		 Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
		 if (ports.hasMoreElements()) {	
			 while (ports.hasMoreElements()) {
				 CommPortIdentifier port = ports.nextElement();
				 if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
					 if (identifier == null) {
						 identifier = port;
					 }
				 }
			 }
		 }
		 if (identifier == null) {
			 throw new NullPointerException();
		 }
	 }
    
    public void setUp() throws Exception {
    	started = false;
    	this.doList();
    	port = (SerialPort) identifier.open(this.getClass().getSimpleName(), 2000);
    	port.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    	port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
    	Thread.sleep(100);
    }
    
    public void tearDown() throws Exception {
    	port.close();
    }
    
    private boolean started;
    
    public final void doStart() throws Exception {
    	this.started = true;
    	stream = port.getInputStream();
    	reader = new InputStreamReader(stream);
        new Thread(this).start();
    }

    public void run() {
       	int intch = -1;
       	int i = 0;
       	int[] t = new int[13];
    	do {
    		try {
    			intch = reader.read();
    			if (intch == '\r') {
    				if (i == 15) {
    					process(t);
    				}
    				i = 0;
    			} else {
    				i++;
    				if (i > 2) {
        				t[i - 3] = intch;
    				}
    			}
    		} catch (IOException e) { /* TODO */ }
    	} while (intch != -1 && this.started);
    }
    
    private void process(int[] t) {
		switch (t[0]) {
		case 1: break;
		case 2: break;
		case 3: break;
		case 4: break;
		default: break;
		}
	}

	public final void doStop() throws Exception {
    	Thread.currentThread().interrupt();
    	this.started = false;
		reader.close();
		stream.close();
    }
    
    public static void main(String[] args) throws Exception {
        SerialReading app = new SerialReading();
        app.setUp();
        app.doStart();
        Thread.sleep(5000);
        app.doStop();
        app.tearDown();
}
    
}