package fr.icam.emit.poweranalyzer;

import java.util.Enumeration;

import purejavacomm.CommPortIdentifier;

public class PortScanning {

	public static void main(String[] arguments) throws Exception {
		Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
		if (ports.hasMoreElements()) {	
			while (ports.hasMoreElements()) {
				CommPortIdentifier port = ports.nextElement();
				if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
					System.out.println(port.getName());
				}
			}
		}
	}
	
}