package fr.icam.emit.callbacks;

import org.eclipse.paho.client.mqttv3.MqttMessage;

import fr.icam.emit.types.Symbol;
import fr.icam.emit.types.Value;

public class FeatureMqttCallback<T extends Comparable<T>> extends EmitMqttCallback {

	private Symbol symbol;
	
	private Value<T> value;
	
	public FeatureMqttCallback(Symbol symbol, Value<T> value) {
		this.symbol = symbol;
		this.value = value;
	}
	
	@Override
	public void messageArrived(String topic, MqttMessage message) {
		try {
			@SuppressWarnings("unchecked")
			Value<T> value = (Value<T>) parameters.get("_value");
			if (this.symbol.compare(this.value.getValue(), value.getValue())) {
				parameters.put("_value", true);	
			} else {
				parameters.put("_value", false);	
			}
		} catch (Exception e) {
			parameters.put("_value", false);
		}
	}

}
