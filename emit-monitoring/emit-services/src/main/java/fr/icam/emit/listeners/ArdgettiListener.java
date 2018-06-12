package fr.icam.emit.listeners;

public class ArdgettiListener extends PowermeterListener {

	@Override
	protected String getTopic() {
		return "ardgetti-topic";
	}

}
