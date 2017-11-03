package fr.icam.emit.types;

public enum Symbol {
	
	EQ("eq"), NEQ("neq"), LT("eq"), LEQ("eq"), GT("eq"), GEQ("eq");
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	private Symbol(String name) {
		this.name = name;
	}
	
	public <T extends Comparable<T>> boolean compare(T fst, T snd) throws Exception {
		int cmp = fst.compareTo(snd);
		switch (this) {
		case EQ:  return cmp == 0;
		case NEQ: return cmp != 0;
		case LT:  return cmp < 0;
		case LEQ: return cmp <= 0;
		case GT:  return cmp > 0;
		case GEQ: return cmp >= 0;
		default: return false;
		}					
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
