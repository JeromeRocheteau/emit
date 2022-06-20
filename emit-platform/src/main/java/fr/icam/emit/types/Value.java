package fr.icam.emit.types;

import fr.icam.emit.types.Type;

public class Value<T extends Comparable<T>> {

	private Type<T> type;
	
	private T value;
	
	public Type<T> getType() {
		return type;
	}
	
	public T getValue() {
		return value;
	}
	
	public Value(Type<T> type, T value) {
		this.type = type;
		this.value = value;
	}
	
	public <U extends Comparable<U>> boolean canCompare(Value<U> value) {
		return this.type.getName().compareTo(value.getType().getName()) == 0;
	}
	
	@SuppressWarnings("unchecked")
	public <U extends Comparable<U>> int doCompare(Value<U> value) throws Exception {
		return this.value.compareTo((T) value.getValue());
	}
	
}
