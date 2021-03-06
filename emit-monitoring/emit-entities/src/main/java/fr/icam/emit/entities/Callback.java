package fr.icam.emit.entities;

public class Callback {

	private Long id;
	
	private String name;
	
	private Long issued;
	
	private String user;
	
	private Boolean atomic;
	
	private String category;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Long getIssued() {
		return issued;
	}

	public String getUser() {
		return user;
	}

	public Boolean getAtomic() {
		return atomic;
	}

	public String getCategory() {
		return category;
	}

	public Callback(Long id, String name, Long issued, String user, Boolean atomic, String category) {
		super();
		this.id = id;
		this.issued = issued;
		this.user = user;
		this.atomic = atomic;
		this.category = category;
		this.name = name;
	}
	
}
