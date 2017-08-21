package fr.icam.emit.entities;

public class Token {

	private String passphrase;

	private Account account;
	
	private Measurand measurand;
	
	private Long expired;

	public String getPassphrase() {
		return passphrase;
	}

	public Account getAccount() {
		return account;
	}

	public Measurand getMeasurand() {
		return measurand;
	}
	
	public Long getExpired() {
		return expired;
	}

	public Token(String passphrase, Account account, Measurand measurand, Long expired) {
		this.passphrase = passphrase;
		this.account = account;
		this.measurand = measurand;
		this.expired = expired;
	}
	
}
