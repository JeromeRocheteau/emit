package fr.icam.emit.entities.callbacks;

import fr.icam.emit.entities.Callback;
import fr.icam.emit.entities.Type;

public class ValueCallback extends Callback {

	private Type type;

	private String value;
	
	public Type getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}

	public ValueCallback(Long id, String name, Long issued, String user, Boolean atomic, String category, Type type, String value) {
		super(id, name, issued, user, atomic, category);
		this.type = type;
		this.value = value;
	}

	
}
