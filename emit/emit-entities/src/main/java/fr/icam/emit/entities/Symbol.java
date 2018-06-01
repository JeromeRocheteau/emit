package fr.icam.emit.entities;

public class Symbol {

	private String name;
	
	private String html;

	public String getName() {
		return name;
	}

	public String getHtml() {
		return html;
	}

	public Symbol(String name, String html) {
		super();
		this.name = name;
		this.html = html;
	}
	
}
