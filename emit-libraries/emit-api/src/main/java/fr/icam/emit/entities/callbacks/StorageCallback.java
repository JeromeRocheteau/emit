package fr.icam.emit.entities.callbacks;

import fr.icam.emit.entities.Callback;

public class StorageCallback extends Callback {

	private String collection;

	public String getCollection() {
		return collection;
	}

	public StorageCallback(Long id, String name, Long issued, String user, Boolean atomic, String category, String collection) {
		super(id, name, issued, user, atomic, category);
		this.collection = collection;
	}

	
}
