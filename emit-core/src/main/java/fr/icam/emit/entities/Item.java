package fr.icam.emit.entities;

public class Item {

	private Long id;
	
	private Long deleted;

	public Long getId() {
		return id;
	}

	public Long getDeleted() {
		return deleted;
	}

	public Item(Long id, Long deleted) {
		super();
		this.id = id;
		this.deleted = deleted;
	}

}
