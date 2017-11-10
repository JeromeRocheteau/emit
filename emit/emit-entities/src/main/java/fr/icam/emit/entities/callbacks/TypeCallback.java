package fr.icam.emit.entities.callbacks;

import fr.icam.emit.entities.Callback;
import fr.icam.emit.entities.Type;

public class TypeCallback extends Callback {

	private Type type;

	public Type getType() {
		return type;
	}

	public TypeCallback(Long id, Long issued, String user, Boolean atomic, String category, Type type) {
		super(id, issued, user, atomic, category);
		this.type = type;
	}

	
}
