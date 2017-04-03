package fr.icam.emit.poweranalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class PowerAnalyzer implements Runnable {

	private String PORT = "/dev/ttyUSB0";
	
	private SerialPort port;
	
	private CommPortIdentifier identifier;
	
    private InputStream stream;
    
    private InputStreamReader reader;
    
    public void setUp() throws Exception {
    	started = false;
    	identifier = CommPortIdentifier.getPortIdentifier(PORT);
    }
    
    public void tearDown() throws Exception {
    }
    
    private boolean started;
    
    private double[] data;
    
    private int index;
    
    private File file;
    
    private OutputStream out;
    
    public final void doStart() throws Exception {
    	data = new double[4];
    	index = 0;
    	this.started = true;
    	port = (SerialPort) identifier.open(this.getClass().getSimpleName(), 2000);
    	port.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    	port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
    	stream = port.getInputStream();
    	reader = new InputStreamReader(stream);
		file = File.createTempFile("emit-poweranalyzer-", ".txt");
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
    
    private void map(InputStream input, OutputStream output) throws Exception {
    	int i = 0;
       	int[] t = new int[13];
   		int intch = -1;
       	InputStreamReader reader = new InputStreamReader(input);
       	try {
       		do {
       			intch = reader.read();
       			if (intch == '\r') {
       				if (i == 15) {
       					process(output, t);
       				} else {
       					System.err.println("ERROR " + i);
       				}
       				i = 0;
       			} else if (i == 15) {
       				System.err.println("WARN " + intch);
       			} else {
       				i++;
       				if (i > 2) {
       					t[i - 3] = intch;
       				}
       			}
       		} while (intch != -1);
       	} finally {
    		reader.close();
    	}
    }
    
    private void process(OutputStream out, int[] t) throws Exception {
    	switch (t[1]) {
    	case '4': ; power(out, t); break;
    	case '5': ; other(out, t); break;
    	default: break;
    	}
	}

	private void other(OutputStream out, int[] t) throws Exception {
    	switch (t[2]) {
    	case '0': ; voltage(out, t); break;
    	case '2': ; intensity(out, t); break;
    	case '4': ; factor(out, t); break;
    	default: break;
    	}		
	}
	
	private void release(OutputStream out) throws Exception {
		if (index == 24) {
			index = 0;
			String line = System.currentTimeMillis() + "\t" + data[0] + "\t" + data[1] + "\t" + data[2] + "\t" + data[3] + "\n";
			out.write(line.getBytes());
			out.flush();
		}
	}

	private void voltage(OutputStream out, int[] t) throws Exception {
		data[1] = value(t);
		index += 3;
		release(out);
	}

	private void intensity(OutputStream out, int[] t) throws Exception {
		data[2] = value(t);
		index += 7;
		release(out);
	}

	private void factor(OutputStream out, int[] t) throws Exception {
		data[3] = value(t);
		index += 13;
		release(out);
	}

	private void power(OutputStream out, int[] t) throws Exception {
    	switch (t[2]) {
    	case '8': ; power(out, t, true); break;
    	case '7': ; power(out, t, false); break;
    	default: break;
    	}		
	}

	private void power(OutputStream out, int[] t, boolean k) throws Exception {
		data[0] = value(t) * (k ? 1000 : 1);
		index += 1;
		release(out);
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
		out.close();
		reader.close();
		stream.close();
    	port.close();
   }

    public void doRetrieve(OutputStream stream) throws Exception {
    	InputStream in = new FileInputStream(this.file);
    	map(in, stream);
    	in.close();
    	file.delete();
    }
    
}