package fr.icam.emit.arduino;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

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
