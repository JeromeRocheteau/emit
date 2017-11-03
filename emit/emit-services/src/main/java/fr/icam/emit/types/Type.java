package fr.icam.emit.types;

import java.text.DateFormat;
import java.util.Date;

public abstract class Type<T extends Comparable<T>> {
	
	public static final Type<String> STRING = new StringType();
	
	public static final Type<Boolean> BOOLEAN = new BooleanType();
	
	public static final Type<Integer> INTEGER = new IntegerType();
	
	public static final Type<Long> LONG = new LongType();
	
	public static final Type<Float> FLOAT = new FloatType();
	
	public static final Type<Double> DOUBLE = new DoubleType();
	
	public static final Type<Date> DATE = new DateType();
	
	public static final Type<java.util.UUID> UUID = new UuidType();
	
	public static final Type<java.net.URI> URI = new UriType();
	
	protected Class<T> type;
	
	public abstract T valueOf(String string) throws Exception;
	
	public String getName() {
		return type.getSimpleName().toLowerCase();
	}
	
	private Type(Class<T> type) {
		this.type = type;
	}
	
	static class StringType extends Type<String> {
		
		@Override
		public String valueOf(String string) {
			return string;
		}
		
		private StringType() {
			super(String.class);
		}
		
	}
	
	static class BooleanType extends Type<Boolean> {
		
		@Override
		public Boolean valueOf(String string) throws Exception {
			return Boolean.valueOf(string);
		}
		
		private BooleanType() {
			super(Boolean.class);
		}
		
	}
	
	static class IntegerType extends Type<Integer> {
		
		@Override
		public Integer valueOf(String string) throws Exception {
			return Integer.valueOf(string);
		}
		
		private IntegerType() {
			super(Integer.class);
		}
		
	}
	
	static class LongType extends Type<Long> {
		
		@Override
		public Long valueOf(String string) throws Exception {
			return Long.valueOf(string);
		}
		
		private LongType() {
			super(Long.class);
		}
		
	}
	
	static class FloatType extends Type<Float> {
		
		@Override
		public Float valueOf(String string) throws Exception {
			return Float.valueOf(string);
		}
		
		private FloatType() {
			super(Float.class);
		}
		
	}
	
	static class DoubleType extends Type<Double> {
		
		@Override
		public Double valueOf(String string) throws Exception {
			return Double.valueOf(string);
		}
		
		private DoubleType() {
			super(Double.class);
		}
		
	}
	
	static class DateType extends Type<Date> {
		
		@Override
		public Date valueOf(String string) throws Exception {
			return DateFormat.getDateInstance().parse(string);
		}
		
		private DateType() {
			super(Date.class);
		}
		
	}
	
	static class UuidType extends Type<java.util.UUID> {
		
		@Override
		public java.util.UUID valueOf(String string) throws Exception {
			return java.util.UUID.fromString(string);
		}
		
		private UuidType() {
			super(java.util.UUID.class);
		}
		
	}
	
	static class UriType extends Type<java.net.URI> {
		
		@Override
		public java.net.URI valueOf(String string) throws Exception {
			return java.net.URI.create(string);
		}
		
		private UriType() {
			super(java.net.URI.class);
		}
		
	}
	
}
