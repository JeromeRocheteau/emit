package fr.icam.emit.poweranalyzer;

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
    
    private double[] data;
    
    private int index;
    
    public final void doStart() throws Exception {
    	data = new double[4];
    	index = 0;
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
    	switch (t[1]) {
    	case '4': ; power(t); break;
    	case '5': ; other(t); break;
    	default: break;
    	}
	}

	private void other(int[] t) {
    	switch (t[2]) {
    	case '0': ; voltage(t); break;
    	case '2': ; intensity(t); break;
    	case '4': ; factor(t); break;
    	default: break;
    	}		
	}
	
	private void release() {
		if (index == 24) {
			index = 0;
			System.out.println(System.currentTimeMillis() + "\t" + data[0] + "\t" + data[1] + "\t" + data[2] + "\t" + data[3]);
		}
	}

	private void voltage(int[] t) {
		data[1] = value(t);
		index += 3;
		release();
	}

	private void intensity(int[] t) {
		data[2] = value(t);
		index += 7;
		release();
	}

	private void factor(int[] t) {
		data[3] = value(t);
		index += 13;
		release();
	}

	private void power(int[] t) {
    	switch (t[2]) {
    	case '8': ; power(t, true); break;
    	case '7': ; power(t, false); break;
    	default: break;
    	}		
	}

	private void power(int[] t, boolean k) {
		data[0] = value(t) * (k ? 1000 : 1);
		index += 1;
		release();
	}

	private double value(int[] t) {
		double v = 0;
		for (int i = 5; i < 13; i++) {
			int j = Character.getNumericValue(t[i]);
			v = v + (j * Math.pow(10, 12 - i));
		}
		v = v / Math.pow(10, dec(t));
		return (t[3] == '0') ? v : -v;
	}

	private double dec(int[] t) {
		double d = 0;
		switch (t[4]) {
    	case '0': ; d = 0; break;
    	case '1': ; d = 1; break;
    	case '2': ; d = 2; break;
    	case '3': ; d = 3; break;
    	default: break;
    	}
		return d;
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