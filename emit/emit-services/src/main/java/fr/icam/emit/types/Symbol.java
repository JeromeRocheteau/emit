package fr.icam.emit.types;

public enum Symbol {
	
	EQ, NEQ, LT, LEQ, GT, GEQ;
	
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
	
	public String getName() {
		return this.toString().toLowerCase();
	}
	
}
