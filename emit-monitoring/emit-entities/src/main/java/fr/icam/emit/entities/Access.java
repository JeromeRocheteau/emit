package fr.icam.emit.entities;

public class Access {

	private Long id;
	
	private Long issued;
	
	private Token token;

	public Long getId() {
		return id;
	}

	public Long getIssued() {
		return issued;
	}

	public Token getToken() {
		return token;
	}

	public Access(Long id, Long issued, Token token) {
		super();
		this.id = id;
		this.issued = issued;
		this.token = token;
	}
	
}
