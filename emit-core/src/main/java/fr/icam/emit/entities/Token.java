package fr.icam.emit.entities;

public class Token {

	private String passphrase;

	private Account account;
	
	private Measurand measurand;
	
	private Long deleted;
	
	private Long issued;

	public String getPassphrase() {
		return passphrase;
	}

	public Account getAccount() {
		return account;
	}

	public Measurand getMeasurand() {
		return measurand;
	}
	
	public Long getDeleted() {
		return deleted;
	}
	
	public Long getIssued() {
		return issued;
	}

	public Token(String passphrase, Long deleted, Account account, Measurand measurand, Long issued) {
		this.passphrase = passphrase;
		this.account = account;
		this.measurand = measurand;
		this.deleted = deleted;
		this.issued = issued;
	}
	
}
