package fr.icam.arduinommetter.arduinometter;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import purejavacomm.CommPortIdentifier;
import purejavacomm.NoSuchPortException;
import purejavacomm.SerialPort;
import purejavacomm.UnsupportedCommOperationException;



public class metter implements Runnable {

private static final String PORT = "COM3"; // "/dev/ttyACM0";
	
	

	private SerialPort port;
	
	private CommPortIdentifier identifier;
	
    private InputStream stream;
    
    private InputStreamReader reader;
    
    private boolean started;
    
    
    
    public metter() throws Exception {
    	
    }
    
  
    
   
    
    public void run() {    	
    	try {
			this.read_line();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
    public  void read_line() throws Exception{
    	
    	//while (started){
    	if (port!= null){
    	port.close();
    	}
    	
    	identifier = CommPortIdentifier.getPortIdentifier(PORT);
    	port = (SerialPort) identifier.open(this.getClass().getSimpleName(), 2000);
    	port.setSerialPortParams(115200, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
    	port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
    	Thread.sleep(100);    	
    	
    	MessageHandler message_handler= new MessageHandler();
    	
    	try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(port.getInputStream()));
			String line = "";
			try {
				while((line = reader.readLine()) != null) {
					//System.out.println(line);
					message_handler.send(line);
					}
				reader.close();
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
				
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
    	//}
    	
    	/*
    	
    	String line = "";    	
    	int inch = -1;
    	stream = port.getInputStream();    	
    	reader = new InputStreamReader(stream);    	
    	inch = reader.read();    	
    	//byte[] bytes = IOUtils.toByteArray(reader);
    	//line = new String(bytes, "UTF-8");
    	
    	return inch;
    	*/
    }
    
    
	
}
