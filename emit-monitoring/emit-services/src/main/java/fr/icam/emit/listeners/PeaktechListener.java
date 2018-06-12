package fr.icam.emit.listeners;

public class PeaktechListener extends PowermeterListener {

	@Override
	protected String getTopic() {
		return "peaktech-topic";
	}

}
