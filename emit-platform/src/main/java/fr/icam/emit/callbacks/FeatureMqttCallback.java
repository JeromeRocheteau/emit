package fr.icam.emit.callbacks;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import fr.icam.emit.types.Symbol;
import fr.icam.emit.types.Type;

public class FeatureMqttCallback<T extends Comparable<T>> extends EmitMqttCallback {

	private Symbol symbol;
	
	private Type<T> type;
	
	private T value;
	
	public FeatureMqttCallback(Symbol symbol, Type<T> type, T value) {
		this.symbol = symbol;
		this.type = type;
		this.value = value;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void messageArrived(String topic, MqttMessage message) {
		try {
			String name = (String) parameters.get("type");
			if (this.type.getName().equals(name)) {
				T value = (T) parameters.get("value");
				if (this.symbol.compare(this.value, value)) {
					this.status(true);
				} else {
					this.status(false);
				}
			} else {
				this.status(false);
			}
		} catch (Exception e) {
			this.status(false);
		}
	}

}
