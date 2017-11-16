package fr.icam.emit.entities.callbacks;

import fr.icam.emit.entities.Callback;
import fr.icam.emit.entities.Symbol;
import fr.icam.emit.entities.Type;

public class FeatureCallback extends Callback {

	private Symbol symbol;
	
	private Type type;

	private String value;

	public Symbol getSymbol() {
		return symbol;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}

	public FeatureCallback(Long id, Long issued, String user, Boolean atomic, String category, Symbol symbol, Type type, String value) {
		super("#" + id + " " + category + " callback: " + symbol.getName() + " " + value + " : " + type.getName(), id, issued, user, atomic, category);
		this.symbol = symbol;
		this.type = type;
		this.value = value;
	}

	
}
